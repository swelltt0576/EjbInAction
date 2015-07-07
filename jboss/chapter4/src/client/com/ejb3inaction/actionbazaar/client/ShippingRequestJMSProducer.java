package com.ejb3inaction.actionbazaar.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.ShippingRequest;

public class ShippingRequestJMSProducer {

	public static void main(String[] args) {
		Destination destination;
		ConnectionFactory connectionFactory;

		long item = 10101;
		String address = "101 Easy Street";
		String method = "Snail mail";
		double amount = 101.00;

		try {
			Context context = new InitialContext();

			destination = (Destination) context
					.lookup("jms/ShippingRequestQueue");

			connectionFactory = (ConnectionFactory) context
					.lookup("ConnectionFactory");

			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(destination);

			ObjectMessage message = session.createObjectMessage();
			ShippingRequest shippingRequest = new ShippingRequest();
			shippingRequest.setItem(item);
			shippingRequest.setShippingAddress(address);
			shippingRequest.setShippingMethod(method);
			shippingRequest.setInsuranceAmount(amount);
			message.setObject(shippingRequest);
			producer.send(message);
			session.close();
			connection.close();
			System.out.println("Shipping request message sent...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}