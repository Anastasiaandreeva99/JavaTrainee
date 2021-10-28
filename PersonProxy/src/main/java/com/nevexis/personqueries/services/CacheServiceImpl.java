
package com.nevexis.personqueries.services;


import com.nevexis.personqueries.model.Cache;
import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.model.PersonTuple;

public class CacheServiceImpl implements BaseService<PersonTuple> {

	@Override
	public void save(PersonTuple tupleToSafe) throws Exception {
		
		PersonTuple tupleFromCache = Cache.getObject(tupleToSafe.getPerson().getId());
		if (null == tupleFromCache) {
			Cache.addObject(tupleToSafe.getPerson().getId(), tupleFromCache);
			tupleFromCache=tupleToSafe;
		}

		if (!tupleFromCache.getVersion().equals(tupleToSafe.getVersion())) {
			throw new Exception("error in optimistic locking");
		}
		Loader.save(Person.class, tupleToSafe.getPerson());
		tupleFromCache.setVersion(tupleFromCache.getVersion() + 1);		
		System.out.println("cache-service-save");

	}

	@Override
	public void delete(PersonTuple personTuple) {
		Person person = personTuple.getPerson();
		if (Cache.containsKey(person.getId())) {
			Cache.remove(person.getId());
		}
		Loader.delete(Person.class, person.getId());
		System.out.println("cache-service-delete");

	}

	@Override
	public PersonTuple load(Class<PersonTuple> cl, Integer id) {
		if (!Cache.containsKey(id)) {
			Person person = new Person();
			person = Loader.load(Person.class, id);
			PersonTuple newPersonTuple = new PersonTuple(1, person);
			Cache.addObject(id, newPersonTuple);
			return new PersonTuple(newPersonTuple);
		}
		System.out.println("cache-service-load");
		return new PersonTuple(Cache.getObject(id));

	}

}
