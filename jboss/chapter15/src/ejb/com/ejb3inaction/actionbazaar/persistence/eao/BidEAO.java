package com.ejb3inaction.actionbazaar.persistence.eao;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Remote
public interface BidEAO {
	public Bid addBid(String bidderId, Item item, double bidPrice);

	public Bid cancelBid(Long bidId);
}