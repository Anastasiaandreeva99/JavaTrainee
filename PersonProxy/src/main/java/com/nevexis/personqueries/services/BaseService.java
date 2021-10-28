package com.nevexis.personqueries.services;

public interface BaseService<T> {
	public void save(T person) throws Exception;

	public void delete(T person);

	T load(Class<T> cl, Integer id);

}
