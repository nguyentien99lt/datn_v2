package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.ColorEntity;
import com.repositories.IColorRepository;
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
public class ImlColorService implements IService<ColorEntity> {


    @Autowired
    private IColorRepository colorRepository;
    @Override
    public ColorEntity create(ColorEntity colorEntity) {
        return colorRepository.save(colorEntity);
    }

    @Override
    public ColorEntity update(ColorEntity colorEntity) {
        ColorEntity color = colorRepository.findById(colorEntity.getId()).get();
        if (color != null) {
            color.setColor(colorEntity.getColor());
            color.setStatus(colorEntity.getStatus());
        return colorRepository.save(color);
        } else {
            return null;
        }
    }

    @Override
    public ColorEntity delete(Integer id) {
        ColorEntity color = colorRepository.findById(id).get();
        if (color != null) {
            colorRepository.deleteById(id);
            return color;
        } else {
            return null;
        }
    }

    @Override
    public Optional<ColorEntity> readById(Integer id) {
        return colorRepository.findById(id);
    }

    @Override
    public FindByPageResponse<ColorEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<ColorEntity> pageCart = colorRepository.findAll(page);
            List<ColorEntity> ColorEntityList = pageCart.getContent();
            FindByPageResponse<ColorEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(ColorEntityList);
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
    public FindByPageResponse<ColorEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<ColorEntity> pageCart = colorRepository.findAll(page);
            List<ColorEntity> ColorEntityList = pageCart.getContent();
            FindByPageResponse<ColorEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(ColorEntityList);
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
