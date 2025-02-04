package com.zcw.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		/*
		 * HelloWorld helloWorld = new HelloWorld(); helloWorld.setName("zcw");
		 */
		// 1.创建Spring的IOC容器对象
		// ApplicationContext代表IOC容器
		// ClassPathXmlApplicationContext是ApplicationContext的实现类，该实现类从类路径下加载
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.从IOC中获取Bean实例
		// 利用id定位到IOC容器中的bean
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		// 3.调用方法
		helloWorld.hello();
		// Car.class 是惟一的情况下可以使用
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		car = (Car) ctx.getBean("car2");
		System.out.println(car);

		Person person = (Person) ctx.getBean("person2");
		System.out.println(person);

	}
}
