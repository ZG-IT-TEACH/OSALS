package com.zcw.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zcw.mybatis.bean.Employee;

public class MyBatisTest {
	/**
	 * 
	 * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
	 * 
	 * @throws IOException
	 */

	@Test
	public void test() throws IOException {
		String resource = "mybatis-confing.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 获取一个sqlSession实例，能直接执行已经映射的SQL语句
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 1)sql的唯一标识
			// 2)执行sql要用的参数
			Employee employee = openSession.selectOne("com.zcw.mybatis.EmployeeMapper.selectBlog", 1);

			System.out.println(employee);
		} finally {
			openSession.close();
		}
	}

}
