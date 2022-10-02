package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.dto.CategoryDTO;
import com.dto.OrderDTO;
import com.dto.UserDTO;
import com.entities.CategoryEntity;
import com.entities.OrderEntity;
import com.entities.SizeEntity;
import com.entities.UserEntity;
import com.entities.SizeEntity;
import com.repositories.ISizeRepository;
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
public class ImlSizeService implements IService<SizeEntity> {

    @Autowired
    private ISizeRepository sizeRepository;
    @Override
    public SizeEntity create(SizeEntity sizeEntity) {
        return sizeRepository.save(sizeEntity);
    }

    @Override
    public SizeEntity update(SizeEntity sizeEntity) {
        SizeEntity size = sizeRepository.findById(sizeEntity.getId()).get();
        if (size != null) {
            size.setSize(sizeEntity.getSize());
            size.setStatus(sizeEntity.getStatus());
            return sizeRepository.save(size);
        } else {
            return null;
        }
    }

    @Override
    public SizeEntity delete(Integer id) {
        SizeEntity size = sizeRepository.findById(id).get();
        if (size != null) {
            sizeRepository.deleteById(id);
            return size;
        } else {
            return null;
        }
    }

    @Override
    public Optional<SizeEntity> readById(Integer id) {
        return sizeRepository.findById(id);
    }

    @Override
    public FindByPageResponse<SizeEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<SizeEntity> pageCart = sizeRepository.findAll(page);
            List<SizeEntity> SizeEntityList = pageCart.getContent();
            FindByPageResponse<SizeEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(SizeEntityList);
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
    public FindByPageResponse<SizeEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<SizeEntity> pageCart = sizeRepository.findAll(page);
            List<SizeEntity> SizeEntityList = pageCart.getContent();
            FindByPageResponse<SizeEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(SizeEntityList);
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
