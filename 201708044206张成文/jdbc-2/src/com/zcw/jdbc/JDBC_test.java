package com.zcw.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sound.midi.MidiDevice.Info;

import org.junit.Test;



public class JDBC_test {

	/**
	 * 
	 * DriverManager  �������Ĺ�����
	 * @throws Exception 
	 */
	@Test
	public void testDriverManager() throws Exception
	{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ�����ļ�
		InputStream in =
		             getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties =new Properties();
		properties.load(in);
		driverClass= properties.getProperty("driver");
		jdbcUrl= properties.getProperty("jdbcUrl");
		user= properties.getProperty("user");
		password = properties.getProperty("password");
		//1.�������ݿ�����
		Class.forName(driverClass);
		//2.ͨ��DriverManager��getConnection����������ȡ���ݿ�����
		Connection connection =DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(connection);
	}
	/**
	 * Driver  ��һ���ӿ�  �����ݿ⳧�̱����ṩʵ�ֵĽӿڣ��ܴ����л�ȡ���ݿ�����
	 * @throws SQLException 
	 */
	
	@Test
	public void testDriver() throws SQLException {
		//1.����һ��Driverʵ����Ķ���
		Driver driver = new com.mysql.cj.jdbc.Driver();
		
		String url = "jdbc:mysql://localhost:3306/eesy?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "z123");
		//2.����Driver�ӿڵ� connect  ��ȡ����
		Connection connection =driver.connect(url, info);
		System.out.println(connection);
		
	}
	
	/**
	 * 
	 * ��дһ��ͨ�õķ������ڲ��޸�Դ���������£����Ի���κ����ݿ������
	 * 
	 * ��������������ļ� 
	 * @throws Exception  
	 */

	public Connection getConnection() throws  Exception
	{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ�����ļ�
		InputStream in =
		             getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties =new Properties();
		properties.load(in);
		driverClass= properties.getProperty("driver");
		jdbcUrl= properties.getProperty("jdbcUrl");
		user= properties.getProperty("user");
		password = properties.getProperty("password");
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		
		Properties info= new Properties();
		info.put("user", user);
		info.put("password", password);
		Connection connection = driver.connect(jdbcUrl, info);
		return connection;
	}
	@Test
	public void testGetConnection() throws Exception
	{
		System.out.println(getConnection());
	}
}
