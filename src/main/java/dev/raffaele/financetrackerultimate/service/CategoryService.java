package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.model.CategoryModel;

import java.util.List;

public interface CategoryService {

     CategoryModel createCategory(CategoryModel categoryModel);
     CategoryModel getCategoryById(int id);
     List<CategoryModel> getAllCategories();
     CategoryModel updateCategory(int id, CategoryModel categoryModel);
     void deleteById(int id);

}
