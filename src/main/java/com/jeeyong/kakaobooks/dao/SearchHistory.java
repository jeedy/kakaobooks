package com.jeeyong.kakaobooks.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jeeyong.kakaobooks.enums.EnumBookCategory;
import com.jeeyong.kakaobooks.enums.EnumBookTarget;

@Entity
@Table(name = "search_history")
public class SearchHistory {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String search_word;

	@Column
	private String category;

	@Column
	private String target;

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

	public String getCategory() {
		return category;
	}

	public String get_category() {
		return EnumBookCategory.getByCode(this.category).getDesc();
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTarget() {
		return target;
	}

	public String get_target() {
		return EnumBookTarget.getByCode(this.target).getDesc();
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public SearchHistory(String search_word, String target, String category, Timestamp regdate, Member member) {
		super();
		this.search_word = search_word;
		this.target = target;
		this.category = category;
		this.regdate = regdate;
		this.member = member;
	}

}
