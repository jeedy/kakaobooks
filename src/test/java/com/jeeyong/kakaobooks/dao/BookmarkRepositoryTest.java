package com.jeeyong.kakaobooks.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookmarkRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	private Member member;

	@Before
	public void setUp() throws Exception {
		member = new Member("test", "1234", Timestamp.valueOf(LocalDateTime.now()));
		this.entityManager.persist(member);
	}

	@Test
	public void testInsertSelectAndDelete() {
		// 1. 북마크 저장
		Bookmark bookmark = new Bookmark("시작하세요", "12341234", Timestamp.valueOf(LocalDateTime.now()), member);
		this.bookmarkRepository.save(bookmark);

		// 2. 회원, ISDN 으로 북마크 검색
		assertNotNull(this.bookmarkRepository.findOneByMemberAccountAndIsbn(member, "12341234"));

		// 3. 회원으로 검색
		assertTrue(this.bookmarkRepository.findByMember(member, new PageRequest(0, 1)).getContent().size() > 0);

		// 4. 삭제 테스트
		this.bookmarkRepository
				.delete(this.bookmarkRepository.findByMember(member, new PageRequest(0, 1)).getContent());

		// 5. 삭제된 내용 확인
		assertFalse(this.bookmarkRepository.findByMember(member, new PageRequest(0, 1)).getContent().size() > 0);

	}

	@After
	public void tearDown() throws Exception {

	}
}
