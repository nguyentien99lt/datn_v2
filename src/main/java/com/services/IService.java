package com.services;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import org.hibernate.service.spi.ServiceException;

import java.util.Optional;

public interface IService<T> {

    public T create(T entity);
    public T update(T entity);
    public T delete(Integer id);
    public Optional<T> readById(Integer id);
    public FindByPageResponse<T> findByPage(FindByPageRequest findByPageRequest) throws ServiceException;;

    public FindByPageResponse<T> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException;;

}
