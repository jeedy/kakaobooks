package com.jeeyong.kakaobooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.dao.MemberRepository;

//@EnableAutoConfiguration
//@ComponentScan
public class App implements CommandLineRunner {
	
	@Autowired MemberRepository memberRepository;

	@Override
	public void run(String... arg0) throws Exception {
		//
		Member member = memberRepository.save(new Member("aaa","ddd"));
		
		System.out.println("result="+member);

	}
	
//	public static void main(String[] args) {
//		SpringApplication.run(App.class, args);
//	}

}
