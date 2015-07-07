package com.ejb3inaction.actionbazaar.buslogic;

import java.util.List;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Remote
public interface BidManager {
	Long addBid(Bid bid);

	void cancelBid(Bid bid);

	List<Bid> getBids(Item item);
}