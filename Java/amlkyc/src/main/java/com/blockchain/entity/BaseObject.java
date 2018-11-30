package com.blockchain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class BaseObject {

	private String crtBy;
	private Date crtsTs;
	private String modBy;
	private Date modTs;
	
	@Column(name="CRT_BY")
	public String getCrtBy() {
		return this.crtBy;
	}

	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRTS_TS")
	public Date getCrtsTs() {
		return this.crtsTs;
	}

	@PrePersist
	public void setCrtsTs(Date crtsTs) {
		this.crtsTs = crtsTs;
	}
	
	@Column(name="MOD_BY")
	public String getModBy() {
		return this.modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MOD_TS")
	public Date getModTs() {
		return this.modTs;
	}

	@PreUpdate
	public void setModTs(Date modTs) {
		this.modTs = modTs;
	}
}
