package com.shop.data.tables;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToOne
	@JoinColumn(name = "shipping_address_id")
	private Address shippingAddressId;

	@OneToOne
	@JoinColumn(name = "billing_address_id")
	private Address billingAddressId;

	@OneToOne
	@JoinColumn(name = "coupon_codes_id")
	private CouponCodes couponCodes;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@Column(name = "books_id")
//	private Collection<Books> books = new LinkedHashSet<Books>();

	public Orders() {

	}

	public Orders(BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId, Address billingAddressId,
			CouponCodes couponCodes, Collection<Books> books) {
		super();
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.shippingAddressId = shippingAddressId;
		this.billingAddressId = billingAddressId;
		this.couponCodes = couponCodes;
//		this.books = books;
	}

	public Orders(Long id, BigDecimal price, EnumPayments paymentMethod, Address shippingAddressId,
			Address billingAddressId, CouponCodes couponCodes, Collection<Books> books) {
		this.id = id;
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.shippingAddressId = shippingAddressId;
		this.billingAddressId = billingAddressId;
		this.couponCodes = couponCodes;
//		this.books = books;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", price=" + price + ", paymentMethod=" + paymentMethod + ", shippingAddressId="
				+ shippingAddressId + ", billingAddressId=" + billingAddressId + ", couponCodes=" + couponCodes
//				+ ", books=" + books + "]";
	;}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public EnumPayments getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(EnumPayments paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Address getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Address shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public Address getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(Address billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public CouponCodes getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCodes(CouponCodes couponCodes) {
		this.couponCodes = couponCodes;
	}

//	public Collection<Books> getBooks() {
//		return books;
//	}
//
//	public void setBooks(Collection<Books> books) {
//		this.books = books;
//	}
}
