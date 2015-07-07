package com.ejb3inaction.actionbazaar.buslogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.interceptor.Interceptors;
import javax.sql.DataSource;

@Interceptors( { ActionBazaarProfilingInterceptor.class })
@Stateless
public class PlaceBidBean implements PlaceBid {
	private DataSource dataSource;

	private Connection connection;

	@Resource
	private SessionContext sc;

	@Resource(name = "jdbc/ActionBazaarDS", mappedName = "java:/DefaultDS")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	@PostConstruct
	public void initialize() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Interceptors(DiscountVerifierInterceptor.class)
	public Long addBid(String userId, Long itemId, Double bidPrice) {
		System.out.println("Bid for " + itemId + " received with price "
				+ bidPrice);
		Long bidId = getBidId();
		createBid(userId, itemId, bidPrice, bidId);
		return bidId;
	}

	private void createBid(String userId, Long itemId, Double bidPrice,
			Long bidId) {
		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO BIDS(" + "BID_ID, " + "BIDDER_ID, "
					+ "ITEM_ID, " + "BID_PRICE) VALUES(" + getBidId() + ", '"
					+ userId + "', " + itemId + ", " + bidPrice + ")");
			TimerService timerService = sc.getTimerService();

			// Create a single event timer that expires after half hour.
			timerService.createTimer(1800000, bidId);
			System.out.println("Bid created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Long getBidId() {
		Long bidId = 0L;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT MAX(BID_ID) FROM BIDS");

			if (resultSet.next()) {
				bidId = resultSet.getLong(1);
			}

			resultSet.close();
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		return bidId++;
	}

	@PreDestroy
	public void cleanup() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Timeout
	public void sendBidInfo(Timer timer) {
		System.out.println("Monitoring status of Bid ID: " + timer.getInfo());

		// Implement your business logic here to monitor the status for bid Id
		// and send email to the bidder.

		return;
	}
}