package com.ayursinfotech.agent.beans.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agent")
public class Agent {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private BigInteger id;
	private String status;
	private String statusChangeReason;
	private String title;
	private String userName;
	private String name;
	private String gender;
	private String mobileNo;
	private String email;
	private String password;
	private java.util.Date createdDate;
	private java.util.Date updatedDate;
	@Column(columnDefinition = "bigint")
	private BigInteger createdBy;
	@Column(columnDefinition = "bigint")
	private BigInteger modifiedBy;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", insertable = false, updatable = false)
	private Address address;
	private String verificationCode;

	public Address getAddress() {
		return address;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public BigInteger getId() {
		return id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusChangeReason() {
		return statusChangeReason;
	}

	public String getTitle() {
		return title;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public String getUserName() {
		return userName;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusChangeReason(String statusChangeReason) {
		this.statusChangeReason = statusChangeReason;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isRegisteredAgent(Agent agent) {
		// TODO Auto-generated method stub
		return false;
	}

}
