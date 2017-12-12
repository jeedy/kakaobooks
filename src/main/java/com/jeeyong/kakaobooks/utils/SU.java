package com.jeeyong.kakaobooks.utils;

import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;

public class SU extends ServletRequestUtils {

	/**
	 * 날짜형을 처리해 주는 메소드.
	 * @param req
	 * @param param
	 * @param defaultValue 값이 없는 경우 디폴트 값
	 * @param maxValue 최대 허용 숫자값
	 */
	public static Date getDateParameter( HttpServletRequest req, String param, String defaultValue, String pattern ) {
		
		return Utils.toDate( SU.getStringParameter( req, param, defaultValue ), pattern );
	}
	public static Date getDateParameter( HttpServletRequest req, String param, String defaultValue ) {
		
		return Utils.toDate( SU.getStringParameter( req, param, defaultValue ), "yyyy-MM-dd" );
	}
	
	/**
	 * 숫자형을 처리해 주는 메소드.
	 * @param req
	 * @param param
	 * @param defaultValue 값이 없는 경우 디폴트 값
	 * @param maxValue 최대 허용 숫자값
	 */
	public static int getIntParameter( HttpServletRequest req, String param, int defaultValue, int maxValue ) {
		
		String temp = SU.getStringParameter( req, param, "" ).replaceAll( "_", "" );
		int returnValue = 0;
		if( temp.equals( "" ) ) {
			returnValue = defaultValue;
		} else {
			try {
				returnValue = Integer.parseInt( temp );
			} catch( Exception e ) {
				returnValue = defaultValue;
			}
		}
		if( returnValue > maxValue ) {
			returnValue = maxValue;
		}
		
		//
		return returnValue;
	}
	
	/**
	 * 숫자형을 처리해 주는 메소드.
	 * @param req
	 * @param param
	 * @param defaultValue 값이 없는 경우 디폴트 값
	 */
	public static int getIntParameter( HttpServletRequest req, String param, int defaultValue ) {

		String temp = SU.getStringParameter( req, param, "" ).replaceAll( "_", "" ).replaceAll( ",", "" );
		int returnValue = 0;
		if( temp.equals( "" ) ) {
			returnValue = defaultValue;
		} else {
			try {
				returnValue = Integer.parseInt( temp );
			} catch( Exception e ) {
				returnValue = defaultValue;
			}
		}
		
		//
		return returnValue;
	}
	
	
	/**
	 * html 허용하나, 위험한 문자열은 치환함
	 * @param req
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static String getStringHtmlEncParameter( HttpServletRequest req, String param, String defaultValue ) {

		String returnValue = SU.getStringParameter( req, param, defaultValue );
		
		returnValue = returnValue.replaceAll( "(?i)<script", "" );
		returnValue = returnValue.replaceAll( "(?i)<body", "" );
		returnValue = returnValue.replaceAll( "(?i)<link", "" );
		returnValue = returnValue.replaceAll( "(?i)<style", "" );
		returnValue = returnValue.replaceAll( "(?i)<meta", "" );
		returnValue = returnValue.replaceAll( "(?i)<frameset", "" );
		returnValue = returnValue.replaceAll( "(?i)<frame", "" );
		returnValue = returnValue.replaceAll( "(?i)<base", "" );
		returnValue = returnValue.replaceAll( "(?i)<object", "" );
		returnValue = returnValue.replaceAll( "(?i)<embed", "" );
		returnValue = returnValue.replaceAll( "(?i)<html", "" );
		returnValue = returnValue.replaceAll( "(?i)<applet", "" );
		returnValue = returnValue.replaceAll( "(?i)<iframe", "" );
		
		returnValue = returnValue.replaceAll( "(?i)javascript", "" );
		returnValue = returnValue.replaceAll( "(?i)expression", "" );
		returnValue = returnValue.replaceAll( "(?i)document.cookie", "" );
		
		return returnValue;
	}

	/**
	 * 에러인 경우 에러코드와 메시지를 만들어서 넣어주기 위한 메소드
	 * @param errorCode
	 * @param errorMessage
	 */
	public static String returnError(HttpServletRequest req, Model model, String returnViewName, String errorCode, String errorMessage) {

		//
		Enumeration<String> names = req.getParameterNames();
		while( names.hasMoreElements() ) {
			
			String name = names.nextElement();
			model.addAttribute( name, SU.getStringParameter( req, name, "" ) );
		}
		
		//
		model.addAttribute( "errorCode", errorCode );
		model.addAttribute( "errorMessage", errorMessage );
		
		//
		return returnViewName;
	}
}
