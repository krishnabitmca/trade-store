package com.example.trade.store.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//mark class as an Entity 
@Entity
// defining class name as Table name
@Table
public class Trade {

	// mark id as primary key
	@Id
	// defining id as column name
	@Column
	private int id;

	// defining version as column name
	@Column
	private int version;

	// defining counter party id as column name
	@Column
	private String cpID;

	// defining book id as column name
	@Column

	private String bookID;

	// defining maturity date as column name
	@Column
	@Temporal(TemporalType.DATE)
	private Date maturityDate;

	// defining created date as column name
	@Column
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	// defining expired as column name
	@Column
	private boolean expired;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCpID() {
		return cpID;
	}

	public void setCpID(String cpID) {
		this.cpID = cpID;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
