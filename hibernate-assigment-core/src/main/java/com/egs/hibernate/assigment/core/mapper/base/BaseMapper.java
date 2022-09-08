package com.egs.hibernate.assigment.core.mapper.base;

/**
 * This interface is designed to correctly map Entities
 * from the received type to the response type.
 *
 * @param <Entity>
 * @param <Request>
 * @param <Response>
 *
 */
public interface BaseMapper <Entity, Request, Response> {

    Entity toEntity(Request request);

    Response toResponse(Entity entity);
}
