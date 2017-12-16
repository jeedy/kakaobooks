package com.jeeyong.kakaobooks.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.dao.SearchHistory;
import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;
import com.jeeyong.kakaobooks.service.ApiService;
import com.jeeyong.kakaobooks.service.BookmarkService;
import com.jeeyong.kakaobooks.service.MemberService;
import com.jeeyong.kakaobooks.service.SearchHistoryService;
import com.jeeyong.kakaobooks.utils.CookieBox;

@RestController
@RequestMapping("ajax/")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	ApiService apiService;

	@Autowired
	MemberService memberService;

	@Autowired
	BookmarkService bookmarkService;

	@Autowired
	SearchHistoryService searchHistoryService;

	/**
	 * 책 검색 restAPI
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchBooks")
	public Map<String, Object> searchBooks(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("searchWord") String searchWord, @RequestParam("target") String target,
			@RequestParam("category") String category, @RequestParam(name = "page", defaultValue = "1") int page) {

		String account = CookieBox.getAccount(req);
		Member member = memberService.getMember(account);
		Map<String, Object> result = apiService.searchBooks(searchWord, target, category, page);
		searchHistoryService
				.save(new SearchHistory(searchWord, target, category, Timestamp.valueOf(LocalDateTime.now()), member));

		//
		return result;
	}

	/**
	 * 책 정보 restAPI
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getBookinfo/isbn/{isbn}", method = RequestMethod.GET)
	public Map<String, Object> getBookinfo(@PathVariable String isbn) {

		Map<String, Object> result = apiService.searchBooks(isbn, EnumBookTarget.ISBN.getCode(),
				EnumBookCategory.전체.getCode(), 1);

		//
		return result;
	}

	/**
	 * 북마크 하기
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bookmark", method = RequestMethod.POST)
	public String bookmark(HttpServletRequest req, HttpServletResponse res, @RequestParam("isbn") String isbn) {

		String account = CookieBox.getAccount(req);
		Member member = memberService.getMember(account);
		if (member == null) {
			return "FAIL";
		}
		boolean result = bookmarkService.addBookmark(member, isbn);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	/**
	 * 북마크 삭제하기
	 * 
	 * @param req
	 * @param res
	 * @param isbn
	 * @return
	 */
	@RequestMapping(value = "/unbookmark", method = RequestMethod.POST)
	public String unbookmark(HttpServletRequest req, HttpServletResponse res, @RequestParam("isbn") String isbn) {

		String account = CookieBox.getAccount(req);
		Member member = memberService.getMember(account);
		if (member == null) {
			return "FAIL";
		}
		boolean result = bookmarkService.deleteBookmark(member, isbn);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

}
