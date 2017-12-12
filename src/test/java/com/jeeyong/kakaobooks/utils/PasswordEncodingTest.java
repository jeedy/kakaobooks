package com.jeeyong.kakaobooks.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PasswordEncodingTest {

	@Test
	public void test() {
		String password="1234";
		String otherPassword = "4321";
		
		PasswordEncoding passwordEncoding = new PasswordEncoding();
		String pw1 = passwordEncoding.encode(password);
		System.out.println("암호1 : "+ pw1);
		String pw2 = passwordEncoding.encode(password);
		System.out.println("암호2 : "+ pw2);
		
		System.out.println("비교 passward <> pw1 : "+ passwordEncoding.matches(password, pw1) );
		
		
		assertTrue(passwordEncoding.matches(password, pw1) );
		
		assertFalse(passwordEncoding.matches(otherPassword, pw1));

	}

}
