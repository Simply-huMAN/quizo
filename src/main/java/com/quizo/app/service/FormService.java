package com.quizo.app.service;

import com.quizo.app.dao.model.Form;
import com.quizo.app.dao.model.Question;
import com.quizo.app.dao.model.Solution;
import com.quizo.app.dao.repository.FormRepository;
import com.quizo.app.dao.repository.QuestionRepository;
import com.quizo.app.dao.repository.SolutionRepository;
import com.quizo.app.dao.repository.SubmissionRepository;
import com.quizo.app.dto.FormDTO;
import com.quizo.app.utils.FormMapper;
import com.quizo.app.utils.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FormService {
    private FormMapper formMapper;
    private FormRepository formRepository;
    private QuestionMapper questionMapper;
    private QuestionRepository questionRepository;
    private SolutionRepository solutionRepository;
    private SubmissionRepository submissionRepository;

    public FormService(FormMapper formMapper, FormRepository formRepository, QuestionMapper questionMapper, QuestionRepository questionRepository, SolutionRepository solutionRepository, SubmissionRepository submissionRepository) {
        this.formMapper = formMapper;
        this.formRepository = formRepository;
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
        this.solutionRepository = solutionRepository;
        this.submissionRepository = submissionRepository;
    }

    public void createForm(FormDTO formDto) {
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
    }

}
