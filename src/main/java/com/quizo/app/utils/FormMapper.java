package com.quizo.app.utils;

import com.quizo.app.dao.model.Form;
import com.quizo.app.dto.FormDTO;
import com.quizo.app.dto.FormResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormMapper extends BaseMapper<FormDTO, FormResponseDTO, Form> {}
