package com.shop.data.services;

import com.shop.data.repositories.OrdersRepository;
import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Book;
import com.shop.data.tables.Order;
import com.shop.data.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;


@Service
@Transactional
public class OrdersService {
    @Autowired
    private OrdersRepository repository;
    @Autowired
    private BooksService booksService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;

    public void save(Order order) {
        if (order != null)
            if (order.getUser() != null) {
                repository.save(order);
            }
    }

    public void save(Collection<Order> orders) {
        if (orders.size() > 0)
            orders.forEach(
                    x -> {
                        if (x != null)
                            save(x);
                    });
    }

    public Order findOne(long id) {
        return repository.findOne(id);
    }

    public Order findOne(Order order) {
        return repository.findOne(order.getId());
    }

    public Iterable<Order> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        Order foundBook = repository.findById(id);
        deleteOperation(foundBook);
    }

    public void delete(Order order) {
        delete(order.getId());
    }

    public void delete(Collection<Order> orders) {
        orders.forEach(x -> {
            if (x.getId() != null)
                delete(x.getId());
        });
    }

    private void deleteOperation(Order order) {
        if (order == null)
            return;
//		removeBooksFromOrders(order);
        removeUserFromOrders(order);

        try {
            repository.delete(order.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeUserFromOrders(Order order) {
        if (order.getUser() == null)
            return;

        User user = usersRepository.findById(order.getUser().getId());
        user.getOrders().remove(order);
        usersRepository.save(user);
    }

    private void removeBooksFromOrders(Order order) {
        ArrayList<Book> myBooks = new ArrayList<>();
        Iterable<Book> books = booksService.findAll();
        order.getBooks().forEach(
                x -> {
                    for (Book book : books)
                        if (x.getId() == book.getId()) {
                            order.getBooks().remove(book);
                            booksService.save(book);
                        }
                }
        );
    }
}
