package com.ayursinfotech.agent.beans.dto;

import java.util.Date;

public class LoginDTO extends BaseDTO {

	private String emailId;
	private String mobileNo;
	private String newpassword;
	private String oldpassword;
	private String password;
	private boolean status;
	private String forgoturl;
	private Integer userId;
	private String sourceType;
	private String sessionId;
	private Date modifiedDate;
	private Long customerId;
	private String customerName;
	private Integer createdBy;

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getForgoturl() {
		return forgoturl;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public String getPassword() {
		return password;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public Integer getUserId() {
		return userId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setForgoturl(String forgoturl) {
		this.forgoturl = forgoturl;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
