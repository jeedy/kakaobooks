package com.jeeyong.kakaobooks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeeyong.kakaobooks.service.LoginService;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@RequestMapping("/loginForm")
	public String index(HttpServletRequest req, HttpServletResponse res, Model model,
			@RequestParam(name = "account", defaultValue = "") String account,
			@RequestParam(name = "pwd", defaultValue = "") String pwd) {

		if (account.isEmpty() && pwd.isEmpty()) {
		} else {
			if (loginService.login(res, account, pwd)) {
				return "redirect:/";
			} else {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "일치하지 않습니다.");
			}
		}
		return "login/loginForm";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {

		return loginService.logout(res);
	}

}
