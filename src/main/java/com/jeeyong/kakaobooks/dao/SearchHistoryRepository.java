package com.jeeyong.kakaobooks.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

	@Query("SELECT s FROM SearchHistory s WHERE s.member=?1")
	Page<SearchHistory> findByMember(Member member, Pageable pageable);

}
