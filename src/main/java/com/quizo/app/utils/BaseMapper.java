package com.quizo.app.utils;


import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<Dto, Entity> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
    List<Dto> toDtoList(List<Entity> entityList);
    List<Entity> toEntityList(List<Dto> dtoList);
    void updateEntityFromDto(Dto dto, @MappingTarget Entity entity);
}
