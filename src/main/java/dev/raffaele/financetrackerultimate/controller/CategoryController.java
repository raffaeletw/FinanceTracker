package dev.raffaele.financetrackerultimate.controller;

import dev.raffaele.financetrackerultimate.model.CategoryModel;
import dev.raffaele.financetrackerultimate.repository.CategoryRepository;
import dev.raffaele.financetrackerultimate.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories/")

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryController(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel categoryModel){
        return ResponseEntity.ok(categoryService.createCategory(categoryModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable int id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable int id, @RequestBody CategoryModel categoryModel){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
