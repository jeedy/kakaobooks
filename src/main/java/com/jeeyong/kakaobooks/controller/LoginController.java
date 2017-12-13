package com.jeeyong.kakaobooks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeyong.kakaobooks.service.LoginService;
import com.jeeyong.kakaobooks.utils.SU;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@RequestMapping("/loginForm")
	public String index(HttpServletRequest req, HttpServletResponse res, Model model) {
		String account = SU.getStringParameter(req, "account", "").trim();
		String pwd = SU.getStringParameter(req, "pwd", "").trim();

		if (account.isEmpty() && pwd.isEmpty()) {
		} else {
			if (loginService.login(req, res)) {
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
