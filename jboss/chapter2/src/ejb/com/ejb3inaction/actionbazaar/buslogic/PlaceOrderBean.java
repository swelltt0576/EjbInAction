package com.ejb3inaction.actionbazaar.buslogic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.persistence.BillingInfo;
import com.ejb3inaction.actionbazaar.persistence.Order;
import com.ejb3inaction.actionbazaar.persistence.ShippingInfo;

@Stateful
public class PlaceOrderBean implements PlaceOrder {
	@PersistenceContext(unitName = "actionBazaar")
	private EntityManager em;

	@Resource(name = "jms/QueueConnectionFactory", mappedName = "ConnectionFactory")
	private QueueConnectionFactory connectionFactory;

	@Resource(name = "jms/OrderBillingQueue", mappedName = "jms/OrderBillingQueue")
	private Queue billingQueue;

	private Long bidderId;

	private List<Long> items;

	private ShippingInfo shippingInfo;

	private BillingInfo billingInfo;

	public PlaceOrderBean() {
		items = new ArrayList<Long>();
	}

	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}

	public void addItem(Long itemId) {
		items.add(itemId);
	}

	public void setShippingInfo(ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public void setBillingInfo(BillingInfo billingInfo) {
		this.billingInfo = billingInfo;
	}

	private void saveOrder(Order order) {
		em.persist(order);
	}

	private void billOrder(Order order) {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(billingQueue);
			ObjectMessage message = session.createObjectMessage();
			message.setObject(order);
			producer.send(message);
			producer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Remove
	public Long confirmOrder() {
		Order order = new Order();
		order.setBidderId(bidderId);
		order.setItems(items);
		order.setShippingInfo(shippingInfo);
		order.setBillingInfo(billingInfo);
		saveOrder(order);
		billOrder(order);
		return order.getOrderId();
	}
}