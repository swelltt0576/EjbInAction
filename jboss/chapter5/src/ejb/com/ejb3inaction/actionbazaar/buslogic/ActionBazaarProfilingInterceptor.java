package com.ejb3inaction.actionbazaar.buslogic;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ActionBazaarProfilingInterceptor {

	@AroundInvoke
	public Object profile(InvocationContext ic) throws Exception {
		System.out
				.println("*** ActionBazaar profiling interceptor invoked for "
						+ ic.getTarget() + " ***");
		long startTime = 0;
		long endTime = 0;

		try {
			startTime = System.currentTimeMillis();
			ic.getContextData().put("MemberStatus", "Gold");
			return ic.proceed();
		} finally {
			endTime = System.currentTimeMillis();
			System.out.println("*** Method " + ic.getMethod() + " executed in "
					+ (endTime - startTime) + "ms ***");
		}
	}
}