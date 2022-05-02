package com.bcstp.helpdesk.tests;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
		
		String  rawPassword4 = "filipe";
		String encodedPassword4 = encoder.encode(rawPassword4); 		
		System.out.println(encodedPassword4);
		
	}

}
















