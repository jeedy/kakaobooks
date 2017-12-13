package com.jeeyong.kakaobooks.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeeyong.kakaobooks.service.ApiService;
import com.jeeyong.kakaobooks.utils.SU;

@RestController
@RequestMapping("/ajax/*")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	@Autowired
	ApiService apiService;

	@RequestMapping(value = "/searchBooks")
	public Map<String, Object> index(HttpServletRequest req, HttpServletResponse res, Model model) {
		String searchWord = SU.getStringParameter(req, "searchWord", "");
		String target = SU.getStringParameter(req, "target", "");
		String category = SU.getStringParameter(req, "category", "");
		int page = SU.getIntParameter(req, "page", 1);

		Map<String, Object> result = apiService.searchBooks(searchWord, target, category, page);

		//
		return result;
	}
}
