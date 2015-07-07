package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Bid;

@Remote
public interface PlaceBid {
	Bid addBid(Bid bid);
}
