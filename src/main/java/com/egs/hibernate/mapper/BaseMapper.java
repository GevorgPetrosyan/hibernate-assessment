package com.egs.hibernate.mapper;

public interface BaseMapper<Entity, Response> {

  Response toResponse(Entity entity);

}
