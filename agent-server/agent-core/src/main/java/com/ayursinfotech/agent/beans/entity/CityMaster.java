package com.ayursinfotech.agent.beans.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "citymaster")
public class CityMaster {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Integer id;
	private String cityName;
	private String stdCode;
	private Integer stateId;
	@Column(columnDefinition = "bigint")
	private BigInteger createdBy;
	@Column(columnDefinition = "bigint")
	private BigInteger modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

	public String getCityName() {
		return cityName;
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

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Integer getStateId() {
		return stateId;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

}
