package com.shop.controllers.shop.actions;

import java.math.BigDecimal;
import java.util.HashSet;

import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Book;

public class EmailText {
	public static String textFromAnonymous(BigDecimal price, EnumPayments paymentType, String shippingAddressStreet, String shippingAddressPostalCode, 
			String shippingAddressCity, String shippingAddressCountry, String billingAddressStreet, String billingAddressPostalCode, 
			String billingAddressCity, String billingAddressCountry, HashSet<Book> basket) {
		String text = "";
		text += "Price to pay : " + price + "\n";
		text += "\nBooks in order :\n";
		for (Book x : basket)
			text += "\t " + x.getName() + "\n";

		text += "\nPayment type : ";
		text += "\n\t" + paymentType;

		text += "\n\nShippingAddress : ";
		text += "\n\tStreet : " + shippingAddressStreet;
		text += "\n\tPostalCode : " + shippingAddressPostalCode;
		text += "\n\tCity : " + shippingAddressCity;
		text += "\n\tCountry : " + shippingAddressCountry;

		text += "\nBillingAddress : ";
		text += "\n\tStreet : " + billingAddressStreet;
		text += "\n\tPostalCode : " + billingAddressPostalCode;
		text += "\n\tCity : " + billingAddressCity;
		text += "\n\tCountry : " + billingAddressCountry;
		return text;
	}
	
	public static String textFromUser(BigDecimal price, EnumPayments paymentType, String shippingAddressStreet, String shippingAddressPostalCode, 
			String shippingAddressCity, String shippingAddressCountry, String billingAddressStreet, String billingAddressPostalCode, 
			String billingAddressCity, String billingAddressCountry, String login, HashSet<Book> basket) {
		String text = "";
		text += "Hello user " + login + "\n\n";
		text += "Price to pay : " + price + "\n";
		text += "\nBooks in order :\n";
		for (Book x : basket)
			text += "\t " + x.getName() + "\n";

		text += "\nPayment type : ";
		text += "\n\t" + paymentType;

		text += "\n\nShippingAddress : ";
		text += "\n\tStreet : " + shippingAddressStreet;
		text += "\n\tPostalCode : " + shippingAddressPostalCode;
		text += "\n\tCity : " + shippingAddressCity;
		text += "\n\tCountry : " + shippingAddressCountry;

		text += "\nBillingAddress : ";
		text += "\n\tStreet : " + billingAddressStreet;
		text += "\n\tPostalCode : " + billingAddressPostalCode;
		text += "\n\tCity : " + billingAddressCity;
		text += "\n\tCountry : " + billingAddressCountry;
		return text;
	}
}
