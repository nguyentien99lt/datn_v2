package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.dto.CategoryDTO;
import com.dto.OrderDTO;
import com.dto.UserDTO;
import com.entities.CartDetailEntity;
import com.entities.CategoryEntity;
import com.entities.OrderEntity;
import com.entities.UserEntity;
import com.entities.CartDetailEntity;
import com.repositories.ICartDetailRepository;
import com.services.IService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImlCartDetailService implements IService<CartDetailEntity> {

    @Autowired
    private ICartDetailRepository cartDetailRepository;
    @Override
    public CartDetailEntity create(CartDetailEntity cartDetailEntity) {
        return cartDetailRepository.save(cartDetailEntity);
    }

    @Override
    public CartDetailEntity update(CartDetailEntity cartDetailEntity) {
        CartDetailEntity cartDetail = cartDetailRepository.findById(cartDetailEntity.getId()).get();
        if (cartDetail != null) {
            cartDetail.setCart(cartDetail.getCart());
            cartDetail.setProductDetail(cartDetail.getProductDetail());
            cartDetail.setQuantity(cartDetail.getQuantity());
            cartDetail.setStatus(cartDetail.getStatus());
            return cartDetailRepository.save(cartDetail);
        } else {
            return null;
        }
    }

    @Override
    public CartDetailEntity delete(Integer id) {
        CartDetailEntity cartDetail = cartDetailRepository.findById(id).get();
        if (cartDetail != null) {
            cartDetailRepository.deleteById(id);
            return cartDetail;
        } else {
            return null;
        }
    }

    @Override
    public Optional<CartDetailEntity> readById(Integer id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public FindByPageResponse<CartDetailEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<CartDetailEntity> pageCart = cartDetailRepository.findAll(page);
            List<CartDetailEntity> CartDetailEntityList = pageCart.getContent();
            FindByPageResponse<CartDetailEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(CartDetailEntityList);
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
    public FindByPageResponse<CartDetailEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<CartDetailEntity> pageCart = cartDetailRepository.findAll(page);
            List<CartDetailEntity> CartDetailEntityList = pageCart.getContent();
            FindByPageResponse<CartDetailEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(CartDetailEntityList);
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
