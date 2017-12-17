package com.jeeyong.kakaobooks.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeeyong.kakaobooks.service.BookmarkService;
import com.jeeyong.kakaobooks.service.MemberService;
import com.jeeyong.kakaobooks.service.SearchHistoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AjaxControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(AjaxControllerTest.class);

	@Autowired
	private AjaxController ajaxController;

	@MockBean
	private MemberService memberService;

	@MockBean
	private BookmarkService bookmarkService;

	@MockBean
	private SearchHistoryService searchHistoryService;

	private MockHttpServletRequest req;
	private MockHttpServletResponse res;

	@Before
	public void setUp() throws Exception {
		//
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();

	}

	@Test
	public void testSearchBooks() throws Exception {
		Map<String, Object> result = ajaxController.searchBooks(req, res, "시작하세요", null, null, 1);
		assertNotNull(result.get("documents"));
		logger.info("### result = " + result);

	}

	@Test
	public void testGetBookinfo() throws Exception {
		Map<String, Object> result = ajaxController.getBookinfo("9791158390785");
		assertNotNull(result.get("documents"));
		logger.info("### result = " + result);
	}

	@Test
	public void testBookmark() throws Exception {
		String result = ajaxController.bookmark(req, res, "9791158390785");
		assertEquals("FAIL", result);
	}

	@Test
	public void testUnbookmark() throws Exception {
		String result = ajaxController.unbookmark(req, res, "9791158390785");
		assertEquals("FAIL", result);
	}

}
