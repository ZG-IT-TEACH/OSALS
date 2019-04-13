package com.zcw.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


import javax.sound.midi.MidiDevice.Info;

import org.junit.Test;



public class JDBC_test {
	
	/**
	 * ���������װ��ʹ��JDBC�����в�ѯ�Ľ��
	 * 1.���� Statement�����executeQuery��Sql�����Եõ������
	 * 2.ResultSet����ʵ���Ͼ���һ�����ݱ���һ��ָ��ָ�����ݱ�ĵ�һ�е�ǰ�棬
	 *   ���Ե���next�������������һ���Ƿ���Ч������Ч��������ŭ��ָ������
	 *   �൱��Iterator hasNext������next���������Ľ����
	 *   3.��ָ�붨λ��ĳһ���ǣ����Ե���getXXX��index����getXXX(columnName)
	 *     ��ȡĳһ�е�ֵ  �磺getInt(1),getString("name")
	 *   4.ResultSet��Ҫ�ر�
	 */
	@Test
	public void testResultSet()
	{
		Connection connection = null;
		
		Statement statement =null;
		ResultSet resultSet= null;
		
		//1.��ȡConnection
		
		//2.��ȡStatement
		
		//3.׼��sql
		
		//4.ִ�в�ѯ�õ�ResultSet
		
		//5.����ResultSet
		
		//6.�ر�
	}
	
	
	
	
	
	
	@Test
	public void testStatement() throws Exception
	{
		
		/**
		 * execyteUpdate(sql)   sql�������� insert��update��delete������select
		 * 
		 * ��Ҫ��finally�� �ر� connection��statement����  ��Ϊ�����쳣�Ļ�  ���޷��ر�
		 * 
		 * �ر������ǹرպ��ȡ��
		 */
		
		
		/*String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ�����ļ�
		InputStream in =
		             this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties =new Properties();
		properties.load(in);
		driverClass= properties.getProperty("driver");
		jdbcUrl= properties.getProperty("jdbcUrl");
		user= properties.getProperty("user");
		password = properties.getProperty("password");
		//JDBC��ָ�������ݱ��в���һ����¼
		//1.��ȡ���ݿ�����
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);*/
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ�����ļ�
		InputStream in =
		             this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties =new Properties();
		properties.load(in);
		driverClass= properties.getProperty("driver");
		jdbcUrl= properties.getProperty("jdbcUrl");
		user= properties.getProperty("user");
		password = properties.getProperty("password");
		Connection connection= null;
		Statement statement= null;
		
		try {
			connection= DriverManager.getConnection(jdbcUrl, user, password);
			
			//3.׼�������SQL���
	//		String sql = "insert into account(id,name,money)"+
	//		"values('4','ddd','1000')";
			
		//	String sql= "delete from account where id=4";
			String sql = "update account set name='TOM' where id=3";
			//4.ִ�в���
			//(1)��ȡ����SQL����statement����:����connection��createstatement������������ȡ
			 statement = connection.createStatement();
			
			//��2������statement�����executupdate��sql��ִ��sql�����в���
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//5.�ر�statement����
				if(statement!=null)
				{
				statement.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//2.�ر�����
				if(connection!=null)
				{
				connection.close();
				}
			}
		}
	}
	/*@Test
	public Connection getConnection2() throws Exception
	{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ�����ļ�
		InputStream in =
		             this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties =new Properties();
		properties.load(in);
		driverClass= properties.getProperty("driver");
		jdbcUrl= properties.getProperty("jdbcUrl");
		user= properties.getProperty("user");
		password = properties.getProperty("password");
		Class.forName(driverClass);
		return DriverManager.getConnection(jdbcUrl, user, password);
		}*/

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
