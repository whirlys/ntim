package com.whirly.model;

import java.io.Serializable;
import java.util.List;

public class Publicer implements Serializable {
	private Integer publicerId;

	private Integer userId;

	private Integer deparmentId;

	private String title;

	private String office;

	private String duty;

	private String email;

	private String introduction;

	private User user;

	private static final long serialVersionUID = 1L;

	public Integer getPublicerId() {
		return publicerId;
	}

	public void setPublicerId(Integer publicerId) {
		this.publicerId = publicerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeparmentId() {
		return deparmentId;
	}

	public void setDeparmentId(Integer deparmentId) {
		this.deparmentId = deparmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office == null ? null : office.trim();
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty == null ? null : duty.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Publicer [publicerId=" + publicerId + ", userId=" + userId + ", deparmentId=" + deparmentId + ", title="
				+ title + ", office=" + office + ", duty=" + duty + ", email=" + email + ", introduction="
				+ introduction + ", user=" + user + ", toString()=" + super.toString() + "]";
	}

}