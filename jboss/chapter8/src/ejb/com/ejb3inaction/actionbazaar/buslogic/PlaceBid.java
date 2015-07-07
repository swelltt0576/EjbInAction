package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Bid;

@Remote
public interface PlaceBid {
	public Bid addBid(String bidderId, Long itemId, Double bidPrice);
}