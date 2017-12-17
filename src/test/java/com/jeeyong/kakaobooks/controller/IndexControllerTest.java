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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jeeyong.kakaobooks.dao.Bookmark;
import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.service.BookmarkService;
import com.jeeyong.kakaobooks.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(IndexControllerTest.class);

	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@MockBean
	private MemberService memberService;

	@MockBean
	private BookmarkService bookmarkService;

	private MockHttpServletRequest req;
	private MockHttpServletResponse res;

	@Before
	public void setUp() throws Exception {
		//
		Member member = new Member("test", "1234", Timestamp.valueOf(LocalDateTime.now()));
		given(memberService.getMember(member.getAccount())).willReturn(member);

		Bookmark bookmark = new Bookmark(1l, "테스트용 북마크된 책제목", "12341234", Timestamp.valueOf(LocalDateTime.now()),
				member);
		List<Bookmark> bookmarklist = new ArrayList<Bookmark>();
		bookmarklist.add(bookmark);
		Page<Bookmark> pageBookmark = new PageImpl<>(bookmarklist);
		given(bookmarkService.getBookmark(member, "12341234")).willReturn(bookmark);
		given(bookmarkService.findByMember(member, new PageRequest(0, 10))).willReturn(pageBookmark);

		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		req.setCookies(new Cookie("account_site", member.getAccount()));

	}

	@Test
	public void testIndex() throws Exception {

		req.setMethod("get");
		req.setRequestURI("/");

		Object handler = handlerMapping.getHandler(req).getHandler();
		ModelAndView mav = handlerAdapter.handle(req, res, handler);
		assertEquals(res.getStatus(), 200);
		assertThat(mav.getViewName(), CoreMatchers.is("/index"));

	}

	@Test
	public void testDetail() throws Exception {

		req.setMethod("get");
		req.setRequestURI("/detail");

		req.addParameter("isbn", "12341234");

		Object handler = handlerMapping.getHandler(req).getHandler();
		ModelAndView mav = handlerAdapter.handle(req, res, handler);
		assertEquals(res.getStatus(), 200);
		assertThat(mav.getViewName(), CoreMatchers.is("/detail"));

		Bookmark bookmark = (Bookmark) mav.getModel().get("bookmark");
		assertNotNull(bookmark);

	}

	@Test
	public void testBookmarks() throws Exception {

		req.setMethod("get");
		req.setRequestURI("/bookmarks");

		Object handler = handlerMapping.getHandler(req).getHandler();
		ModelAndView mav = handlerAdapter.handle(req, res, handler);
		assertEquals(res.getStatus(), 200);
		assertThat(mav.getViewName(), CoreMatchers.is("/bookmarks"));

	}

	@Test
	public void testSearchHistory() throws Exception {

		req.setMethod("get");
		req.setRequestURI("/searchHistory");

		Object handler = handlerMapping.getHandler(req).getHandler();
		ModelAndView mav = handlerAdapter.handle(req, res, handler);
		assertEquals(res.getStatus(), 200);

		assertThat(mav.getViewName(), CoreMatchers.is("/searchHistory"));
	}

	@After
	public void tearDown() throws Exception {
	}
}
