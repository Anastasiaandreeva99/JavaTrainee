package com.nevexis.personqueries.services;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.nevexis.personqueries.model.Cache;
import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.model.PersonTuple;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory {
	public static PersonTuple enhanceWithCache(PersonTuple originalInput) {
		MethodInterceptor interceptor = new MethodInterceptor() {

			PersonTuple original = originalInput;

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Integer id = original.getPerson().getId();
				switch (method.getName()) {
				case "save":
					Object saved = method.invoke(original, args);
					Cache.remove(id);
					original = new PersonTuple(1, Loader.load(Person.class, id));
					return saved;
				case "delete":
					Object deleted = method.invoke(original, args);
					Cache.remove(original.getPerson().getId());
					return deleted;
				default:
				}

				return method.invoke(original, args);
			}

		};
		System.out.println("intercept-cache");
		return (PersonTuple) Enhancer.create(PersonTuple.class, interceptor);

	}

	public static PersonTuple enhanceWithOptimisticLock(PersonTuple originalInput) {
		MethodInterceptor interceptor = new MethodInterceptor() {

			PersonTuple original = originalInput;

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Integer version = original.getVersion();
				switch (method.getName()) {
				case "save":
					PersonTuple cachedPerson = Cache.getObject(original.getPerson().getId());
					if (null == cachedPerson) {
						Cache.addObject(original.getPerson().getId(), original);
						cachedPerson = original;
					}

					if (!cachedPerson.getVersion().equals(version)) {
						throw new Exception("error in optimistic locking");
					}
					Object result = method.invoke(original, args);
					original.setVersion(version++);
					PersonTuple newCachedPerson = Cache.getObject(original.getPerson().getId());
					newCachedPerson.setVersion(version);
					return result;
				}

				return method.invoke(original, args);
			}

		};
		System.out.println("intercept-optimisticLock");
		return (PersonTuple) Enhancer.create(PersonTuple.class, interceptor);

	}

	public static PersonTuple enhanceWithLock(PersonTuple originalInput) {
		MethodInterceptor interceptor = new MethodInterceptor() {

			PersonTuple original = originalInput;
			ReadWriteLock lock = new ReentrantReadWriteLock();
			Lock readLock = lock.readLock();
			Lock writeLock = lock.writeLock();

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = null;
				switch (method.getName()) {

				case "load":
					try {
						readLock.lock();
						result = method.invoke(original, args);
					} finally {
						readLock.unlock();
					}
					return result;

				}
				try {
					writeLock.lock();
					result = method.invoke(original, args);
				} finally {
					writeLock.unlock();
				}

				return result;
			}

		};
		System.out.println("intercept-lock");
		return (PersonTuple) Enhancer.create(PersonTuple.class, interceptor);
	}

}
