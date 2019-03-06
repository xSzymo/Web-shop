package com.shop.data.tables;

import com.shop.data.enums.EnumPayments;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

//TODO - after delete repositoryAccess add user to constructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "payment_method")
    private EnumPayments paymentMethod;
    @Column(name = "realized")
    private boolean realized = false;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @CollectionTable(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @OneToOne
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_codes_id")
    private CouponCode couponCodes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Column(name = "book_id")
    private Collection<Book> books = new ArrayList<Book>();

    public Order() {
    }

    public Order(BigDecimal price, boolean realized) {
        this.price = price;
        this.realized = realized;
    }

    public Order(BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId, Address billingAddressId,
                 CouponCode couponCodes) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddressId;
        this.billingAddress = billingAddressId;
        this.couponCodes = couponCodes;
    }

    public Order(BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId, Address billingAddressId,
                 CouponCode couponCodes, Collection<Book> books) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddressId;
        this.billingAddress = billingAddressId;
        this.couponCodes = couponCodes;
        this.books = books;
    }


    public Order(BigDecimal price, boolean realized, Collection<Book> books) {
        this.price = price;
        this.realized = realized;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", price=" + price + ", paymentMethod=" + paymentMethod + ", shippingAddressId="
                + shippingAddress + ", billingAddressId=" + billingAddress + ", couponCodes=" + couponCodes + ", books=" + books + "]";
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!Objects.equals(id, order.id)) return false;
        if (realized != order.realized) return false;
        if (paymentMethod != order.paymentMethod) return false;

        if (user != null ? !user.getLogin().equals(order.user.getLogin()) : order.user != null) return false;
        if (!Objects.equals(shippingAddress, order.shippingAddress))
            return false;
        if (!Objects.equals(billingAddress, order.billingAddress))
            return false;
        return Objects.equals(couponCodes, order.couponCodes);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (realized ? 1 : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (couponCodes != null ? couponCodes.hashCode() : 0);
        return result;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EnumPayments getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(EnumPayments paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address shippingAddressId) {
        this.shippingAddress = shippingAddressId;
    }

    public Address getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(Address billingAddressId) {
        this.billingAddress = billingAddressId;
    }

    public CouponCode getCouponCodes() {
        return this.couponCodes;
    }

    public void setCouponCodes(CouponCode couponCodes) {
        this.couponCodes = couponCodes;
    }

    public synchronized Collection<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public boolean getIsRealized() {
        return this.realized;
    }

    public void setRealized(boolean realized) {
        this.realized = realized;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

