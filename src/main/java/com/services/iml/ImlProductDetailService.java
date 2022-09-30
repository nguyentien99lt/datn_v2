package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.ProductDetailEntity;
import com.repositories.IProductDetailRepository;
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
public class ImlProductDetailService implements IService<ProductDetailEntity> {

    @Autowired
    private IProductDetailRepository productDetailRepository;

    @Override
    public ProductDetailEntity create(ProductDetailEntity productDetailEntity) {
        return productDetailRepository.save(productDetailEntity);
    }

    @Override
    public ProductDetailEntity update(ProductDetailEntity productDetailEntity) {
        ProductDetailEntity productDetail = productDetailRepository.findById(productDetailEntity.getId()).get();
        if (productDetail != null) {
            productDetail.setProduct(productDetailEntity.getProduct());
            productDetail.setColor(productDetailEntity.getColor());
            productDetail.setCreateDate(productDetailEntity.getCreateDate());
            productDetail.setName(productDetailEntity.getName());
            productDetail.setSize(productDetailEntity.getSize());
            productDetail.setImage(productDetailEntity.getImage());
            productDetail.setPrice(productDetailEntity.getPrice());
            productDetail.setQuantity(productDetailEntity.getQuantity());
            productDetail.setStatus(productDetailEntity.getStatus());
            productDetail.setDescription(productDetailEntity.getDescription());
            return productDetailRepository.save(productDetail);
        } else {
            return null;
        }
    }



    @Override
    public ProductDetailEntity delete(Integer id) {
        ProductDetailEntity color = productDetailRepository.findById(id).get();
        if (color != null) {
            productDetailRepository.deleteById(id);
            return color;
        } else {
            return null;
        }
    }

    @Override
    public Optional<ProductDetailEntity> readById(Integer id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public FindByPageResponse<ProductDetailEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<ProductDetailEntity> pageCart = productDetailRepository.findAll(page);
            List<ProductDetailEntity> ProductDetailEntityList = pageCart.getContent();
            FindByPageResponse<ProductDetailEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(ProductDetailEntityList);
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
    public FindByPageResponse<ProductDetailEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<ProductDetailEntity> pageCart = productDetailRepository.findAll(page);
            List<ProductDetailEntity> ProductDetailEntityList = pageCart.getContent();
            FindByPageResponse<ProductDetailEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(ProductDetailEntityList);
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
