package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;

import com.entities.CategoryEntity;
import com.services.iml.ImlCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

}
