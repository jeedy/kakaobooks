package com.jeeyong.kakaobooks.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.dao.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	@Transactional
	public Member getMember(String account) {
		return memberRepository.findOne(account);
	}

	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}

}
