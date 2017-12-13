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
@Table(name = "bookmark")
public class Bookmark {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Timestamp regdate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_account")
	private Member member;

	public Bookmark() {
		super();

	}

	public Bookmark(Long id, Timestamp regdate, Member member) {
		super();
		this.id = id;
		this.regdate = regdate;
		this.member = member;
	}

}
