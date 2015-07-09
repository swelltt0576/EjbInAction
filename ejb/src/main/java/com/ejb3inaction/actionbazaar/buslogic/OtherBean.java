package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Stateless;

@Stateless
public class OtherBean implements Other {
    public String sayMe() {
	return "other";
    }
}