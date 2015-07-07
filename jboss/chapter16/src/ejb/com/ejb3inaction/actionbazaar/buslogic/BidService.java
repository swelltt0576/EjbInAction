package com.ejb3inaction.actionbazaar.buslogic;

public interface BidService {
	Long addBid(String userId, Long itemId, Double bidPrice);
}