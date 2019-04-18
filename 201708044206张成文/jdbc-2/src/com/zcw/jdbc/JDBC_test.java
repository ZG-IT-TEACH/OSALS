package com.zcw.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import javax.sound.midi.MidiDevice.Info;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.junit.Test;
import org.junit.runner.notification.RunListener.ThreadSafe;

import com.mysql.cj.jdbc.result.ResultSetMetaData;



public class JDBC_test {
	
	/**
	 * 
	 * ���E��
	 * 
	 * 1.������Sql�M�в�ԃ���õ��Y����
	 * 2.���÷��䄓��ʵ����Č��󣬴���Customer����
	 * 3.��ȡ��������еı��� Name��Money��Id
	 * 4.�ڻ�ȡ�������ÿһ�е�ֵ�����3�õ�һ��Map�������еı���
	 *      ֵ���е�ֵ
	 * 5.�����÷���Ϊ2�Ķ�Ӧ�����Ը�ֵ�����Լ�ΪMap�ļ���ֵ��ΪMap��ֵ
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 1.������ResultSet��Ԫ���ݣ������п��Ի�ȡ��������ж����У�
	 *   ������ʲô
	 * 2.�õ�ResultSetMetaData���󣺵���Result��getMetaData��������
	 *    ���õķ�����
	 *    >int getColumnCount��sql����а�����Щ��
	 *    >String getColumnLable��int column��:��ȡָ�����еı���
	 *                                         ������1��ʼ
	 * 
	 */
	@Test
	public void testResultSetMetaData()
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			String sql="select id,name Name,money Money from account where id =?";
			connection =JDBC_tools.getconnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setInt(1, 3);
			resultSet =preparedStatement.executeQuery();
			
			Map<String, Object> values = new HashMap<String,Object>();
			//1.�õ�ResultMetaData����
			java.sql.ResultSetMetaData resultSetMetaData =resultSet.getMetaData();
			 	while(resultSet.next())
			 	{//2.��ӡÿһ�е�����
			 		for(int i=0;i<resultSetMetaData.getColumnCount();i++)
			 		{
			 			String columnLabel =resultSetMetaData.getColumnLabel(i+1);
			 		//	System.out.println(columnLabel);
			 			Object columnValue =resultSet.getObject(columnLabel);
			 			values.put(columnLabel, columnValue);
			 		}
			 	}
			//System.out.println(values);
			
			Class clazz= Customer.class;
			Object object = clazz.newInstance();
			for(Map.Entry<String, Object> entry:values.entrySet())
			{
				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();
				//System.out.println(fieldName+":  "+fieldValue);
				ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
			}
			System.out.println(object);
			for(int  i= 0;i<resultSetMetaData.getColumnCount();i++)
			{
				String columnLabel = resultSetMetaData.getColumnLabel(i+1);
				//System.out.println(columnLabel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			JDBC_tools.release(resultSet, preparedStatement, connection);
		}
		
	}
	/**
	 * ͨ�õĻ�ȡ��Ϣ�ķ���
	 * <T>����
	 */
	public void testGet()
	{
		String sql = "select id ,name,money from account where id=1";
	    Customer customer = get(Customer.class,sql,1);
	    System.out.println(customer);
		
	}
	public <T> T get(Class<T> clazz,String sql,Object...args)//��������ͣ�SQL������
	{
		T entity = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			//1.�õ�ResultSet����
			connection =JDBC_tools.getconnection();
			preparedStatement =connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++)
			{
				preparedStatement.setObject(i+1, args[i]);
			}
			resultSet =preparedStatement.executeQuery();
			//2.�õ�ResultSetMetaData����
			java.sql.ResultSetMetaData resultSetMetaData =resultSet.getMetaData();
			//3.����һ��Map<String ,Object>����  �����еı���  ֵ���е�ֵ
			Map<String, Object> values = new HashMap<>();
			//4��������������ResultSetMetaData  ���3��Ӧ�Ķ���
			if(resultSet.next())
			{
				for(int i=0;i<resultSetMetaData.getColumnCount();i++)
				{
					String columnLabel =resultSetMetaData.getColumnLabel(i+1);
					Object columnValues= resultSet.getObject(i+1);
					
					values.put(columnLabel, columnValues);
				}
			}
			//5��Map��Ϊ�գ����÷��䴴��clazz��Ӧ�Ķ���
			if(values.size()>0)
			{
				entity = clazz.newInstance();
				//6.����Map�������÷���ΪClass����Ķ�Ӧ������ֵ��ֵ
				for(Map.Entry<String, Object>entry:values.entrySet())
				{
					String fieldName = entry.getKey();
					Object value=entry.getValue();
					ReflectionUtils.setFieldValue(entry, fieldName, value);
				}
			}
			
			
			if(resultSet.next())
			{//���÷��䴴������
				entity =clazz.newInstance();
				//ͨ������sql����жϵ���ѡ������Щ��   �Լ�
				//    ��ҪΪEntity�������Щ���Ը�ֵ
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			JDBC_tools.release(resultSet, preparedStatement, connection);
		}
		return entity;
	}
