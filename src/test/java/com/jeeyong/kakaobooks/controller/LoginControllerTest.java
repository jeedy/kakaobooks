package com.jeeyong.kakaobooks.controller;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jeeyong.kakaobooks.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@MockBean
	private LoginService loginService;

	private MockHttpServletRequest req;
	private MockHttpServletResponse res;

	@Before
	public void setUp() throws Exception {
		//
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();

	}

	@Test
	public void testIndex() throws Exception {

		req.setMethod("get");
		req.setRequestURI("/loginForm");

		Object handler = handlerMapping.getHandler(req).getHandler();
		ModelAndView mav = handlerAdapter.handle(req, res, handler);
		assertEquals(res.getStatus(), 200);

	}

	@Test
	public void testLogout() throws Exception {
		req.setMethod("get");
		req.setRequestURI("/logout");

		Object handler = handlerMapping.getHandler(req).getHandler();
		ModelAndView mav = handlerAdapter.handle(req, res, handler);
		assertEquals(res.getStatus(), 200);

	}

	@After
	public void tearDown() throws Exception {
	}
}
