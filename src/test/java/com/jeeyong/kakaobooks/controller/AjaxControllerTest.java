package com.jeeyong.kakaobooks.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeeyong.kakaobooks.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AjaxControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(AjaxControllerTest.class);
	@Autowired
	AjaxController ajaxController;

	@Autowired
	MemberService memberService;

	private MockMvc mockMvc;

	private String jsonStringFromObject(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	@Test
	public void testSearch() throws Exception {
		// memberService.save(new Member("test", "1234",
		// Timestamp.valueOf(LocalDateTime.now())));
		//
		// MockHttpServletRequest req = new MockHttpServletRequest();
		// MockHttpServletResponse res = new MockHttpServletResponse();
		//
		// Model model = new ExtendedModelMap();
		// Map<String, Object> result = ajaxController.searchBooks(req, res, "시작하세요",
		// null, null, 1);
		// logger.info("result = " + result);

	}

	@Test
	public void testGetBookinfo() throws Exception {
		// memberService.save(new Member("test", "1234",
		// Timestamp.valueOf(LocalDateTime.now())));
		// MvcResult result =
		// mockMvc.perform(get("/ajax/getBookinfo/isbn/9791158390785")).andExpect(status().isOk())
		// .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();
		// logger.info(result.getResponse().getContentAsString());

	}
}
