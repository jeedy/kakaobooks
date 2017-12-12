package com.jeeyong.kakaobooks.controller;

import java.sql.Date;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/loginForm")
	public String index( HttpServletRequest req, HttpServletResponse res, Model model ) {
		
		//
		return "login/loginForm";
	}

}
