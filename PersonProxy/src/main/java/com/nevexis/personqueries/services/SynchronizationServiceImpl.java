package com.nevexis.personqueries.services;

import java.util.HashMap;

import com.nevexis.personqueries.interceptor.PersonInterceptor;
import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.model.PersonTuple;

import net.sf.cglib.proxy.Enhancer;

public class SynchronizationServiceImpl implements BaseService<PersonTuple> {
	private HashMap<Integer, PersonTuple> people = new HashMap<>();

	private CacheServiceImpl init(Person person) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CacheServiceImpl.class);
		enhancer.setCallbackType(PersonInterceptor.class);
		enhancer.setCallback(new PersonInterceptor());
		CacheServiceImpl proxy = (CacheServiceImpl) enhancer.create();
		return proxy;
	}

	@Override
	public PersonTuple load(Class<PersonTuple> cl, Integer id) {
		PersonTuple person = new PersonTuple();
		CacheServiceImpl proxy = init(new Person());
		person = proxy.load(cl, id);
		people.put(id, person);
		System.out.println("sync-service-load");
		return person;
	}

	@Override
	public void save(PersonTuple personTuple) throws Exception {
		CacheServiceImpl proxy = init(personTuple.getPerson());
		proxy.save(personTuple);
		System.out.println("sync-service-save");
	}

	@Override
	public void delete(PersonTuple personTuple) {
		CacheServiceImpl proxy = init(personTuple.getPerson());
		proxy.delete(personTuple);
		System.out.println("sync-service-delete");

	}

}
