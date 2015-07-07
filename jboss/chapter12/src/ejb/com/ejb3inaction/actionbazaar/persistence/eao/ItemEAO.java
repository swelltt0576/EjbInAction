package com.ejb3inaction.actionbazaar.persistence.eao;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Remote
public interface ItemEAO {
	public Item addItem(Item item);

	public Item findByItemId(Long itemId);

	public Bid findHighestBidForItem(Item item);
}