package org.jboss.as.quickstarts.ejb.remote.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ejb3inaction.actionbazaar.buslogic.IManager;

public class ManagerClient {
    private static IManager m;

    public static void main(String[] args) {
	try {
	    Context context = new InitialContext();
	    m = (IManager) context.lookup("ejb/Manager!com.ejb3inaction.actionbazaar.buslogic.IManager");
	    m.test();
	} catch (NamingException e) {
	    e.printStackTrace();
	}
    }
}