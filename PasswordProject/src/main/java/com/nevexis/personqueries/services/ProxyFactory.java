package com.nevexis.personqueries.services;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory {
	private static Logger logger = Logger.getLogger(ProxyFactory.class.getName());
	private static Pattern pattern = Pattern
			.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}");

	public static LoginService enchanceWithExpire(LoginService original) {
		MethodInterceptor interceptor = new MethodInterceptor() {
			LoginService original1 = original;

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if (method.getName().equals("login") && Utils.isExpired((String) args[0])) {
					logger.info("interceptor for expire");
					throw new IllegalArgumentException();
				}
				return method.invoke(original1, args);
			}
		};
		return (LoginService) Enhancer.create(LoginService.class, interceptor);
	}

	public static LoginService enchanceWithHash(LoginService original) {
		MethodInterceptor interceptor = new MethodInterceptor() {
			LoginService original1 = original;

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				logger.info("interceptor for hash");
				args[1] = Utils.hash((String) args[1]);
				if (args.length == 3) {
					args[2] = Utils.hash((String) args[2]);
				}
				return method.invoke(original1, args);
			}
		};
		return (LoginService) Enhancer.create(LoginService.class, interceptor);
	}

	public static LoginService enchanceWithValidation(LoginService original) {
		MethodInterceptor interceptor = new MethodInterceptor() {
			LoginService original1 = original;

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if (method.getName().equals("changePassword")) {
					logger.info("interceptor for validation");

					Matcher matcher = pattern.matcher((String) args[2]);
					if (!matcher.find()) {
						throw new IllegalArgumentException("Password is not valid");
					}
				}
				return method.invoke(original1, args);
			}
		};
		return (LoginService) Enhancer.create(LoginService.class, interceptor);
	}

	public static LoginService enchanceWithHistory(LoginService original) {
		MethodInterceptor interceptor = new MethodInterceptor() {
			LoginService original1 = original;

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if (method.getName().equals("changePassword")) {
					logger.info("interceptor for history");
					if (!Utils.isValidInHistory((String) args[0], (String) args[2])) {
						throw new IllegalArgumentException("Password is used in the last month");
					}
				}
				return method.invoke(original1, args);
			}
		};
		return (LoginService) Enhancer.create(LoginService.class, interceptor);
	}

}
