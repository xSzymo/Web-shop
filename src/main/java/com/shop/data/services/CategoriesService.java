package com.shop.data.services;

import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Iterator;

@Service
@Transactional
public class CategoriesService {
	@Autowired
	private CategoriesRepository repository;
	@Autowired
	private BooksService booksService;

	//if category dont have book add id
	//do not save category when category with same name already exist
	public void save(Category category) {
		if (category == null)
			return;
		category.getBooks().forEach(x -> booksService.save(x));
		repository.save(category);
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
		return repository.findOne(id);
	}

	public Category findOne(Category category) {
		return repository.findOne(category.getId());
	}

	public Iterable<Category> findAll() {
		return repository.findAll();
	}

	public void delete(long id) {
		Category foundBook = repository.findById(id);
		deleteOperation(foundBook);
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

		category.getBooks().forEach(
				x -> booksService.delete(x)
		);

		repository.delete(category.getId());
}
}