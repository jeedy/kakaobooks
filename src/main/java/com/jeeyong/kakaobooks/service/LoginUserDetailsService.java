package com.jeeyong.kakaobooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.dao.MemberRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		Member member = memberRepository.findOne(account);
		if(member == null) {
			throw new UsernameNotFoundException("계정을 찾을 수 없습니다.");
		}
		return new LoginUserDetails(member);
	}

}
