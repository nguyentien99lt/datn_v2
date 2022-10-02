package com.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.dto.CategoryDTO;
import com.entities.CategoryEntity;
import com.services.iml.ImlCategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ImlCategoryService categoryService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<CategoryEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return categoryService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<CategoryEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return categoryService.findByPageParam(pageNumber, pageSize);
    }
    
    @GetMapping("/read-by-id/{id}")
    public Optional<CategoryEntity> readById(@PathVariable Integer id) {
        return categoryService.readById(id);
    }

    @PostMapping("/create")
    public CategoryEntity create(@RequestBody CategoryEntity category) {
        return categoryService.create(category);
    }

    @PutMapping("/update")
    public CategoryEntity update(@RequestBody CategoryEntity category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public CategoryEntity delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
//    
//    @PostMapping("/search")
//    public List<CategoryEntity> viewCate(@RequestBody CategoryDTO request ) {
//    	return	categoryService.listAll(request);
//    }
    @PostMapping("/search")
    public FindByPageResponse<CategoryEntity> viewCate(
    		@RequestBody CategoryDTO dto, 
    		@RequestParam (name = "pageNumber", defaultValue = "0") Integer pageNumber
    		,@RequestParam (name = "pageSize", defaultValue = "2") Integer pageSize  ) {
    	return	categoryService.listAll(dto, pageNumber, pageSize);
    }

}
