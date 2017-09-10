package com.mgb.forms;

public class PaymentMonthlyDetailsDto {
	private int id;
	private String paymentMonth;
	private String paymentMadeDate;
	private String amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public String getPaymentMadeDate() {
		return paymentMadeDate;
	}
	public void setPaymentMadeDate(String paymentMadeDate) {
		this.paymentMadeDate = paymentMadeDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

}
