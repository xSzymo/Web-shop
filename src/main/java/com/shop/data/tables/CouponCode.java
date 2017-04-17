package com.shop.data.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon_codes")
public class CouponCode {
	@Id
	@GeneratedValue
	@Column(name = "codes_id")
	private Long id;

	@Column(name = "discount")
	private double codeDiscount;
	@Column(name = "code")
	private String code;

	public CouponCode() {

	}

	public CouponCode(double codeDiscount, String code) {
		this.codeDiscount = codeDiscount;
		this.code = code;
	}

	public CouponCode(Long id, double codeDiscount, String code) {
		this.id = id;
		this.codeDiscount = codeDiscount;
		this.code = code;
	}

	@Override
	public String toString() {
		return "CouponCodes [id=" + id + ", codeDiscount=" + codeDiscount + ", code=" + code + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCodeDiscount() {
		return codeDiscount;
	}

	public void setCodeDiscount(double codeDiscount) {
		this.codeDiscount = codeDiscount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
