package com.ejb3inaction.actionbazaar.buslogic;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DiscountVerifierInterceptor {
	@AroundInvoke
	public Object giveDiscount(InvocationContext ic) throws Exception {
		System.out.println("*** Discount verifier interceptor invoked for "
				+ ic.getMethod().getName() + " ***");

		if (ic.getMethod().getName().equals("addBid")
				&& (((String) (ic.getContextData().get("MemberStatus")))
						.equals("Gold"))) {
			Object[] params = ic.getParameters();
			params[2] = new Double((Double) params[2] * 0.99);
			System.out
					.println("*** Discount verifier reducing price by 1 percent ***");
			ic.setParameters(params);
		}

		return ic.proceed();
	}
}