package com.egs.hibernate.mapper;

import java.util.Set;

public interface BaseMapper<E, D> {
  E dtoToEntity(D dto);

  D entityToDTO(E entity);

  Set<E> entityToDTOSet(Set<D> DTOS);
}
