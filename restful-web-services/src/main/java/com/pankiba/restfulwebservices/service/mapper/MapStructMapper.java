package com.pankiba.restfulwebservices.service.mapper;

import java.util.List;

public interface MapStructMapper<D, E> {

	D toDto(E e);

	E toEntity(D d);

	List<D> toDto(List<E> entityList);

	List<E> toEntity(List<D> dtoList);
}
