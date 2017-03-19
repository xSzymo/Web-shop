package com.shop.data.tables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.shop.data.enums.EnumPayments;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@Column(name = "order_price")
	private BigDecimal price;
	@Column(name = "order_payment_method")
	private EnumPayments paymentMethod;
	@Column(name = "realized")
	private boolean realized = false;

	@OneToOne
	@JoinColumn(name = "shipping_address_id")
	private Address shippingAddress;

	@OneToOne
	@JoinColumn(name = "billing_address_id")
	private Address billingAddress;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "coupon_codes_id")
	private CouponCodes couponCodes;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@Column(name = "books_id")
	private Collection<Books> books = new ArrayList<Books>();

	public Orders() {
	}
	
	public Orders(BigDecimal price, boolean realized) {
		super();
		this.price = price;
		this.realized = realized;
	}

	public Orders(BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId, Address billingAddressId,
			CouponCodes couponCodes) {
		super();
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.shippingAddress = shippingAddressId;
		this.billingAddress = billingAddressId;
		this.couponCodes = couponCodes;
	}

	public Orders(BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId, Address billingAddressId,
			CouponCodes couponCodes, Collection<Books> books) {
		super();
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.shippingAddress = shippingAddressId;
		this.billingAddress = billingAddressId;
		this.couponCodes = couponCodes;
		this.books = books;
	}

	public Orders(Long id, BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId,
			Address billingAddressId, CouponCodes couponCodes, Collection<Books> books) {
		this.id = id;
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.shippingAddress = shippingAddressId;
		this.billingAddress = billingAddressId;
		this.couponCodes = couponCodes;
		this.books = books;
	}
	
	public Orders(Long id, BigDecimal price, boolean realized, Collection<Books> books) {
		this.id = id;
		this.price = price;
		this.realized = realized;
		this.books = books;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", price=" + price + ", paymentMethod=" + paymentMethod + ", shippingAddressId="
				+ shippingAddress + ", billingAddressId=" + billingAddress + ", couponCodes=" + couponCodes				+ ", books=" + books + "]";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CouponCodes getCouponCodes() {
		return this.couponCodes;
	}

	public void setCouponCodes(CouponCodes couponCodes) {
		this.couponCodes = couponCodes;
	}

	public synchronized Collection<Books> getBooks() {
		return this.books;
	}

	public void setBooks(Collection<Books> books) {
		this.books = books;
	}

	public boolean getIsRealized() {
		return this.realized;
	}

	public void setRealized(boolean realized) {
		this.realized = realized;
	}
}
