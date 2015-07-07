package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Long orderId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BIDDER_ID", referencedColumnName = "USER_ID")
	private Bidder bidder;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Item> items;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SHIPPING_ID", referencedColumnName = "SHIPPING_ID")
	private ShippingInfo shippingInfo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BILLING_ID", referencedColumnName = "BILLING_ID")
	private BillingInfo billingInfo;

	@Column(name = "ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Bidder getBidder() {
		return this.bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		getItems().add(item);
	}

	public void remoteItem(Item item) {
		getItems().remove(item);
	}

	public ShippingInfo getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public BillingInfo getBillingInfo() {
		return this.billingInfo;
	}

	public void setBillingInfo(BillingInfo billingInfo) {
		this.billingInfo = billingInfo;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus orderStatus) {
		this.status = orderStatus;
	}
}