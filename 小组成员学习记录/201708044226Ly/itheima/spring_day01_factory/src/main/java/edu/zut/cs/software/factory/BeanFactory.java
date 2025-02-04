package edu.zut.cs.software.factory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建Been对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义
 *JavaBean：用java语言编写的可重用组件
 *      javabean > 实体类
 * 它就是创建我们的service和dao对象的
 *
 *      1：需要一个配置文件来配置我们的service和dao
 *          配置的内容：唯一标志 = 全限定类名 （key = value）
 *      2：通过读取配置文件中配置的内容，反射创建对象
 *
 * 我们的配置文件：xml和properties
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties properties;

    //定义一个Map，用于存放我们要创建的对象，我们把它称之为容器
    private  static Map<String, Object> beans;

    //使用静态代码块为Properties对象赋值
    static {
        try {
            //实例化对象
            properties = new Properties();
            //获取properties文件的流对象
            InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            properties.load(inputStream);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys = properties.keys();
            //遍历枚举
            while (keys.hasMoreElements()) {
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = properties.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key, value);

            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败！");
        }

    }

    /**
     * 根据bean的名称获取对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return

    public static Object getBean(String beanName) {
        Object bean = null;
        String beanPath = properties.getProperty(beanName);
//        System.out.println("beanPath:" + beanPath);
        try {
            bean = Class.forName(beanPath).newInstance();//每次都会调用默认构造函数创建对象
//            System.out.println("bean:" + bean);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bean;
    }
    */
}
