package com.jeeyong.kakaobooks.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeeyong.kakaobooks.utils.CryptEncoding;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);

	private MockHttpServletRequest req;
	private MockHttpServletResponse res;

	@Autowired
	private LoginService loginService;

	@Before
	public void setUp() throws Exception {
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();

	}

	@Test
	public void testLogin() {
		CryptEncoding encoding = new CryptEncoding();
		boolean result = loginService.login(res, "testMember", encoding.encode("1234"));
		logger.info("islogined =" + result);

		String redirectUrl = loginService.logout(res);
		assertEquals("redirect:/loginForm", redirectUrl);

	}

}
