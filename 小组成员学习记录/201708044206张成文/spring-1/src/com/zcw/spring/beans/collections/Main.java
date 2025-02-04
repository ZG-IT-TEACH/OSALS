package com.zcw.spring.beans.collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Person person = (Person) context.getBean("person5");

		System.out.println(person);

		NewPerson newPerson = (NewPerson) context.getBean("newPerson");

		System.out.println(newPerson);

		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource.getProperties());

	}

}
