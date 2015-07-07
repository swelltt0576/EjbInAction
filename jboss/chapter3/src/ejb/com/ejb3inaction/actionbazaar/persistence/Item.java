package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long itemId;
	private String itemName;
	private Double initialPrice;
	private Date bidStartDate;
	private Date bidEndDate;
	private List<Bid> bids;
	private Date createdDate;

	public Item() {
	}

	public Item(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Date getBidStartDate() {
		return bidStartDate;
	}

	public void setBidStartDate(Date bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	public Date getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		return bid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}