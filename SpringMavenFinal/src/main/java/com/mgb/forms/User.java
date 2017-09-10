package com.mgb.forms;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.mgb.bo.SubscriberDetails;
import com.mgb.bo.UserLogin;


public class User {
	private int id;
	@NotEmpty(message="You should have a name. Please enter it.")
	private String name;
	@NotEmpty(message="Email id can not be empty")
	@Email(message="Invalid email id")
	private String email;
	@NotEmpty(message="Mobile number can not be empty")
	@Pattern(regexp="(^$|[0-9]{10})",message="Enter valid mobile number")
	private String mobileNo;
	@NotEmpty(message="Address can not be empty")
	private String address;
	@NotEmpty(message="Gender can not be empty")
	private String gender;
	//@NotEmpty(message="Date of birth can not be empty")
	//@DateTimeFormat(pattern="dd/MM/yyyy") 
	private Date dob;
	@NotEmpty(message="Please enter your password")
	private String password;
	@NotEmpty(message="Re-enter the password")
	private String rePassword;
	@Valid
	private UserLogin userLogin;
	//feilds used for reference
	private String createdBy="developer";
	private Date createdDate=new Date();
	private String modifiedBy="developer";
	private Date modifiedDate=new Date();
	private Boolean isActive=true;
	private String role;
	private String pendingAmount="00";
	private List<String> testList;
	private SubscriberDetails subscriberDetails;
	public User() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserLogin getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getTestList() {
		return testList;
	}
	public void setTestList(List<String> testList) {
		this.testList = testList;
	}
	public String getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}
	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}
	
	
	
	
}
