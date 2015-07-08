package org.jboss.as.quickstarts.ejb.remote.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.HelloUser;

public class TestEJB {

	public static void main(String[] args) throws Exception {
		Context context = new InitialContext();
		HelloUser x = (HelloUser) context
				.lookup("ejb/HelloUserBean!com.ejb3inaction.actionbazaar.buslogic.HelloUser");
		x.sayHello("skdfj");
	}
}
