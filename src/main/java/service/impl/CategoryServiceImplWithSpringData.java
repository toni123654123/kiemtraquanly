package service.impl;


import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoryRepository;
import service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CategoryServiceImplWithSpringData implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return streamAll().collect(Collectors.toList());
    }

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category save(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public List<Category> save(List<Category> categories) {
        Iterable<Category> updatedProvinces = categoryRepository.save(categories);
        return streamAll(updatedProvinces).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return categoryRepository.exists(id);
    }

    @Override
    public List<Category> findAll(List<Long> ids) {
        Iterable<Category> Provinces = categoryRepository.findAll(ids);
        return streamAll(Provinces).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void delete(Category Category) {
        categoryRepository.delete(Category);
    }

    @Override
    public void delete(List<Category> categories) {
        categoryRepository.delete(categories);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    private Stream<Category> streamAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false);
    }

    private Stream<Category> streamAll(Iterable<Category> Provinces) {
        return StreamSupport.stream(Provinces.spliterator(), false);
    }
}
