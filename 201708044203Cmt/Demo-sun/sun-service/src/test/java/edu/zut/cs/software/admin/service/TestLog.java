package edu.zut.cs.software.admin.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext acx = new ClassPathXmlApplicationContext("bean.xml");
		UserManager userManager = (UserManager) acx.getBean("CGLIBProxyFactoryBean");//�ɴ��������ɴ������
		userManager.sayHello("world");

	}

}
