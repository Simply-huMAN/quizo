package com.quizo.app.service;

import com.quizo.app.dao.model.*;
import com.quizo.app.dao.model.Question;
import com.quizo.app.dao.repository.*;
import com.quizo.app.dto.*;
import com.quizo.app.response.Response;
import com.quizo.app.utils.FormMapper;
import com.quizo.app.utils.QuestionMapper;
import com.quizo.app.utils.SubmissionMapper;
import com.quizo.app.utils.SubmissionQuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class FormService {
    private FormMapper formMapper;
    private FormRepository formRepository;
    private QuestionMapper questionMapper;
    private QuestionRepository questionRepository;
    private SolutionRepository solutionRepository;
    private SubmissionMapper submissionMapper;
    private SubmissionQuestionRepository submissionQuestionRepository;
    private SubmissionQuestionMapper submissionQuestionMapper;
    private SubmissionRepository submissionRepository;

    public UUID createForm(FormDTO formDto) {
        // Save form in FORM Table
        Form form = formMapper.toEntity(formDto);
        var formResponse = formRepository.save(form);

        // Save questions in QUESTION Table
        AtomicInteger index = new AtomicInteger(0);
        List<Question> questionsList = questionMapper.toEntityList(formDto.getQuestions());
        questionsList.stream().forEach((question) -> {
            question.setFormId(formResponse.getFormId());
            var response = questionRepository.save(question);

            // Save solutions in SOLUTION Table
            int i = index.getAndIncrement();
            Solution solution = new Solution();
            solution.setQuestionId(response.getQuestionId());
            solution.setCorrectOptionIds(formDto.getQuestions().get(i).getCorrectOptionIds());
            solution.setExplanation(formDto.getQuestions().get(i).getExplanation());
            solutionRepository.save(solution);
        });
        // questionRepository.saveAll(questionsList);
        return formResponse.getFormId();
    }

    public FormResponseDTO getFormById(UUID id, boolean includeQuestions) {
        if(includeQuestions) {
            Form form = formRepository.getById(id);
            var questions = questionRepository.findAllByFormId(id);
            List<QuestionResponseDTO> questionDTOS = questionMapper.toResponseDtoList(questions);
            FormResponseDTO formDTO = formMapper.toResponseDto(form);
            formDTO.setQuestions(questionDTOS);
            return formDTO;
        }
        Form form = formRepository.getById(id);
        return formMapper.toResponseDto(form);
    }

    public Response submitForm(SubmissionDTO submissionDTO) {
        // Save submission in SUBMISSION Table
        Submission submission = submissionMapper.toEntity(submissionDTO);
        var submissionResponse = submissionRepository.save(submission);

        // Save submission questions in SUBMISSION_QUESTION Table
        List<SubmissionQuestionDTO> submissionQuestionDTOS = submissionDTO.getAnswers();
        submissionQuestionDTOS.stream().forEach(submissionQuestionDTO -> {
            UUID submissionId = submissionResponse.getSubmissionId();
            submissionQuestionDTO.setSubmissionId(submissionId);

            SubmissionQuestion submissionQuestion = submissionQuestionMapper.toEntity(submissionQuestionDTO);
            submissionQuestionRepository.save(submissionQuestion);
        });
        return Response.<String>builder()
                .metadata(Metadata.builder().message("success").pagination(null).timestamp(Instant.now()).build())
                .data(String.format("Form submitted successfully, Submission ID: %s",submissionResponse.getSubmissionId()))
                .build();
    }

    public Response<Integer> getFormResults(UUID submissionId) {
        // Check if score already evaluated for the submission
        Submission submission = submissionRepository.getById(submissionId);
        if(submission.getTotalScore() != null) {
            return Response.<Integer>builder()
                    .metadata(Metadata.builder().message("success").pagination(null).timestamp(Instant.now()).build())
                    .data(submission.getTotalScore())
                    .build();
        }

        // Calculate score and correct answers for the submission
        // Get all questions for the submission, get all correct answers for the questions, compare and calculate score
        UUID formId = submission.getFormId();
        AtomicInteger score = new AtomicInteger(0);

        // Fetch all questions for the form
        List<Question> questions = questionRepository.findAllByFormId(formId);
        questions.stream().map(question -> {
            Solution solution = solutionRepository.findByQuestionId(question.getQuestionId());
            SubmissionQuestion submissionQuestion = submissionQuestionRepository.findAllByQuestionIdAndSubmissionId(question.getQuestionId(), submissionId);
            // Compare solution and submissionQuestion to calculate score for the question
            int scoreObtained = 0;
            if(solution.getCorrectOptionIds().equals(submissionQuestion.getSelectedOptionId())) {
                scoreObtained += Integer.parseInt(question.getScore());
                // Modify status of the submission to CORRECT
                submissionQuestion.setIsCorrect(true);
            }
            else {
                scoreObtained += Integer.parseInt(question.getNegativeScore());
                submissionQuestion.setIsCorrect(false);
            }
            submissionQuestion.setScoreObtained(scoreObtained);
            submissionQuestionRepository.save(submissionQuestion);
            return scoreObtained;
        }).filter(scoreObtained -> {
            score.addAndGet(scoreObtained);
            return true;
        }).toList();
        // Mark submission state to EVALUATED
        submission.setTotalScore(score.get());
        submission.setEvaluationStatus(Status.COMPLETED);
        submission.setScoreEvaluatedAt(Instant.now());
        submissionRepository.save(submission);

        // Return score
        return Response.<Integer>builder()
                .metadata(Metadata.builder().message("success").pagination(null).timestamp(Instant.now()).build())
                .data(score.get())
                .build();
    }

}
