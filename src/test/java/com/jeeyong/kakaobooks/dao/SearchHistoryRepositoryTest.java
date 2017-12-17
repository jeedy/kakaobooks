package com.jeeyong.kakaobooks.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
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

import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchHistoryRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SearchHistoryRepository SearchHistoryRepository;

	private Member member;

	@Before
	public void setUp() throws Exception {
		member = new Member("test", "1234", Timestamp.valueOf(LocalDateTime.now()));
		// this.memberRepository.save(member);
		this.entityManager.persist(member);
	}

	@Test
	public void testInsertSelectAndDelete() {
		SearchHistory searchHistory = new SearchHistory("시작하세요", EnumBookTarget.전체.getCode(),
				EnumBookCategory.전체.getCode(), Timestamp.valueOf(LocalDateTime.now()), member);
		this.SearchHistoryRepository.save(searchHistory);

		// 1. 저장된 것 확인
		assertThat(searchHistory.getSearch_word()).isEqualTo("시작하세요");
		// 2. 회원으로 검색
		assertTrue(this.SearchHistoryRepository.findByMember(member, new PageRequest(0, 1)).getContent().size() > 0);
		// 3. 삭제 테스트
		this.SearchHistoryRepository
				.delete(this.SearchHistoryRepository.findByMember(member, new PageRequest(0, 1)).getContent());
		assertFalse(this.SearchHistoryRepository.findByMember(member, new PageRequest(0, 1)).getContent().size() > 0);

	}

	@After
	public void tearDown() throws Exception {

	}
}
