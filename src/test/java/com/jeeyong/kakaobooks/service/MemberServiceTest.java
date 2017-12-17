package com.jeeyong.kakaobooks.service;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeeyong.kakaobooks.dao.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);

	@Autowired
	private MemberService memberService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertSelect() throws Exception {

		// 1. 회원정보 저장
		Member member = new Member("testMember", "1234", Timestamp.valueOf(LocalDateTime.now()));
		memberService.save(member);

		// 2. 회원정보 검색
		Member searchedMember = memberService.getMember(member.getAccount());
		assertEquals(member.getAccount(), searchedMember.getAccount());
	}

}
