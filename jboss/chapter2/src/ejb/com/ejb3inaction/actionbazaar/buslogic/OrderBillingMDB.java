package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.persistence.Order;
import com.ejb3inaction.actionbazaar.persistence.OrderStatus;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/OrderBillingQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class OrderBillingMDB implements MessageListener {
	@PersistenceContext(unitName = "actionBazaar")
	private EntityManager em;

	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Order order = (Order) objectMessage.getObject();

			try {
				bill(order);
				notifyBillingSuccess(order);
				order.setStatus(OrderStatus.COMPLETE);
			} catch (BillingException be) {
				notifyBillingFailure(be, order);
				order.setStatus(OrderStatus.BILLING_FAILED);
			} finally {
				update(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bill(Order order) throws BillingException {
		// Implement billing code here.
		System.out.println("Account number: "
				+ order.getBillingInfo().getAccountNumber() + " charged.");
	}

	private void update(Order order) {
		em.merge(order);
	}

	private void notifyBillingSuccess(Order order) {
		System.out.println("Billing successful.");
	}

	private void notifyBillingFailure(BillingException be, Order order) {
		System.out.println("Billing failed.");
	}
}