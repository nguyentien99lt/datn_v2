package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.RoleEntity;
import com.services.iml.ImlRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private ImlRoleService roleService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<RoleEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return roleService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<RoleEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return roleService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<RoleEntity> readById(@PathVariable Integer id) {
        return roleService.readById(id);
    }

    @PostMapping("/create")
    public RoleEntity create(@RequestBody RoleEntity role) {
        return roleService.create(role);
    }

    @PutMapping("/update")
    public RoleEntity update(@RequestBody RoleEntity role) {
        return roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public RoleEntity delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }
}
