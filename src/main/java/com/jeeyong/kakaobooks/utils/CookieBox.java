package com.jeeyong.kakaobooks.utils;

import javax.servlet.http.HttpServletResponse;

public class CookieBox {

	public static void login(HttpServletResponse res, String account) {
		String encryptKey = "";
		String currentTImeStamp = String.valueOf( System.currentTimeMillis() );
	}
}
