package com.ejb3inaction.actionbazaar.buslogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Bidder;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Stateless(name = "BidManager")
public class BidManagerBean implements BidManager {
	private Connection connection;

	@Resource(name = "jdbc/ActionBazaarDS", mappedName = "java:/DefaultDS")
	private DataSource dataSource;

	@PostConstruct
	public void initialize() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public Long addBid(Bid bid) {
		Long bidId = getBidId();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO BIDS(" + "BID_ID, " + "BIDDER_ID, "
					+ "ITEM_ID, " + "BID_PRICE" + ") VALUES(" + bidId + ", "
					+ "'" + bid.getBidder().getUserId() + "', "
					+ bid.getItem().getItemId() + ", " + bid.getBidPrice()
					+ ")");
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		return bidId;
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

	public void cancelBid(Bid bid) {
		try {
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM BIDS WHERE BID_ID = "
					+ bid.getBidId());
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	public List<Bid> getBids(Item item) {
		List<Bid> bids = new ArrayList<Bid>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT " + "BID_ID, "
					+ "BIDDER_ID, " + "BID_PRICE, " + "BID_DATE "
					+ "FROM BIDS " + "WHERE ITEM_ID = " + item.getItemId());

			while (resultSet.next()) {
				Bid bid = new Bid();

				bid.setBidId(resultSet.getLong("BID_ID"));

				Bidder bidder = new Bidder();
				bidder.setUserId(resultSet.getString("BIDDER_ID"));
				bid.setBidder(bidder);

				bid.setItem(item);
				bid.setBidPrice(resultSet.getDouble("BID_PRICE"));
				bid.setBidDate(resultSet.getDate("BID_DATE"));

				bids.add(bid);
			}

			resultSet.close();
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		return bids;
	}

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