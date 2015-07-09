package com.ejb3inaction.actionbazaar.buslogic;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@Remote(value = IManager.class)
public class Manager implements IManager {
    @Resource
    SessionContext sessionContext;

    /*
     * (non-Javadoc)
     * 
     * @see com.ejb3inaction.actionbazaar.buslogic.IManager#test()
     */
    public void test() {

	System.out.println("test======================start");
	HelloUser hu = (HelloUser) sessionContext
		.lookup("java:global/ejb/HelloUserBean!com.ejb3inaction.actionbazaar.buslogic.HelloUser");
	System.out.println("test======================01");
	hu.sayHello("manager: ");
    }

}
