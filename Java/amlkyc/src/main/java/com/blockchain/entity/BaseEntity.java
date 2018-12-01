package com.blockchain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
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

	public void setModTs(Date modTs) {
		this.modTs = modTs;
	}
	
	@PrePersist
	public void setCreatedTs() {
		setCrtsTs(new Date());
	}
	
	@PreUpdate
	public void setModfiedTs() {
		setModTs(new Date());
	}
}
