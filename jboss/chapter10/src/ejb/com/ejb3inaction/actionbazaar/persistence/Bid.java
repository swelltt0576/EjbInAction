package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BIDS")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long bidId;
	private String userId;
	private Long itemId;
	private Double bidPrice;
	private Date bidDate;

	public Bid() {
	}

	public Bid(String userId, Long itemId, Double bidPrice) {
		this.itemId = itemId;
		this.userId = userId;
		this.bidPrice = bidPrice;
	}

	@Id
	@Column(name = "BID_ID")
	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	@Column(name = "BIDDER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String user) {
		this.userId = user;
	}

	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "BID_PRICE")
	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Column(name = "BID_DATE")
	@Temporal(TemporalType.DATE)
	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
}