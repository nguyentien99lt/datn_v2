package com.services.iml;



import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.dto.CategoryDTO;
import com.dto.OrderDTO;
import com.dto.UserDTO;
import com.entities.CategoryEntity;
import com.entities.OrderEntity;
import com.entities.UserEntity;
import com.repositories.ICategoryRepository;
import com.services.IService;

@Service
public class ImlCategoryService implements IService<CategoryEntity> {
    @Autowired
    private ICategoryRepository categoryRepository;
    
  
    public FindByPageResponse<CategoryEntity> listAll(CategoryDTO dto,Integer pageNumber , Integer pageSize) 
    {
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	Page<CategoryEntity>  page = categoryRepository.findByNameLikeAndStatus(dto.getName(),dto.getStatus(), pageable);
    	List<CategoryEntity> list = page.getContent();
    	FindByPageResponse<CategoryEntity> reponse = new FindByPageResponse();
        reponse.setPageResponse(list);
        reponse.setPageSize(page.getSize());
        reponse.setCurrentPage(page.getNumber());
        reponse.setTotalPage(page.getTotalPages());
        reponse.setTotalElement(page.getTotalElements());
        return reponse;
   
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        CategoryEntity category = categoryRepository.findById(categoryEntity.getId()).get();
        if (category != null) {
            category.setName(categoryEntity.getName());
            category.setStatus(categoryEntity.getStatus());
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    @Override
    public CategoryEntity delete(Integer id) {
        CategoryEntity category = categoryRepository.findById(id).get();
        if (category != null) {
            categoryRepository.deleteById(id);
            return category;
        } else {
            return null;
        }
    }

    @Override
    public Optional<CategoryEntity> readById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public FindByPageResponse<CategoryEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<CategoryEntity> pageCart = categoryRepository.findAll(page);
            List<CategoryEntity> CategoryEntityList = pageCart.getContent();
            FindByPageResponse<CategoryEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(CategoryEntityList);
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
    public FindByPageResponse<CategoryEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<CategoryEntity> pageCart = categoryRepository.findAll(page);
            List<CategoryEntity> CategoryEntityList = pageCart.getContent();
            FindByPageResponse<CategoryEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(CategoryEntityList);
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
