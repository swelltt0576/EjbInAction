package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Remote;

@Remote
public interface HelloUser {
	public void sayHello(String name);
}