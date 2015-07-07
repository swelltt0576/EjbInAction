package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.BillingInfo;
import com.ejb3inaction.actionbazaar.persistence.BiographicalInfo;
import com.ejb3inaction.actionbazaar.persistence.LoginInfo;

@Remote
public interface BidderAccountCreator {
	void addLoginInfo(LoginInfo loginInfo);

	void addBiographicalInfo(BiographicalInfo biographicalInfo)
			throws WorkflowOrderViolationException;

	void addBillingInfo(BillingInfo billingInfo)
			throws WorkflowOrderViolationException;

	void cancelAccountCreation();

	void createAccount();
}