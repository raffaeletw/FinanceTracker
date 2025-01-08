package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.entity.CategoryEntity;
import dev.raffaele.financetrackerultimate.exception.CategoryNotFoundException;
import dev.raffaele.financetrackerultimate.model.CategoryModel;
import dev.raffaele.financetrackerultimate.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryModel createCategory(CategoryModel categoryModel){
        CategoryEntity categoryEntity = mapToEntity(categoryModel);
        CategoryEntity savedEntity = categoryRepository.save(categoryEntity);
        return mapToModel(savedEntity);
    }


    public CategoryModel getCategoryById(int id){
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
        if(categoryEntityOptional.isEmpty()){
        throw new CategoryNotFoundException() ;
    }
        return mapToModel(categoryEntityOptional.get());
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryModel updateCategory(int id, CategoryModel categoryModel) {
        CategoryEntity existingCategory = categoryRepository.findById(id).
                orElseThrow(() -> new CategoryNotFoundException());

        existingCategory.setName(categoryModel.getName());
        existingCategory.setDescription(categoryModel.getDescription());
        existingCategory.setIcon(categoryModel.getIcon());

        CategoryEntity updatedEntity = categoryRepository.save(existingCategory);
        return mapToModel(updatedEntity);
    }

    @Override
    public void deleteById(int id) {
        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException());
        categoryRepository.delete(existingCategory);
    }


    private CategoryEntity mapToEntity (CategoryModel categoryModel){
        return new CategoryEntity(
                categoryModel.getId(),
                categoryModel.getName(),
                categoryModel.getDescription(),
                categoryModel.getIcon()
        );
    }

    private CategoryModel mapToModel (CategoryEntity categoryEntity){
        return new CategoryModel(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getDescription(),
                categoryEntity.getIcon()
        );
    }


}
