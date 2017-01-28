package com.ayursinfotech.agent.beans.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countrymaster")
public class CountryMaster {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Integer id;
	private String countryName;
	private String countryCode;
	private Integer isdCode;
	@Column(columnDefinition = "bigint")
	private BigInteger createdBy;
	@Column(columnDefinition = "bigint")
	private BigInteger modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIsdCode() {
		return isdCode;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsdCode(Integer isdCode) {
		this.isdCode = isdCode;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
