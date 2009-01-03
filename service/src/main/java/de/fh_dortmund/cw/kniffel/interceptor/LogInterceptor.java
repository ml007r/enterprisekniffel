package de.fh_dortmund.cw.kniffel.interceptor;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * 
 * @author tbs
 * 
 */
public class LogInterceptor {

	@AroundInvoke
	public Object log(InvocationContext invocation) throws Exception {
		long startTime = System.currentTimeMillis();

		Logger log = Logger.getLogger(invocation.getTarget().getClass()
				.getName());
		String methodName = invocation.getMethod().getName();
		String paramString = "";
		if (invocation.getParameters() != null) {
			for (int i = 0; i < invocation.getParameters().length; i++) {
				Object param = invocation.getParameters()[i];
				if (!paramString.equals("")) {
					paramString += ", ";
				}
				paramString += param.toString();
			}
		}
		long threadId = Thread.currentThread().getId();
		String prefix = "[Thread:" + threadId + "] [" + methodName + "("
				+ paramString + ")]: ";
		log.info(prefix + "start");
		Object result = null;
		try {
			result = invocation.proceed();
			return result;
		} finally {
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			log.info(prefix + " result: " + result + " (" + duration + " ms).");
		}
	}
}
