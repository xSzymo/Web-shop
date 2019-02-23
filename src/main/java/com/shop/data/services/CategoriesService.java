package com.shop.data.services;

import com.shop.data.repositories.BooksRepository;
import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CategoriesService {
    @Autowired
    private CategoriesRepository repository;
    @Autowired
    private BooksService booksService;
    @Autowired
    private BooksRepository booksRepository;

    public void save(Category category) {
        if (category == null)
            return;
        Category existCategory = findOneByName(category.getName());

        if (existCategory != null) {
            existCategory.setBooks(category.getBooks());
            existCategory.getBooks().forEach(
                    x -> {
                        x.setCategory(category);
                        booksRepository.save(x);
                    });

            repository.save(existCategory);
        } else {
            repository.save(category);
        }
    }

    public void save(Collection<Category> category) {
        if (category.size() > 0)
            category.forEach(
                    x -> {
                        if (x != null)
                            save(x);
                    });
    }

    public Category findOne(long id) {
        try {
            return repository.findOne(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Category findOne(String name) {
        try {
            return repository.findByName(name);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Category findOne(Category category) {
        try {
            if (category.getName() == null)
                return null;

            return findOneByName(category.getName());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Category findOneByName(String name) {
        Iterable<Category> all = findAll();
        try {
            for (Category x : all)
                if (x.getName().equals(name))
                    return x;
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    public Category findOneByName(Category category) {
        Iterable<Category> all = findAll();

        try {
            String name = category.getName();
            for (Category x : all)
                if (x.getName().equals(name))
                    return x;
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    public Iterable<Category> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        Category foundBook = repository.findById(id);
        deleteOperation(foundBook);
    }

    public void delete(String name) {
        deleteOperation(repository.findByName(name));
    }

    public void delete(Category category) {
        delete(category.getId());
    }

    public void delete(Collection<Category> category) {
        if (category.size() > 0)
            category.forEach(x -> {
                if (x.getId() != null)
                    delete(x.getId());
            });
    }

    private void deleteOperation(Category category) {
        if (category == null)
            return;

        category.getBooks().forEach(x -> booksService.delete(x));

        repository.delete(category.getId());
    }
}