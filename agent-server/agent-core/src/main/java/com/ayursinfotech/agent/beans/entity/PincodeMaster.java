package com.ayursinfotech.agent.beans.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pincodemaster")
public class PincodeMaster {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private BigInteger id;
	private String pincodeNumber;
	private Date createdDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId")
	private CityMaster city;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stateId")
	private StateMaster state;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId")
	private CountryMaster country;

	@Column(columnDefinition = "bigint")
	private BigInteger createdBy;
	@Column(columnDefinition = "bigint")
	private BigInteger modifiedBy;

	public CityMaster getCity() {
		return city;
	}

	public CountryMaster getCountry() {
		return country;
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

	public String getPincodeNumber() {
		return pincodeNumber;
	}

	public StateMaster getState() {
		return state;
	}

	public void setCity(CityMaster city) {
		this.city = city;
	}

	public void setCountry(CountryMaster country) {
		this.country = country;
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

	public void setPincodeNumber(String pincodeNumber) {
		this.pincodeNumber = pincodeNumber;
	}

	public void setState(StateMaster state) {
		this.state = state;
	}

}
