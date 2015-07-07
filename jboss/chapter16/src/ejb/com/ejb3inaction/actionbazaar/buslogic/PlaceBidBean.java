package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class PlaceBidBean implements PlaceBid {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BidService bidService;

	public Long addBid(String userId, Long itemId, Double bidPrice) {
		return bidService.addBid(userId, itemId, bidPrice);
	}
}