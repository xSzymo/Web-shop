package com.shop.data.services;

import com.shop.data.repositories.BooksRepository;
import com.shop.data.repositories.OrdersRepository;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Iterator;

//TODO - fix
@Service
@Transactional
public class BooksService {
	@Autowired
	private BooksRepository repository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private PicturesService picturesService;

	public void save(Book book) {
		if (book == null)
			return;
		if (book.getCategory() == null)
			return;
		repository.save(book);
	}

	public void save(Collection<Book> books) {
		if (books.size() > 0)
			books.forEach(
					x -> {
						if (x != null)
							save(x);
					});
	}

	public Book findOne(long id) {
		return repository.findOne(id);
	}

	public Book findOne(Book book) {
		return repository.findOne(book.getId());
	}

	public Iterable<Book> findAll() {
		return repository.findAll();
	}

	public void delete(long id) {
		Book foundBook = repository.findById(id);
		deleteOperation(foundBook);
	}

	public void delete(Book book) {
		delete(book.getId());
	}

	public void delete(Collection<Book> book) {
		if (book.size() > 0)
			book.forEach(x -> {
				if (x.getId() != null)
					delete(x.getId());
			});
	}

	private void deleteOperation(Book book) {
		if (book == null)
			return;

		removeBookFromOrders(book);
		removeBookFromCategories(book);

		picturesService.delete(book.getPictures());
		repository.delete(book.getId());
	}

	private void removeBookFromCategories(Book book) {
		Iterable<Category> categories = categoriesService.findAll();

		for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext(); ) {
			Category x2 = iterator.next();
			for (Iterator<Book> iterator2 = x2.getBooks().iterator(); iterator2.hasNext(); ) {
				Book x3 = iterator2.next();
				if (x3.getId() == book.getId()) {
					iterator2.remove();
					categoriesService.save(x2);
				}
			}
		}
	}

	private void removeBookFromOrders(Book book) {
		Iterable<Order> orders = ordersRepository.findAll();

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext(); ) {
			Order x = iterator.next();
			for (Iterator<Book> iterator2 = x.getBooks().iterator(); iterator2.hasNext(); ) {
				Book x1 = iterator2.next();
				if (x1.getId() == book.getId()) {
					iterator2.remove();
					ordersRepository.save(x);
				}
			}
		}
	}
}
