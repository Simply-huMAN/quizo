package com.quizo.app.utils;

import com.quizo.app.dao.model.SubmissionQuestion;
import com.quizo.app.dto.SubmissionQuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubmissionQuestionMapper extends BaseMapper<SubmissionQuestionDTO, Object, SubmissionQuestion> {
}
