package com.jeeyong.kakaobooks.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "member")
public class Member {

	@Id
	private String account;

	@JsonIgnore
	@Column(nullable = false)
	private String pwd;

	@Column(nullable = false)
	private Timestamp regdate;

	public Member() {
		super();
	}

	public Member(String account, String pwd, Timestamp regdate) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.regdate = regdate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

}
