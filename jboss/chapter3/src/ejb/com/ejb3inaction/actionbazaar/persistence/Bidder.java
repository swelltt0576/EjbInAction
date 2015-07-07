package com.ejb3inaction.actionbazaar.persistence;

import java.util.Set;

public class Bidder extends User {
	private static final long serialVersionUID = 1L;
	private Long creditRating;
	private Set<Bid> bids;

	public Long getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(Long creditRating) {
		this.creditRating = creditRating;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
}