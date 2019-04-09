package com.zcw.jpa.helloworld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args)
	{
		//1.����EntityManagerFactory
		String persistenceUnitName = "jpa-1";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		//2.����EntityManager
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//3.��������
		
		EntityTransaction transaction =entityManager.getTransaction();
		
		transaction.begin();
		//4.���г־û�����
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setEmail("tom@zcw.com");
		customer.setLastName("Tom");
		
		entityManager.persist(customer);
		//5.�ύ����
		
		
		transaction.commit();
		//6.�ر�EntityManager
		
		entityManager.close();
		
		//7.�ر�EntityManagerFactory
		entityManagerFactory.close();
	}
}
