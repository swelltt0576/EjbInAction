package com.ejb3inaction.actionbazaar.web;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejb3inaction.actionbazaar.buslogic.PlaceBid;
import com.ejb3inaction.actionbazaar.buslogic.PlaceBidBean;

public class PlaceBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceBid placeBid;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Context context = new InitialContext();
			placeBid = (PlaceBid) context.lookup("chapter16/"
					+ PlaceBidBean.class.getSimpleName() + "/remote");
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long itemId;
		String userId = "";
		Double bidPrice = new Double(0);

		itemId = Long.valueOf(request.getParameter("itemId"));

		userId = request.getParameter("userId");
		bidPrice = Double.valueOf(request.getParameter("bidPrice"));

		try {
			System.out.println("Item ID: " + itemId);
			System.out.println("User ID: " + userId);
			System.out.println("Bid price: " + bidPrice);

			Long bidId = placeBid.addBid(userId, itemId, bidPrice);

			System.out.println("Bid persisted successfully. Bid ID: " + bidId);
			request.setAttribute("bidId", bidId);

			this.getServletContext().getRequestDispatcher("/jsp/success.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/jsp/error.jsp")
					.forward(request, response);
		}
	}
}