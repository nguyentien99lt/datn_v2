package com.services.iml;


import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.dto.CategoryDTO;
import com.dto.OrderDTO;
import com.dto.UserDTO;
import com.entities.BrandEntity;
import com.entities.CategoryEntity;
import com.entities.OrderEntity;
import com.entities.UserEntity;
import com.repositories.IBrandRepository;
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
public class ImlBrandService implements IService<BrandEntity> {

    @Autowired
    private IBrandRepository brandRepository;


    @Override
    public BrandEntity create(BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }

    @Override
    public BrandEntity update(BrandEntity brandEntity) {
        BrandEntity brand = brandRepository.findById(brandEntity.getId()).get();
        if (brand != null) {
            brand.setName(brandEntity.getName());
            brand.setAddress(brandEntity.getAddress());
            brand.setStatus(brandEntity.getStatus());
            return brandRepository.save(brand);
        } else {
            return null;
        }
    }

    @Override
    public BrandEntity delete(Integer id) {
        BrandEntity brand = brandRepository.findById(id).get();
        if (brand != null) {
            brandRepository.deleteById(id);
            return brand;
        } else {
            return null;
        }
    }

    @Override
    public Optional<BrandEntity> readById(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public FindByPageResponse<BrandEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<BrandEntity> pageCart = brandRepository.findAll(page);
            List<BrandEntity> BrandEntityList = pageCart.getContent();
            FindByPageResponse<BrandEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(BrandEntityList);
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
    public FindByPageResponse<BrandEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<BrandEntity> pageCart = brandRepository.findAll(page);
            List<BrandEntity> BrandEntityList = pageCart.getContent();
            FindByPageResponse<BrandEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(BrandEntityList);
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
