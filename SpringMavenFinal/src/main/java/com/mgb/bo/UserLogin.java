package com.mgb.bo;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.mgb.forms.User;

public class UserLogin {
	private int id;
	@Valid
	private User user;
	@NotEmpty(message="Please enter your password")
	private String password;
	private Boolean isLogedIn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsLogedIn() {
		return isLogedIn;
	}
	public void setIsLogedIn(Boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}
	

}
