package com.zcw.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DAO {

	
	public void update(String sql, Object...args)  {
		//insert,update,delete �����԰�������
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBC_tools.getconnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++)
			{
				preparedStatement.setObject(i+1, args[i]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBC_tools.release(null, preparedStatement, connection);
		}
	}
    
   // ��һ����Ϣ,���ض�Ӧ�Ķ���
     public <T> T get(Class<T> clazz,String sql, Object...args){
    	 
    	 T entity =null;
    	 Connection connection =null;
    	 PreparedStatement preparedStatement = null;
    	 ResultSet rs =null;
    	 try {
    		 connection = JDBC_tools.getconnection();
    		 preparedStatement = connection.prepareStatement(sql);
    		 //3.���ռλ��
    		 for(int i=0;i<args.length;i++)
    		 {
    			 preparedStatement.setObject(i+1, args[i]);
    		 }
    		 //4.���в�ѯ���õ�ResultSet
    		 rs = preparedStatement.executeQuery();
    		 //5.��ResultSet���м�¼��׼��һ��Map<String ,Object>  ��������еı���  ֵ������е�ֵ
    		 
    		 if(rs.next())
    		 {
    			 Map<String ,Object> values =new HashMap<String ,Object>();
    			 //6���õ�ResultSetMetaData����
    			 ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
    			 //7.����ResultSet��ָ������һ����λ
    			 //8.��ResultSetMetaData����õ���������ж�����
    			  int columnCount = resultSetMetaData.getColumnCount();
    			  //9.��ResultSetMetaData�õ�ÿһ�еı�������ResultSet�õ�ÿһ�е�ֵ
    			  for(int i=0;i<columnCount;i++)
    			  {
    				  String columnLabel = resultSetMetaData.getColumnLabel(i+1);
    				  Object columnValue = rs.getObject(i+1);
    				  //10.���Map����
    				  values.put(columnLabel, columnValue);
    				  
    			  }
    			  //11.�÷��䴴��Class��Ӧ�Ķ���
    			 entity = clazz.newInstance();
    			  //12.����Map�����÷��������������ֵ
    			  for(Map.Entry<String, Object> entry: values.entrySet())
    			  {
    				  String propertyName = entry.getKey();
    				  Object value = entry.getValue();
    				  //ReflectionUtils.setFieldValue(entity, propertyName, value);
    				  //Ϊ���Ը�ֵ
    				  BeanUtils.setProperty(entity, propertyName, value);
    			  }
    		 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBC_tools.release(rs, preparedStatement, connection);
		}
	return entity;
    }
 // ��һ����Ϣ�����ض�Ӧ�Ķ��󼯺�
     public <T> List<T> getForList(Class<T> clazz,String sql, Object...args){
	return null;
   }
//����ĳ����¼��ĳһ���ֶε�ֵ  ��һ��ͳ�Ƶ�ֵ��һ���ж�������¼�ȡ���
    public <E> E getForValue(String sql, Object...args){

	return null;
    }
}
