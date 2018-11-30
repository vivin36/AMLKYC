package com.blockchain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the remittances database table.
 * 
 */
@Entity
@Table(name="remittances")
public class Remittance extends BaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String comments;
	
	private String description;
	
	private String receiverAccountNumber;
	private String senderAccountNumber;
	private String status;

	public Remittance() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="COMMENTS")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="RECEIVER_ACCOUNT_NUMBER")
	public String getReceiverAccountNumber() {
		return this.receiverAccountNumber;
	}

	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	@Column(name="SENDER_ACCOUNT_NUMBER")
	public String getSenderAccountNumber() {
		return this.senderAccountNumber;
	}

	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}