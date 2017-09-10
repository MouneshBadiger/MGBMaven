package com.mgb.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class PaymentDefDTO {
	private int id;
	@NotEmpty(message="Month not be empty")
	private String defMonth;
	@NotEmpty(message="Year can not be empty")
	private String defYear;
	@NotEmpty(message="Amount can not be empty")
	private String amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDefMonth() {
		return defMonth;
	}
	public void setDefMonth(String defMonth) {
		this.defMonth = defMonth;
	}
	public String getDefYear() {
		return defYear;
	}
	public void setDefYear(String defYear) {
		this.defYear = defYear;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	

}
