package com.mgb.forms;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.mgb.bo.AreaMaster;
import com.mgb.bo.SubscriberDetails;

public class Subscriber {
	private int id;
	@NotEmpty(message="Please enter subscriber name.")
	private String name;
	//@NotEmpty(message="Email id can not be empty")
	//@Email(message="Invalid email id")
	private String email;
	@NotEmpty(message="Mobile number can not be empty")
	@Pattern(regexp="(^$|[0-9]{10})",message="Enter valid mobile number")
	private String mobileNo;
	@NotEmpty(message="Address can not be empty")
	private String address;
	@NotEmpty(message="Gender can not be empty")
	private String gender;
	//@NotEmpty(message="Date of birth can not be empty")
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	private Date dob;
	private String adarCardNo;
	private String role;
	//feilds used for reference
	private String createdBy;
	private Date createdDate=new Date();
	private String modifiedBy;
	private Date modifiedDate=new Date();
	private Boolean isActive=true;
	@Valid
	private SubscriberDetails subscriberDetails;
	private AreaMaster areaId;
	@NotEmpty(message="Please select Area")
	private String areaStrId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAdarCardNo() {
		return adarCardNo;
	}
	public void setAdarCardNo(String adarCardNo) {
		this.adarCardNo = adarCardNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}
	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}
	public AreaMaster getAreaId() {
		return areaId;
	}
	public void setAreaId(AreaMaster areaId) {
		this.areaId = areaId;
	}
	public String getAreaStrId() {
		return areaStrId;
	}
	public void setAreaStrId(String areaStrId) {
		this.areaStrId = areaStrId;
	}
	
	

}
