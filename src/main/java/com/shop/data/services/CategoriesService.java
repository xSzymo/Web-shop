package com.shop.data.services;

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

	public void save(Category category) {
		boolean leaveIfCannotSaveOrUpdateCategory = false;
		if (category == null)
			return;
		if (findOneByName(category.getName()) != null) {
			leaveIfCannotSaveOrUpdateCategory = true;
			if (category.getId() != null) {
				leaveIfCannotSaveOrUpdateCategory = true;
				if (findOne(category.getId()) != null)
					leaveIfCannotSaveOrUpdateCategory = false;
			}
			if (leaveIfCannotSaveOrUpdateCategory == true)
				return;
		}

		category.getBooks().forEach(
				x -> {
					x.setCategory(category);
					booksService.save(x);
				});
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
		try {
			return repository.findOne(id);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Category findOne(Category category) {
		try {
			return repository.findOne(category.getId());
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