//	
//	/**
//	 * 
//	 * ʹ��PreparedStatement���SQLע������
//	 */
//	@Test
//	
//	public void testSqlInjection2()
//	{
//		String name ="a' or money = ";
//		String money= "or '1'='1";
//		String sql = "select * from account where name=?"+
//				"and money=?";
	
      ////String sql="insert into account(name,money)"+
      ////"values(?,?)";
//		Connection connection=null;
//		PreparedStatement preparedStatement =null;
//		ResultSet resultSet =null;
//		try {
//			connection =JDBC_tools.getconnection();
//			preparedStatement=connection.prepareStatement(sql);
//			preparedStatement.setString(1, name);
//			preparedStatement.setString(2, money);
//			resultSet=preparedStatement.executeQuery();
//			if(resultSet.next())
//			{
//				System.out.println("��¼�ɹ�");
//			}else{
//				System.out.println("ʧ��");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally
//		{
//			JDBC_tools.release(resultSet, preparedStatement, connection);
//		}
//	}
//	
//	
//	/**
//	 * SQLע��
//	 * 
//	 */
//	@Test
//	public void testSqlInjection()
//	{
//		String name ="a' or money = ";
//		String money= "or '1'='1";
//		String sql = "select * from account where name='"+name
//				+ "'and"+"money='"+money+"'";
//		Connection connection=null;
//		Statement statement =null;
//		ResultSet resultSet =null;
//		try {
//			connection =JDBC_tools.getconnection();
//			statement =connection.createStatement();
//			resultSet=statement.executeQuery(sql);
//			if(resultSet.next())
//			{
//				System.out.println("��¼�ɹ�");
//			}else{
//				System.out.println("ʧ��");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally
//		{
//			JDBC_tools.release(resultSet, statement, connection);
//		}
//	}
//	
//	
//	
//	/**
//	 * 
//	 * PreparedStatement��Statement���ӽӿڣ����Դ����ռλ����sql��䣬
//	 *  �ṩ�˲���ռλ�������ķ���,�����Է��� SQLע�빥��
//	 * 1)ʹ��Statement ��Ҫ���� sql����ƴд
//	 * 2)PreparedStatement  
//	 * 1.����PreparedStatement
//	 * 
//	 * String sql = "insert into account values(?,?,?)";
//	 * PreparedStatement ps = conn.preparedstatement(sql);
//	 * 
//	 * 2.����preparedstatement��setXXX��int index������ֵ��1��ʼ��,Object val������
//	 *  ����ռλ����ֵ
//	 *  
//	 *  3.ִ��sql��� executeQuery������executeUpdate����  ִ��ʱ����Ҫ����sql
//	 *  
//	 * 
//	 */
//	
////	@Test
////	public void testPreparedStatement()
////	{
////		Connection connection= null;
////		PreparedStatement preparedStatement =null;
////		try {
////			connection=JDBC_tools.getconnection();
////			String sql="insert into account(name,money)"+
////			"values(?,?)";
////			/**
////			 * update(String sql,Object...args(�����ɱ�))
////			 * for(int i=0;i<args.length;i++)
////			 * {
////			 * preparedStatement.setObject(i+1,args[i]);
////			 * }
////			 *   
////			 */
////			preparedStatement =connection.prepareStatement(sql);
////			preparedStatement.setString(1, "Mary");
////			preparedStatement.setInt(2,1000 );
////			//preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
////			preparedStatement.executeUpdate();
////		} catch (Exception e) {
////			// TODO: handle exception
////			e.printStackTrace();
////		}finally {
////			JDBC_tools.release(null, preparedStatement, connection);
////		}
////	}
//	
//	/**
//	 * ���������װ��ʹ��JDBC�����в�ѯ�Ľ��
//	 * 1.���� Statement�����executeQuery��Sql�����Եõ������
//	 * 2.ResultSet����ʵ���Ͼ���һ�����ݱ���һ��ָ��ָ�����ݱ�ĵ�һ�е�ǰ�棬
//	 *   ���Ե���next�������������һ���Ƿ���Ч������Ч��������ŭ��ָ������
//	 *   �൱��Iterator hasNext������next���������Ľ����
//	 *   3.��ָ�붨λ��ĳһ���ǣ����Ե���getXXX��index����getXXX(columnName)
//	 *     ��ȡĳһ�е�ֵ  �磺getInt(1),getString("name")
//	 *   4.ResultSet��Ҫ�ر�
//	 */
//	@Test
//	public void testResultSet()
//	{
//		Connection connection = null;
//		
//		Statement statement =null;
//		ResultSet resultSet= null;
//		
//		try {
//			
//			
//			//1.��ȡConnection
//			connection= JDBC_tools.getconnection();
//			//2.��ȡStatement
//			statement= connection.createStatement();
//			//3.׼��sql
//			//String sql = "select id ,name,money from account where id=1";
//			String sql = "select id ,name,money from account ";
//			//4.ִ�в�ѯ�õ�ResultSet
//			resultSet= statement.executeQuery(sql);
//			//5.����ResultSet
//			while(resultSet.next())
//			{
//				int id= resultSet.getInt(1);
//				String name = resultSet.getString("name");
//				int money = resultSet.getInt(3);
//				System.out.println(id);
//				System.out.println(name);
//				System.out.println(money);
//			}
//			//6.�ر�
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			JDBC_tools.release(resultSet, statement, connection);
//		}
//		
//	}
//	
//	
//	
//	
//	
//	
//	@Test
//	public void testStatement() throws Exception
//	{
//		
//		/**
//		 * execyteUpdate(sql)   sql�������� insert��update��delete������select
//		 * 
//		 * ��Ҫ��finally�� �ر� connection��statement����  ��Ϊ�����쳣�Ļ�  ���޷��ر�
//		 * 
//		 * �ر������ǹرպ��ȡ��
//		 */
//		
//		
//		/*String driverClass = null;
//		String jdbcUrl = null;
//		String user = null;
//		String password = null;
//		
//		//��ȡ�����ļ�
//		InputStream in =
//		             this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		
//		Properties properties =new Properties();
//		properties.load(in);
//		driverClass= properties.getProperty("driver");
//		jdbcUrl= properties.getProperty("jdbcUrl");
//		user= properties.getProperty("user");
//		password = properties.getProperty("password");
//		//JDBC��ָ�������ݱ��в���һ����¼
//		//1.��ȡ���ݿ�����
//		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);*/
//		String driverClass = null;
//		String jdbcUrl = null;
//		String user = null;
//		String password = null;
//		
//		//��ȡ�����ļ�
//		InputStream in =
//		             this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		
//		Properties properties =new Properties();
//		properties.load(in);
//		driverClass= properties.getProperty("driver");
//		jdbcUrl= properties.getProperty("jdbcUrl");
//		user= properties.getProperty("user");
//		password = properties.getProperty("password");
//		Connection connection= null;
//		Statement statement= null;
//		
//		try {
//			connection= DriverManager.getConnection(jdbcUrl, user, password);
//			
//			//3.׼�������SQL���
//	//		String sql = "insert into account(id,name,money)"+
//	//		"values('4','ddd','1000')";
//			
//		//	String sql= "delete from account where id=4";
//			String sql = "update account set name='TOM' where id=3";
//			//4.ִ�в���
//			//(1)��ȡ����SQL����statement����:����connection��createstatement������������ȡ
//			 statement = connection.createStatement();
//			
//			//��2������statement�����executupdate��sql��ִ��sql�����в���
//			statement.executeUpdate(sql);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				//5.�ر�statement����
//				if(statement!=null)
//				{
//				statement.close();
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally{
//				//2.�ر�����
//				if(connection!=null)
//				{
//				connection.close();
//				}
//			}
//		}
//	}
//	/*@Test
//	public Connection getConnection2() throws Exception
//	{
//		String driverClass = null;
//		String jdbcUrl = null;
//		String user = null;
//		String password = null;
//		
//		//��ȡ�����ļ�
//		InputStream in =
//		             this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		
//		Properties properties =new Properties();
//		properties.load(in);
//		driverClass= properties.getProperty("driver");
//		jdbcUrl= properties.getProperty("jdbcUrl");
//		user= properties.getProperty("user");
//		password = properties.getProperty("password");
//		Class.forName(driverClass);
//		return DriverManager.getConnection(jdbcUrl, user, password);
//		}*/
//
//	/**
//	 * 
//	 * DriverManager  �������Ĺ�����
//	 * @throws Exception 
//	 */
//	@Test
//	public void testDriverManager() throws Exception
//	{
//		String driverClass = null;
//		String jdbcUrl = null;
//		String user = null;
//		String password = null;
//		
//		//��ȡ�����ļ�
//		InputStream in =
//		             getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		
//		Properties properties =new Properties();
//		properties.load(in);
//		driverClass= properties.getProperty("driver");
//		jdbcUrl= properties.getProperty("jdbcUrl");
//		user= properties.getProperty("user");
//		password = properties.getProperty("password");
//		//1.�������ݿ�����
//		Class.forName(driverClass);
//		//2.ͨ��DriverManager��getConnection����������ȡ���ݿ�����
//		Connection connection =DriverManager.getConnection(jdbcUrl, user, password);
//		System.out.println(connection);
//	}
//	/**
//	 * Driver  ��һ���ӿ�  �����ݿ⳧�̱����ṩʵ�ֵĽӿڣ��ܴ����л�ȡ���ݿ�����
//	 * @throws SQLException 
//	 */
//	
//	@Test
//	public void testDriver() throws SQLException {
//		//1.����һ��Driverʵ����Ķ���
//		Driver driver = new com.mysql.cj.jdbc.Driver();
//		
//		String url = "jdbc:mysql://localhost:3306/eesy?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//		Properties info = new Properties();
//		info.put("user", "root");
//		info.put("password", "z123");
//		//2.����Driver�ӿڵ� connect  ��ȡ����
//		Connection connection =driver.connect(url, info);
//		System.out.println(connection);
//		
//	}
//	
//	/**
//	 * 
//	 * ��дһ��ͨ�õķ������ڲ��޸�Դ���������£����Ի���κ����ݿ������
//	 * 
//	 * ��������������ļ� 
//	 * @throws Exception  
//	 */
//
//	public Connection getConnection() throws  Exception
//	{
//		String driverClass = null;
//		String jdbcUrl = null;
//		String user = null;
//		String password = null;
//		
//		//��ȡ�����ļ�
//		InputStream in =
//		             getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//		
//		Properties properties =new Properties();
//		properties.load(in);
//		driverClass= properties.getProperty("driver");
//		jdbcUrl= properties.getProperty("jdbcUrl");
//		user= properties.getProperty("user");
//		password = properties.getProperty("password");
//		Driver driver = (Driver) Class.forName(driverClass).newInstance();
//		
//		Properties info= new Properties();
//		info.put("user", user);
//		info.put("password", password);
//		Connection connection = driver.connect(jdbcUrl, info);
//		return connection;
//	}
//	@Test
//	public void testGetConnection() throws Exception
//	{
//		System.out.println(getConnection());
//	}
}
