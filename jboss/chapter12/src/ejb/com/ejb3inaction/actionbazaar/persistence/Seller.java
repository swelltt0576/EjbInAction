package com.ejb3inaction.actionbazaar.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "S")
public class Seller extends User {
	private static final long serialVersionUID = 1L;
	private Double commissionRate;
	private Long maxItemsAllowed;
	private Set<Item> items;

	@Column(name = "COMMISSION_RATE")
	public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

	@Column(name = "MAX_ITEMS")
	public Long getMaxItemsAllowed() {
		return maxItemsAllowed;
	}

	public void setMaxItemsAllowed(Long maxItemsAllowed) {
		this.maxItemsAllowed = maxItemsAllowed;
	}

	@OneToMany(mappedBy = "seller")
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setSeller(this);
		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setSeller(null);
		return item;
	}
}