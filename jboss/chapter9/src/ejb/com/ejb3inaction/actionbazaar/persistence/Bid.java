package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BIDS")
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
		this.item = item;
		this.bidder = bidder;
		this.bidPrice = bidPrice;
	}

	@SequenceGenerator(name = "BID_SEQUENCE_GENERATOR", sequenceName = "BID_SEQUENCE", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BID_SEQUENCE_GENERATOR")
	@Column(name = "BID_ID")
	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	@ManyToOne
	@JoinColumn(name = "BIDDER_ID", referencedColumnName = "USER_ID")
	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	@ManyToOne
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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