package com.jeeyong.kakaobooks.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;
import com.jeeyong.kakaobooks.utils.JsonUtils;
import com.jeeyong.kakaobooks.utils.Utils;

@Service
public class ApiService {
	private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

	private static final String API_REST_API_KEY = "a91743afc6e93942887ba8ec1449b565";
	private static final String API_BOOK_URL = "https://dapi.kakao.com/v2/search/book";

	public Map<String, Object> searchBooks(String searchWord, String target, String category, int page) {
		//
		final String URL = API_BOOK_URL + "?target=" + target + "&target=" + target + "&category=" + category + "&page="
				+ page;
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "KakaoAK " + API_REST_API_KEY);
		Map<String, String> params = new HashMap<>();
		params.put("query", searchWord);
		String jsonString = null;
		Map<String, Object> resultData = null;
		try {
			logger.debug(URL);

			jsonString = Utils.getHttpPOST2String(URL, headers, params, false);
			logger.debug("get API Info : " + jsonString);
			resultData = JsonUtils.readJsonToStringObjectUnparse(jsonString);

		} catch (Exception e) {
			logger.info("get API Exception : " + jsonString);
			e.printStackTrace();
		}
		return resultData;
	}

	/**
	 * 책 정보 가져오기
	 * 
	 * 데이터 참조 :
	 * https://developers.kakao.com/docs/restapi/search#%EC%B1%85-%EA%B2%80%EC%83%89
	 * 
	 * @param ISBN
	 * @return JSON -> Map<String,Object>
	 */
	public Map<String, Object> getBookByISBN(String ISBN) {
		Map<String, Object> book = null;
		Map<String, Object> json = this.searchBooks(ISBN, EnumBookTarget.전체.getCode(), EnumBookCategory.전체.getCode(),
				1);
		int cnt = (Integer) ((Map) json.get("meta")).get("total_count");
		logger.info("cnt=" + cnt);
		if (cnt > 0) {
			book = (Map) ((List) json.get("documents")).get(0);
		}
		return book;
	}

}
