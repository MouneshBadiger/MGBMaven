package com.mgb.bo;

import java.util.Date;

import com.mgb.forms.User;

public class PaymentDetails {
private int id;
private User userId;
private Integer amountPaid;
private Date paymentDate;
private PaymentDefinition paymentDefinition;
private Boolean isActive;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public User getUserId() {
	return userId;
}
public void setUserId(User userId) {
	this.userId = userId;
}

public Integer getAmountPaid() {
	return amountPaid;
}
public void setAmountPaid(Integer amountPaid) {
	this.amountPaid = amountPaid;
}
public Date getPaymentDate() {
	return paymentDate;
}
public void setPaymentDate(Date paymentDate) {
	this.paymentDate = paymentDate;
}
public PaymentDefinition getPaymentDefinition() {
	return paymentDefinition;
}
public void setPaymentDefinition(PaymentDefinition paymentDefinition) {
	this.paymentDefinition = paymentDefinition;
}
public Boolean getIsActive() {
	return isActive;
}
public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}



}
