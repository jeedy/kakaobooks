package com.jeeyong.kakaobooks.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jeeyong.kakaobooks.utils.JsonUtils;
import com.jeeyong.kakaobooks.utils.Utils;

@Service
public class ApiService {
	private static final String API_REST_API_KEY="a91743afc6e93942887ba8ec1449b565";
	private static final String API_BOOK_URL="https://dapi.kakao.com/v2/search/book";
	
	public Map<String, Object> searchBooks(String searchWord, String target, String category, int page){
		//
		final String URL = API_BOOK_URL+"?target="+target+"&target="+target+"&category="+category+"&page="+page;
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "KakaoAK " + API_REST_API_KEY);
		Map<String, String> params = new HashMap<>();
		params.put("query", searchWord);
		String jsonString = null;
		Map<String,Object> resultData = null;
		try {
			System.out.println(URL);
			
			jsonString = Utils.getHttpPOST2String(URL, headers, params, false);
			System.out.println(Utils.fmtDate(new Timestamp(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss")+" - get API CardInfo : "+jsonString);
			resultData = JsonUtils.readJsonToStringObjectUnparse(jsonString);
			
		}catch(Exception e) {
			System.out.println(Utils.fmtDate(new Timestamp(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss")+" - "+jsonString);
			e.printStackTrace();
		}
		return resultData;
	}

}
