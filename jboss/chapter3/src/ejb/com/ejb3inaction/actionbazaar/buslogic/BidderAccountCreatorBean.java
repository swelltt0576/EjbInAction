package com.ejb3inaction.actionbazaar.buslogic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;

import com.ejb3inaction.actionbazaar.persistence.BillingInfo;
import com.ejb3inaction.actionbazaar.persistence.BiographicalInfo;
import com.ejb3inaction.actionbazaar.persistence.LoginInfo;

@Stateful(name = "BidderAccountCreator")
public class BidderAccountCreatorBean implements BidderAccountCreator {

	@Resource(name = "jdbc/ActionBazaarDS", mappedName = "java:/DefaultDS")
	private DataSource dataSource;

	private Connection connection;

	private LoginInfo loginInfo;

	private BiographicalInfo biographicalInfo;

	private BillingInfo billingInfo;

	@PostConstruct
	@PostActivate
	public void openConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void addLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public void addBiographicalInfo(BiographicalInfo biographicalInfo)
			throws WorkflowOrderViolationException {
		if (loginInfo == null) {
			throw new WorkflowOrderViolationException(
					"Login info must be set before biographical info");
		}

		this.biographicalInfo = biographicalInfo;
	}

	public void addBillingInfo(BillingInfo billingInfo)
			throws WorkflowOrderViolationException {
		if (biographicalInfo == null) {
			throw new WorkflowOrderViolationException(
					"Biographical info must be set before billing info");
		}

		this.billingInfo = billingInfo;
	}

	@Remove
	public void cancelAccountCreation() {
		loginInfo = null;
		biographicalInfo = null;
		billingInfo = null;
	}

	@Remove
	public void createAccount() {
		try {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO BIDDERS (" + "username, " + "password, "
					+ "first_name, " + "last_name, " + "credit_card_type, "
					+ "account_number, " + "expiry_date" + ") VALUES (" + "'"
					+ loginInfo.getUsername() + "', " + "'"
					+ loginInfo.getPassword() + "', " + "'"
					+ biographicalInfo.getFirstName() + "', " + "'"
					+ biographicalInfo.getLastName() + "', " + "'"
					+ billingInfo.getCreditCardType() + "', " + "'"
					+ billingInfo.getAccountNumber() + "', " + "'"
					+ billingInfo.getExpiryDate() + "'" + ")";
			statement.execute(sql);
			statement.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	@PrePassivate
	@PreDestroy
	public void cleanup() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}