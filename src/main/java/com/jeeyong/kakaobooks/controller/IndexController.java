package com.jeeyong.kakaobooks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;
import com.jeeyong.kakaobooks.service.MemberService;
import com.jeeyong.kakaobooks.utils.CookieBox;
import com.jeeyong.kakaobooks.utils.SU;

@Controller
@EnableAutoConfiguration
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	MemberService memberService;

	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res, Model model) {

		String account = CookieBox.getAccount(req);

		model.addAttribute("EnumTarget", EnumBookTarget.values());
		model.addAttribute("EnumCategory", EnumBookCategory.values());
		Member member = memberService.getMember(account);
		if (member == null) {
			CookieBox.logout(res);
			return "redirect:/loginForm";
		} else {
		}

		return "/index";
	}

	@RequestMapping("/detail")
	public String detail(HttpServletRequest req, HttpServletResponse res, Model model) {
		String isbn = SU.getStringParameter(req, "isbn", "");

		return "/detail";
	}

}
