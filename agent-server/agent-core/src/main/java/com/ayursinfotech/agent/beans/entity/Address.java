package com.ayursinfotech.agent.beans.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private BigInteger id;
	private String addressType;
	private String address1;
	private String address2;
	private String location;
	private String landmark;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pincode")
	private PincodeMaster pincodeMaster;
	@Column(columnDefinition = "bigint")
	private BigInteger createdBy;
	@Column(columnDefinition = "bigint")
	private BigInteger modifiedBy;

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getAddressType() {
		return addressType;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public BigInteger getId() {
		return id;
	}

	public String getLandmark() {
		return landmark;
	}

	public String getLocation() {
		return location;
	}

	public BigInteger getModifiedBy() {
		return modifiedBy;
	}

	public PincodeMaster getPincodeMaster() {
		return pincodeMaster;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setPincodeMaster(PincodeMaster pincodeMaster) {
		this.pincodeMaster = pincodeMaster;
	}

}
