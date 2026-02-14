package com.quizo.app.utils;

import com.quizo.app.dao.model.Question;
import com.quizo.app.dto.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends BaseMapper<QuestionDTO, Question> {}
