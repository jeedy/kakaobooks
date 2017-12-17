package com.jeeyong.kakaobooks.service;

import static org.junit.Assert.*;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(ApiServiceTest.class);

	@Autowired
	private ApiService apiService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSearchBooks() throws Exception {
		// 1. 카카오 책 검색 API 호출
		Map<String, Object> jsonMap = apiService.searchBooks("시작하세요", EnumBookTarget.전체.getCode(),
				EnumBookCategory.전체.getCode(), 1);

		// 2. 검색된 정보 (책 리스트) 확인.
		assertNotNull(jsonMap.get("documents"));

	}

	@Test
	public void testGetBookByISBN() throws Exception {
		// 1. 카카오 책 검색 API 호출
		Map<String, Object> document = apiService.getBookByISBN("9791158390785"); // title: 시작하세요! C# 7.1 프로그래밍

		// 2. 찾은 책 정보 확인
		assertNotNull(document);
		assertEquals(document.get("title"), "시작하세요! C# 7.1 프로그래밍");

	}

}
