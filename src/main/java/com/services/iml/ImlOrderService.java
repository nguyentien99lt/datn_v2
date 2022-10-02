package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.dto.OrderDTO;
import com.dto.OrderInterface;
import com.entities.OrderEntity;
import com.repositories.IOrderRepository;
import com.services.IService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImlOrderService implements IService<OrderEntity> {


    @Autowired
    private IOrderRepository orderRepository;

    public FindByPageResponse<OrderInterface> listAll(OrderDTO dto, Pageable pageable)
    {
//    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	
    	Integer firstResult = (pageable.getPageNumber() * pageable.getPageSize());
		Integer maxResults = (pageable.getPageSize());
		
    	List<OrderInterface> listData = orderRepository.findByOrder(dto.getName(), dto.getPhone(), firstResult, maxResults);

    	int totalCount = orderRepository.totalCountByOrder(dto.getName(), dto.getPhone());
    	Page<OrderInterface> page = (Page<OrderInterface>) new PageImpl<OrderInterface>(
    			listData, pageable, totalCount);
    	List<OrderInterface> list = page.getContent();
    	FindByPageResponse<OrderInterface> reponse = new FindByPageResponse<OrderInterface>();
   	 	reponse.setPageResponse(list);
        reponse.setPageSize(page.getSize());
        reponse.setCurrentPage(page.getNumber());
        reponse.setTotalPage(page.getTotalPages());
        reponse.setTotalElement(page.getTotalElements());
        return reponse;
    }
    
    @Override
    public OrderEntity create(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
        OrderEntity order = orderRepository.findById(orderEntity.getId()).get();
        if (order != null) {
            order.setOrderAddress(orderEntity.getOrderAddress());
            order.setOrderDate(orderEntity.getOrderDate());
            order.setReceivedDate(orderEntity.getReceivedDate());
            order.setCart(orderEntity.getCart());
            order.setPhone(orderEntity.getPhone());
            order.setUser(orderEntity.getUser());
            order.setStatus(orderEntity.getStatus());
            order.setNote(orderEntity.getNote());
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public OrderEntity delete(Integer id) {
        OrderEntity color = orderRepository.findById(id).get();
        if (color != null) {
            orderRepository.deleteById(id);
            return color;
        } else {
            return null;
        }
    }

    @Override
    public Optional<OrderEntity> readById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public FindByPageResponse<OrderEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<OrderEntity> pageCart = orderRepository.findAll(page);
            List<OrderEntity> OrderEntityList = pageCart.getContent();
            FindByPageResponse<OrderEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(OrderEntityList);
            reponse.setPageSize(pageCart.getSize());
            reponse.setCurrentPage(pageCart.getNumber());
            reponse.setTotalPage(pageCart.getTotalPages());
            reponse.setTotalElement(pageCart.getTotalElements());
            return reponse;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public FindByPageResponse<OrderEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<OrderEntity> pageCart = orderRepository.findAll(page);
            List<OrderEntity> OrderEntityList = pageCart.getContent();
            FindByPageResponse<OrderEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(OrderEntityList);
            reponse.setPageSize(pageCart.getSize());
            reponse.setCurrentPage(pageCart.getNumber());
            reponse.setTotalPage(pageCart.getTotalPages());
            reponse.setTotalElement(pageCart.getTotalElements());
            return reponse;
        } catch (Exception e) {
            return null;
        }
    }

}
