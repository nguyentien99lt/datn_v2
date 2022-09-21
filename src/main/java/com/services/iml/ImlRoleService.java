package com.services.iml;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.RoleEntity;
import com.repositories.IRoleRepository;
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
public class ImlRoleService implements IService<RoleEntity> {

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public RoleEntity create(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        RoleEntity role = roleRepository.findById(roleEntity.getId()).get();
        if (role != null) {
            role.setName(roleEntity.getName());
            role.setStatus(roleEntity.getStatus());
            return roleRepository.save(role);
        } else {
            return null;
        }
    }

    @Override
    public RoleEntity delete(Integer id) {
        RoleEntity role = roleRepository.findById(id).get();
        if (role != null) {
            roleRepository.deleteById(id);
            return role;
        } else {
            return null;
        }
    }

    @Override
    public Optional<RoleEntity> readById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public FindByPageResponse<RoleEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<RoleEntity> pageCart = roleRepository.findAll(page);
            List<RoleEntity> RoleEntityList = pageCart.getContent();
            FindByPageResponse<RoleEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(RoleEntityList);
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
    public FindByPageResponse<RoleEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<RoleEntity> pageCart = roleRepository.findAll(page);
            List<RoleEntity> RoleEntityList = pageCart.getContent();
            FindByPageResponse<RoleEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(RoleEntityList);
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
