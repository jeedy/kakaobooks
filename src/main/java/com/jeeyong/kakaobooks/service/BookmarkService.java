package com.jeeyong.kakaobooks.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jeeyong.kakaobooks.controller.AjaxController;
import com.jeeyong.kakaobooks.dao.Bookmark;
import com.jeeyong.kakaobooks.dao.BookmarkRepository;
import com.jeeyong.kakaobooks.dao.Member;
import com.jeeyong.kakaobooks.dao.MemberRepository;

@Service
public class BookmarkService {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BookmarkRepository bookmarkRepository;

	@Autowired
	ApiService apiService;

	@Transactional
	public Bookmark getBookmark(Member member, String isbn) {
		return bookmarkRepository.findOneByMemberAccountAndIsbn(member, isbn);
	}

	@Transactional
	public boolean addBookmark(Member member, String isbn) {
		Map<String, Object> map_book = apiService.getBookByISBN(isbn);
		Bookmark check = this.getBookmark(member, isbn);

		if (check != null) {
			return false;
		}

		Bookmark bookmark = new Bookmark();
		bookmark.setTitle(map_book.get("title").toString());
		bookmark.setMember(member);
		bookmark.setIsbn(isbn);
		bookmark.setRegdate(Timestamp.valueOf(LocalDateTime.now()));

		bookmarkRepository.save(bookmark);

		return true;
	}

	@Transactional
	public boolean deleteBookmark(Member member, String isbn) {
		Bookmark bookmark = this.getBookmark(member, isbn);
		if (bookmark != null) {
			bookmarkRepository.delete(bookmark);
			return true;
		}
		return false;
	}

	@Transactional
	public Page<Bookmark> findByMember(Member member, Pageable pageable) {
		//
		return bookmarkRepository.findByMember(member, pageable);
	}

}
