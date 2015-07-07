package com.ejb3inaction.actionbazaar.client;

import com.ejb3inaction.actionbazaar.webservice.PlaceBid;
import com.ejb3inaction.actionbazaar.webservice.PlaceBidService;

public class PlaceBidClient {
	public static void main(String[] args) {
		try {
			PlaceBidService placeBidService = new PlaceBidService();
			PlaceBid placeBid = placeBidService.getPlaceBidPort();

			System.out.println("Bid price sent to the bean is: " + 200000.5);
			System.out.println("Bid successful, bid ID received is: "
					+ placeBid.addBid("idiot", 1L, 2000005.50));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}