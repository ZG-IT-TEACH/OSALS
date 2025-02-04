package com.zcw.hibernate.hello;


import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		//1.创建一个SessionFactory对象
		SessionFactory sessionFactory = null;
		//1)创建Configuration对象 ：对应hibernate的基本配置信息和对象关系映射信息
		
		Configuration configuration = new Configuration().configure();
		//5版本.调用Configuration的buildSessionFactory()方法返回一个SessionFactory对象 
				sessionFactory = configuration.buildSessionFactory();  
				
				//4.x版本. 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
						//hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
						//ServiceRegistry serviceRegistry = 
						//		new ServiceRegistryBuilder().applySettings(configuration.getProperties())
						//		                            .buildServiceRegistry();
		//2.创建一个Session对象
		Session session = sessionFactory.openSession();
		
		//3.开启事务
		Transaction transaction = session.beginTransaction();
		//4.执行保存操作
		News news = new News("Java12345", "zcw", new Date(new java.util.Date().getTime()));
		session.save(news);		
		//5.提交事务
		transaction.commit();
		//6.关闭Session
		session.close();
		//7.关闭SessionFactory对象
		sessionFactory.close();
		
		/*Configuration configuration = null;
        StandardServiceRegistryBuilder builder = null;
		StandardServiceRegistry registry = null;
		configuration = new Configuration().configure();
		builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		registry = builder.build();
		News news = new News("Java12345", "zcw", new Date(new java.util.Date().getTime()));
		Session session = configuration.buildSessionFactory(registry).getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(news);
		transaction.commit();*/
		
		
	}

}
