package com.mgb.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SubscriberDetails {
	private int id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date subscribedDate;
	private String balance="00";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSubscribedDate() {
		return subscribedDate;
	}
	public void setSubscribedDate(Date subscribedDate) {
		this.subscribedDate = subscribedDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}

}
