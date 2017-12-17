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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeeyong.kakaobooks.dao.Bookmark;
import com.jeeyong.kakaobooks.dao.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(BookmarkServiceTest.class);

	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private MemberService memberService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertSelect() throws Exception {
		Member member = new Member("testMember", "1234", Timestamp.valueOf(LocalDateTime.now()));
		memberService.save(member);

		// 1. 북마크 삽입
		boolean isInserted = bookmarkService.addBookmark(member, "9791158390785");
		assertTrue(isInserted);

		// 2. 북마크 검색
		Bookmark bookmark = bookmarkService.getBookmark(member, "9791158390785");
		assertNotNull(bookmark);

		// 3. 북마크 리스트 검색
		Page<Bookmark> pageBookmark = bookmarkService.findByMember(member, new PageRequest(0, 10));
		assertNotNull(pageBookmark);

		// 4. 북마크 삭제
		boolean isDeleted = bookmarkService.deleteBookmark(member, "9791158390785");
		assertTrue(isDeleted);

		// 5. 북마크 검색
		bookmark = bookmarkService.getBookmark(member, "9791158390785");
		assertNull(bookmark);

	}

}
