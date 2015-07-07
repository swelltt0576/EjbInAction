package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jboss.wsf.spi.annotation.WebContext;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;
import com.ejb3inaction.actionbazaar.persistence.eao.BidEAO;
import com.ejb3inaction.actionbazaar.persistence.eao.ItemEAO;

@WebService(name = "PlaceBid", serviceName = "PlaceBidService", targetNamespace = "http://actionbazaar.ejb3inaction.com/webservice")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@WebContext(contextRoot = "/chapter15")
@Stateless(name = "PlaceBid")
public class PlaceBidBean {

	@EJB
	private ItemEAO itemEAO;

	@EJB
	private BidEAO bidEAO;

	@WebMethod
	@WebResult(name = "bidNumber")
	public Long addBid(@WebParam(name = "User")
	String userId, @WebParam(name = "Item")
	Long itemId, @WebParam(name = "Price")
	Double bidPrice) throws BidException {
		System.out.println("Bid for " + itemId + " received with price "
				+ bidPrice);
		Item item = itemEAO.findByItemId(itemId);

		if (item == null) {
			throw new BidException("No such item with ID: " + itemId);
		}

		Bid highBid = itemEAO.findHighestBidForItem(item);

		if (highBid != null) {
			System.out.println("Highest bid ID: " + highBid.getBidId()
					+ ", price: " + highBid.getBidPrice());
			System.out.println("Current bid price: " + bidPrice);

			if (bidPrice <= highBid.getBidPrice()) {
				throw new BidException(
						"Bid Price is lower than the current bid price");
			}
		}

		Bid bid = bidEAO.addBid(userId, item, bidPrice);
		return bid.getBidId();
	}
}