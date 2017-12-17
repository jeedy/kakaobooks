package com.jeeyong.kakaobooks.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void testInsertAndSelect() {
		this.memberRepository.save(new Member("test", "1234", Timestamp.valueOf(LocalDateTime.now())));
		Member member = this.memberRepository.findOne("test");
		assertThat(member.getAccount()).isEqualTo("test");
	}
}
