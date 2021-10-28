package com.nevexis.forkjoin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IteratorCLass {
	
	private static Stream<Person> iterate(ResultSet rs) {

		Iterator<Person> iter = new Iterator<Person>() {

			@Override
			public boolean hasNext() {
				try {
					return !rs.isLast();
				} catch (SQLException e) {	
				}
				return false;
			}

			@Override
			public Person next() {
				if(hasNext())
				{
					try {
						rs.next();
					} catch (SQLException e) {
					
					}
					try {
						return new Person(rs.getString("FirstName"),rs.getString("LastName"));
					} catch (SQLException e) {
						
					}
				}
				return null;
			}
		};
		
		Stream<Person> targetStream = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
		return targetStream;
	}
}
