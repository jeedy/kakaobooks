package com.jeeyong.kakaobooks.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "search_history")
public class SearchHistory {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String search_word;

	@Column(nullable = false)
	private Timestamp regdate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_account")
	private Member member;

	public SearchHistory() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSearch_word() {
		return search_word;
	}

	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public SearchHistory(String search_word, String isbn, Timestamp regdate, Member member) {
		super();
		this.search_word = search_word;
		this.regdate = regdate;
		this.member = member;
	}

}
