package com.jeeyong.kakaobooks.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndexControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(IndexControllerTest.class);

	@Autowired
	private IndexController indexController;

	@MockBean
	private MemberService memberService;

	@Test
	public void testController() {

		memberService.save(new Member("test", "1234", Timestamp.valueOf(LocalDateTime.now())));

		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();

		Model model = new ExtendedModelMap();
		String result = indexController.index(req, res, model);
		logger.info("result = " + result);
		Map<String, Object> modelAsMap = model.asMap();

	}
}
