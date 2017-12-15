package com.jeeyong.kakaobooks.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	@Query("SELECT b FROM Bookmark b WHERE b.member=?1 and b.isbn=?2")
	List<Bookmark> findByMemberAccountAndIsbn(Member member, String isbn, Pageable pageable);

	default Bookmark findOneByMemberAccountAndIsbn(Member member, String isbn) {
		List<Bookmark> boomarks = findByMemberAccountAndIsbn(member, isbn, new PageRequest(0, 1));
		if (boomarks != null && boomarks.size() > 0) {
			return boomarks.get(0);
		}
		return null;
	}

	@Query("SELECT b FROM Bookmark b WHERE b.member=?1")
	Page<Bookmark> findByMember(Member member, Pageable paeable);

}
