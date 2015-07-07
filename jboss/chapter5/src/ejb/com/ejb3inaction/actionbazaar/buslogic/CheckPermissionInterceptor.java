package com.ejb3inaction.actionbazaar.buslogic;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class CheckPermissionInterceptor {
	@Resource
	private SessionContext ctx;

	@AroundInvoke
	public Object checkUserRole(InvocationContext ic) throws Exception {
		System.out.println("*** Check permission interceptor invoked for "
				+ ic.getTarget() + " ***");

		if (!ctx.isCallerInRole("admin")) {
			throw new SecurityException("User: '"
					+ ctx.getCallerPrincipal().getName()
					+ "' does not have permissions for method "
					+ ic.getMethod());
		}

		return ic.proceed();
	}
}