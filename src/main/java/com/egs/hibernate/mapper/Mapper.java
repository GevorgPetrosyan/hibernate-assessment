package com.egs.hibernate.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public <S, T> List<T> mapToDTOList(List<S> source, Class<T> targetClass) {
        ModelMapper mapper = new ModelMapper();
        return source.stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
