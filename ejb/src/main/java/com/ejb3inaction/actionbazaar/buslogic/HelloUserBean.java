package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HelloUserBean implements HelloUser {

    @EJB
    Other other;

    
    private String name;
    
    public void sayHello(String name) {
//	System.out.println("Hello " + name + " welcome to EJB 3 In Action!");
	System.out.println("xxxxxxxxxxxxxxxxx----------"+other.sayMe());
	
	System.out.println(this.name);
	this.name = name;
    }
}