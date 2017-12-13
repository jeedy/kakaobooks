package com.jeeyong.kakaobooks.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.utils.CookieBox;
import com.jeeyong.kakaobooks.utils.PasswordEncoding;
import com.jeeyong.kakaobooks.utils.SU;

@Service
public class LoginService {

	@Autowired
	MemberService memberService;

	@Transactional
	public boolean login(HttpServletRequest req, HttpServletResponse res) {
		//
		String account = SU.getStringParameter(req, "account", "").trim();
		String pwd = SU.getStringParameter(req, "pwd", "").trim();

		Member member = memberService.getMember(account);
		if (!account.isEmpty() && !pwd.isEmpty()) {
			PasswordEncoder passwordEncoder = new PasswordEncoding();
			if (member == null) { // 계정없는 경우 신규생성
				member = new Member(account, passwordEncoder.encode(pwd));
				memberService.save(member);
				CookieBox.login(res, account);
				return true;
			}
			if (member != null) {
				if (passwordEncoder.matches(pwd, member.getPwd())) {
					CookieBox.login(res, account);
					return true;
				}
			}

		}
		return false;
	}

}
