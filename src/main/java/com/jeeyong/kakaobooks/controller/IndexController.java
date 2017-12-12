package com.jeeyong.kakaobooks.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeyong.kakaobooks.enums.EnumCategory;
import com.jeeyong.kakaobooks.enums.EnumTarget;


@Controller
@EnableAutoConfiguration
public class IndexController {
	
	@RequestMapping("/")
	public String index( HttpServletRequest req, HttpServletResponse res, Model model ) {
		
		//
		model.addAttribute("EnumTarget", EnumTarget.values());
		model.addAttribute("EnumCategory", EnumCategory.values());
		return "/index";
	}

	
}
