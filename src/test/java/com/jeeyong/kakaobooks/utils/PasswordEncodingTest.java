package com.jeeyong.kakaobooks.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncodingTest {
	private static final Logger logger = LoggerFactory.getLogger(PasswordEncodingTest.class);

	@Test
	public void test() {
		String password = "1234";
		String otherPassword = "4321";

		PasswordEncoding passwordEncoding = new PasswordEncoding();
		String pw1 = passwordEncoding.encode(password);
		logger.info("암호1 : " + pw1);
		String pw2 = passwordEncoding.encode(password);
		logger.info("암호2 : " + pw2);

		logger.info("비교 passward <> pw1 : " + passwordEncoding.matches(password, pw1));

		assertTrue(passwordEncoding.matches(password, pw1));

		assertFalse(passwordEncoding.matches(otherPassword, pw1));

	}

}
