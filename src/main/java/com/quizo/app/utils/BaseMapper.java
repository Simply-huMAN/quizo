package com.quizo.app.utils;


import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<RequestDto, ResponseDto, Entity> {
    RequestDto toDto(Entity entity);
    Entity toEntity(RequestDto dto);
    ResponseDto toResponseDto(Entity entity);
    List<RequestDto> toDtoList(List<Entity> entityList);
    List<ResponseDto> toResponseDtoList(List<Entity> entityList);
    List<Entity> toEntityList(List<RequestDto> dtoList);
    void updateEntityFromDto(RequestDto dto, @MappingTarget Entity entity);
}
