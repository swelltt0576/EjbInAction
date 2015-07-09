package com.ejb3inaction.actionbazaar.client;


import javax.jms.*;
import javax.naming.NamingException;

public class QueueSender {
    javax.naming.Context ic = null;
    javax.jms.QueueConnectionFactory cf = null;
    javax.jms.QueueConnection connection = null;
    javax.jms.Queue queue = null;
    javax.jms.QueueSession session = null;

    String destinationName = "queue/DLQ";

    public static void main(final String[] args) {
	QueueSender qproducer = new QueueSender();
	try {
	    qproducer.getInitialContext();
	    qproducer.connectAndCreateSession();
	    qproducer.produce();
	    qproducer.closeConnection();
	} catch (NamingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (JMSException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    void getInitialContext() throws NamingException {

	java.util.Properties p = new java.util.Properties();

	p.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
		"org.jboss.naming.remote.client.InitialContextFactory");
	// p.put(javax.naming.Context.URL_PKG_PREFIXES,
	// "org.jboss.naming:org.jnp.interfaces");
	// p.put(javax.naming.Context.PROVIDER_URL, "jnp://localhost:1099");
	p.put(javax.naming.Context.PROVIDER_URL,
		"http-remoting://localhost:8080");

	ic = new javax.naming.InitialContext(p);

    }

    void connectAndCreateSession() throws NamingException, JMSException {
//	cf = (javax.jms.QueueConnectionFactory) ic.lookup("/ConnectionFactory");
	queue = (Queue) ic.lookup(destinationName);
//	connection = cf.createQueueConnection();
//	session = connection.createQueueSession(true, 0);
	connection.start();
    }

    public void closeConnection() {
	if (session != null) {
	    try {
		session.close();
	    } catch (JMSException e) {
		e.printStackTrace();
	    }
	}

	if (connection != null) {
	    try {
		connection.close();
	    } catch (JMSException e) {
		e.printStackTrace();
	    }
	}

    }

    public void produce() {
	try {
	    MessageProducer qproducer = session.createProducer(queue);
	    for (int i = 0; i < 5000; i++) {
		TextMessage message = session
			.createTextMessage("This is a text message from queuesender ....."
				+ i);

		// this log is getting printed everytime
		System.out.println("Sent message from queuesender : "
			+ message.getText());

		// at this line current thread gets blocked after sendinf some
		// 4000 messages
		qproducer.send(message);

		// This log is not getting printed after sending approx 4000
		// messages
		System.out.println("JMS message Id is -->"
			+ message.getJMSMessageID());
		session.commit();

	    }

	} catch (JMSException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
