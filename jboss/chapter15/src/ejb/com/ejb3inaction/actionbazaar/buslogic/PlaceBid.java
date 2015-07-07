package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Remote;

@Remote
public interface PlaceBid {
	Long addBid(String userId, Long itemId, Double bidPrice);
}