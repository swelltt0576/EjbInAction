package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long bidId;
	private Bidder bidder;
	private Item item;
	private Double bidPrice;
	private Date bidDate;

	public Bid() {
	}

	public Bid(Bidder bidder, Item item, Double bidPrice) {
		this.bidder = bidder;
		this.item = item;
		this.bidPrice = bidPrice;
	}

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
}