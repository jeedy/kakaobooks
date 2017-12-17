package com.jeeyong.kakaobooks.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptEncodingTest {
	private static final Logger logger = LoggerFactory.getLogger(CryptEncodingTest.class);

	@Test
	public void test() {
		String password = "1234";
		String otherPassword = "4321";

		// 1. 암호화 객체 생성
		CryptEncoding passwordEncoding = new CryptEncoding();

		// 2. password 암호1
		String pw1 = passwordEncoding.encode(password);
		logger.info("암호1 : " + pw1);

		// 3. password 암호2
		String pw2 = passwordEncoding.encode(password);
		logger.info("암호2 : " + pw2);

		// pw1 과 pw2 암호 비교.
		assertTrue(passwordEncoding.matches(password, pw1));

		// pw1 과 다른 암호(otherPassword) 비교.
		assertFalse(passwordEncoding.matches(otherPassword, pw1));

	}

}
