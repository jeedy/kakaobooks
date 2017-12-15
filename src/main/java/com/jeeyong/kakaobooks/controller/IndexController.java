package com.jeeyong.kakaobooks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeeyong.kakaobooks.dao.Bookmark;
import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;
import com.jeeyong.kakaobooks.exceptions.IllegalLoginException;
import com.jeeyong.kakaobooks.service.BookmarkService;
import com.jeeyong.kakaobooks.service.MemberService;
import com.jeeyong.kakaobooks.utils.CookieBox;

@Controller
@EnableAutoConfiguration
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	MemberService memberService;

	@Autowired
	BookmarkService bookmarkService;

	private Member getMemberObj(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String account = CookieBox.getAccount(req);
		Member member = memberService.getMember(account);
		if (member == null) {
			throw new IllegalLoginException("로그인 되지 않았습니다.");
		}
		return member;
	}

	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res, Model model) {
		try {
			Member member = getMemberObj(req, res);

			model.addAttribute("EnumTarget", EnumBookTarget.values());
			model.addAttribute("EnumCategory", EnumBookCategory.values());
			return "/index";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/loginForm";
		}

	}

	@RequestMapping("/detail")
	public String detail(HttpServletRequest req, HttpServletResponse res, Model model,
			@RequestParam("isbn") String isbn) {
		try {
			Member member = getMemberObj(req, res);

			Bookmark bookmark = bookmarkService.getBookmark(member, isbn);
			model.addAttribute("bookmark", bookmark);
			return "/detail";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/loginForm";
		}
	}

	@RequestMapping("/bookmarks")
	public String bookmarks(HttpServletRequest req, HttpServletResponse res, Model model,
			@PageableDefault(size = 10, page = 0, sort = "regdate", direction = Direction.ASC) Pageable pageable) {
		try {
			Member member = getMemberObj(req, res);

			Page<Bookmark> bookmarkPage = bookmarkService.findByMember(member, pageable);
			model.addAttribute("bookmarkPage", bookmarkPage);
			return "/bookmarks";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/loginForm";
		}

	}

	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest req, HttpServletResponse res, Model model) {
		try {
			Member member = getMemberObj(req, res);

			return "/mypage";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/loginForm";
		}

	}

}
