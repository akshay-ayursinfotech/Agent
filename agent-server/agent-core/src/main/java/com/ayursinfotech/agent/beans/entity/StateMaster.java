package com.ayursinfotech.agent.beans.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statemaster")
public class StateMaster {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private BigInteger id;
	private String stateName;
	private String stateCode;
	private Integer countryId;
	@Column(columnDefinition = "bigint")
	private BigInteger createdBy;
	@Column(columnDefinition = "bigint")
	private BigInteger modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

	public Integer getCountryId() {
		return countryId;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public BigInteger getId() {
		return id;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getStateCode() {
		return stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
