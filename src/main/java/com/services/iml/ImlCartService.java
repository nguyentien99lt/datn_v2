package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.CartEntity;
import com.repositories.ICartRepository;
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
public class ImlCartService implements IService<CartEntity> {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public CartEntity create(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }

    @Override
    public CartEntity update(CartEntity cartEntity) {
        CartEntity cart = cartRepository.findById(cartEntity.getId()).get();
        if (cart != null) {
            cart.setCreateDate(cartEntity.getCreateDate());
            cart.setUser(cartEntity.getUser());
            cart.setStatus(cartEntity.getStatus());
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }

    @Override
    public CartEntity delete(Integer id) {
        CartEntity cart = cartRepository.findById(id).get();
        if (cart != null) {
            cartRepository.deleteById(id);
            return cart;
        } else {
            return null;
        }
    }

    @Override
    public Optional<CartEntity> readById(Integer id) {
        return cartRepository.findById(id);
    }

    @Override
    public FindByPageResponse<CartEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<CartEntity> pageCart = cartRepository.findAll(page);
            List<CartEntity> CartEntityList = pageCart.getContent();
            FindByPageResponse<CartEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(CartEntityList);
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
    public FindByPageResponse<CartEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<CartEntity> pageCart = cartRepository.findAll(page);
            List<CartEntity> CartEntityList = pageCart.getContent();
            FindByPageResponse<CartEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(CartEntityList);
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
