package com.shop.data.enums;

public enum EnumPayments {
	BANKTRANSFER("bank transfer"), PACKAGEAFTERPAY("after");
	
	private String type;
	
	EnumPayments(String type){
		this.type = type;
	}
	
	public String getPaymentType() {
		return type;
	}

}
