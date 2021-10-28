package com.nevexis.personqueries.services;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.nevexis.personqueries.interceptor.PersonInterceptor;
import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.model.PersonTuple;

import net.sf.cglib.proxy.Enhancer;

public class LockServiceImpl<T> implements BaseService<PersonTuple> {
	static private ReadWriteLock lock = new ReentrantReadWriteLock();
	static private Lock readLock = lock.readLock();
	static private Lock writeLock = lock.writeLock();
	private HashMap<Integer, PersonTuple> people = new HashMap<>();

	private SynchronizationServiceImpl init(Person person) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SynchronizationServiceImpl.class);
		enhancer.setCallbackType(PersonInterceptor.class);
		enhancer.setCallback(new PersonInterceptor());
		SynchronizationServiceImpl proxy = (SynchronizationServiceImpl) enhancer.create();
		return proxy;
	}

	@Override
	public void save(PersonTuple person) throws Exception {
		writeLock.lock();
		SynchronizationServiceImpl proxy = init(person.getPerson());
		proxy.save(person);
		writeLock.unlock();
		System.out.println("lock-service-save");

	}

	@Override
	public void delete(PersonTuple personTuple) {
		writeLock.lock();
		SynchronizationServiceImpl proxy = init(personTuple.getPerson());
		proxy.delete(personTuple);
		writeLock.unlock();
		System.out.println("lock-service-delete");

	}

	@Override
	public PersonTuple load(Class<PersonTuple> cl, Integer id) {
		PersonTuple person = new PersonTuple();
		readLock.lock();
		SynchronizationServiceImpl proxy = init(new Person());
		person = (PersonTuple) proxy.load(cl, id);
		people.put(id, person);
		readLock.unlock();
		System.out.println("lock-service-load");
		return person;
	}

}
