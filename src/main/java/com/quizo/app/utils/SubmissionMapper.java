package com.quizo.app.utils;

import com.quizo.app.dao.model.Submission;
import com.quizo.app.dto.SubmissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubmissionMapper extends BaseMapper<SubmissionDTO, Object, Submission> {
}
