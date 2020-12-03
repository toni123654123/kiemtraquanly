package service;



import model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findOne(Long id);

    Category save(Category Category);

    List<Category> save(List<Category> categories);

    boolean exists(Long id);

    List<Category> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(Category Category);

    void delete(List<Category> categories);

    void deleteAll();
}
