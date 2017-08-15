package com.mgb.forms;

import java.util.List;
import java.util.Map;

public class PaymentDetailsDTO {
	private int id;
	private String pendingFromMonth;
	private String pendingTillMonth;
	private String userName;
	private String userId;
	private String totalAmountPending;
	private Map<String,String> pendingFromMonthMap;
	private Map<String,String> pendingTillMonthMap;
	private List<PaymentMonthlyDetailsDto> monthDetailsList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPendingFromMonth() {
		return pendingFromMonth;
	}
	public void setPendingFromMonth(String pendingFromMonth) {
		this.pendingFromMonth = pendingFromMonth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPendingTillMonth() {
		return pendingTillMonth;
	}
	public void setPendingTillMonth(String pendingTillMonth) {
		this.pendingTillMonth = pendingTillMonth;
	}
	
	public Map<String, String> getPendingFromMonthMap() {
		return pendingFromMonthMap;
	}
	public void setPendingFromMonthMap(Map<String, String> pendingFromMonthMap) {
		this.pendingFromMonthMap = pendingFromMonthMap;
	}
	public Map<String, String> getPendingTillMonthMap() {
		return pendingTillMonthMap;
	}
	public void setPendingTillMonthMap(Map<String, String> pendingTillMonthMap) {
		this.pendingTillMonthMap = pendingTillMonthMap;
	}
	
	public String getTotalAmountPending() {
		return totalAmountPending;
	}
	public void setTotalAmountPending(String totalAmountPending) {
		this.totalAmountPending = totalAmountPending;
	}
	public List<PaymentMonthlyDetailsDto> getMonthDetailsList() {
		return monthDetailsList;
	}
	public void setMonthDetailsList(List<PaymentMonthlyDetailsDto> monthDetailsList) {
		this.monthDetailsList = monthDetailsList;
	}
	

}
