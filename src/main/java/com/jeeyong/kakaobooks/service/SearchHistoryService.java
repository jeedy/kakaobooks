package com.jeeyong.kakaobooks.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.dao.SearchHistory;
import com.jeeyong.kakaobooks.dao.SearchHistoryRepository;

@Service
public class SearchHistoryService {
	@Autowired
	SearchHistoryRepository seaerchHistoryRepository;

	@Transactional
	public void save(SearchHistory entity) {
		seaerchHistoryRepository.save(entity);
	}

	public Page<SearchHistory> findByMember(Member member, Pageable pageable) {
		//
		return seaerchHistoryRepository.findByMember(member, pageable);
	}

}
