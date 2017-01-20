package com.shop.data.tables;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon_codes")
public class CouponCodes {
	@Id
	@GeneratedValue
	@Column(name = "coupon_codes_id")
	private Long id;

	@Column(name = "coupon_code_discount")
	private BigDecimal codeDiscount;
	@Column(name = "coupon_code")
	private String code;

	public CouponCodes() {

	}

	public CouponCodes(BigDecimal codeDiscount, String code) {
		super();
		this.codeDiscount = codeDiscount;
		this.code = code;
	}

	public CouponCodes(Long id, BigDecimal codeDiscount, String code) {
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

	public BigDecimal getCodeDiscount() {
		return codeDiscount;
	}

	public void setCodeDiscount(BigDecimal codeDiscount) {
		this.codeDiscount = codeDiscount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
