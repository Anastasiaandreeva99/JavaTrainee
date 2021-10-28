package com.nevexis.consumerSupplier;

public class Main {

	public static void main(String[] args) {

		ConsumerSi<String> consumer1 = (phone) ->printPhones(phone);
		ConsumerSi<Person> consumer2 = Main::printPerson;
		consumer1.accept("0878654312");
		Person sisi =new Person("sisi", 16);
		consumer2.accept(sisi);
		test(consumer1);
		SupplierSi<String> supplier = () -> sisi.getName();
        System.out.println(supplier.get());

	}
	
	public static void printPhones(String phone) {
		System.out.println("Phone number: " + phone);
	}

	public static void printPerson(Person person) {
		System.out.println(person.getName() + "-" + person.getYears());
	}
	public static void test(ConsumerSi<String> consumer)
	{
		consumer.accept("vancho");
		//insert code here 
	}
}
