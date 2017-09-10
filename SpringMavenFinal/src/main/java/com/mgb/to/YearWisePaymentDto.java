package com.mgb.to;

import java.util.List;

import com.mgb.forms.PaymentMonthlyDetailsDto;

public class YearWisePaymentDto {
	private int id;
	private String subscriberName;
	private String subscribedDate;
	private String address;
	private String phoneNo;
	private String preBalance;
	private List<PaymentMonthlyDetailsDto>   monthList;
	private String totalBalance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public List<PaymentMonthlyDetailsDto> getMonthList() {
		return monthList;
	}
	public void setMonthList(List<PaymentMonthlyDetailsDto> monthList) {
		this.monthList = monthList;
	}
	public String getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(String preBalance) {
		this.preBalance = preBalance;
	}
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}
	public String getSubscribedDate() {
		return subscribedDate;
	}
	public void setSubscribedDate(String subscribedDate) {
		this.subscribedDate = subscribedDate;
	}
	

}
