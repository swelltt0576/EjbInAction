package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ejb3inaction.actionbazaar.buslogic.HelloUser;
import com.ejb3inaction.actionbazaar.buslogic.HelloUserBean;

public class HelloUserClient {
	private static HelloUser helloUser;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			helloUser = (HelloUser) context.lookup("chapter1/"
					+ HelloUserBean.class.getSimpleName() + "/remote");
			helloUser.sayHello("Curious George");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}