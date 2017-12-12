package com.jeeyong.kakaobooks.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.jeeyong.kakaobooks.dao.Member;

import lombok.Data;

public class LoginUserDetails extends User{

	private final Member member;

	
	public Member getMember() {
		return member;
	}


	public LoginUserDetails(Member member) {
		//
		super(member.getAccount(), member.getPwd(), AuthorityUtils.createAuthorityList("ROLE_USER") );
		this.member = member;
	}

}
