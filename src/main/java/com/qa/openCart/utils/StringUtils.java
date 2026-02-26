package com.qa.openCart.utils;

public class StringUtils {
	
	public static String getRandomEmailId() {
		String emailId = "userauto" + System.currentTimeMillis() + "@opencart.com";
		System.out.println("User email id is : " + emailId);
		return emailId;
	}

}
