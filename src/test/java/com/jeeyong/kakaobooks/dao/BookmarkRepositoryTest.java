package com.jeeyong.kakaobooks.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookmarkRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	private Member member;

	@Before
	public void setUp() throws Exception {
		member = new Member("test", "1234", Timestamp.valueOf(LocalDateTime.now()));
		this.memberRepository.save(member);
	}

	@Test
	public void testInsertAndSelect() {
		Bookmark bookmark = new Bookmark("시작하세요", "12341234", Timestamp.valueOf(LocalDateTime.now()), member);
		this.bookmarkRepository.save(bookmark);
		assertThat(bookmark.getTitle()).isEqualTo("시작하세요");

		assertNotNull(this.bookmarkRepository.findOneByMemberAccountAndIsbn(member, "12341234"));

		assertTrue(this.bookmarkRepository.findByMember(member, new PageRequest(0, 1)).getContent().size() > 0);

	}

	@After
	public void tearDown() throws Exception {

	}
}
