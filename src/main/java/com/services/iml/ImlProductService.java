package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.ProductEntity;
import com.repositories.IProductRepository;
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
public class ImlProductService implements IService<ProductEntity> {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductEntity productEntity) {
        ProductEntity product = productRepository.findById(productEntity.getId()).get();
        if (product != null) {
            product.setBrand(productEntity.getBrand());
            product.setCategory(productEntity.getCategory());
            product.setName(productEntity.getName());
            product.setStatus(productEntity.getStatus());

            return productRepository.save(product);
        } else {
            return null;
        }
    }

    @Override
    public ProductEntity delete(Integer id) {
        ProductEntity color = productRepository.findById(id).get();
        if (color != null) {
            productRepository.deleteById(id);
            return color;
        } else {
            return null;
        }
    }

    @Override
    public Optional<ProductEntity> readById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public FindByPageResponse<ProductEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<ProductEntity> pageCart = productRepository.findAll(page);
            List<ProductEntity> ProductEntityList = pageCart.getContent();
            FindByPageResponse<ProductEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(ProductEntityList);
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
    public FindByPageResponse<ProductEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<ProductEntity> pageCart = productRepository.findAll(page);
            List<ProductEntity> ProductEntityList = pageCart.getContent();
            FindByPageResponse<ProductEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(ProductEntityList);
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
