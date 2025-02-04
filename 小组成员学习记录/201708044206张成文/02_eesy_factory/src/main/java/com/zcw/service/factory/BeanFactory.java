package com.zcw.service.factory;

import sun.misc.ExtensionInstallationException;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂   创建我们的service和dao对象的
 *
 * 1.需要一个配置文件来配置我们的service和dao
 *    配置的内容，唯一标识=全限定类名（key=value）
 * 2.通过读取配置文件中的配置内容，反射创建对象
 *
 *
 * 配置文件可以是xml和properties（相对简单）
 *
 * Bean 在计算机英语中  有可重用组件的含义
 * Javabean！=实例类
 * Javabean>实体类  用java语言编写的可重用组件
 *
 *
 */
public class BeanFactory {
    //定义一个properties对象
    private static Properties props;
    //定义一个Map,用于存放我们要创建的对象，称之为容器
    private static Map<String,Object>beans;
    //使用静态代码块为properti对象赋值
    static {
        try {
            //实例化对象
            props = new Properties();//降低耦合，不可能完全消除
            //获取properti文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys = props.keys();
            //遍历枚举
            while(keys.hasMoreElements())
            {
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPtah = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPtah).newInstance();
                //吧key和value存入容器之中
                beans.put(key,value);

            }
        }catch (Exception e)
        {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 单例   根据Bean名称获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName)
    {
        return beans.get(beanName);
    }
    /**
     * 根据Bean名称获取bean对象
     * @param beanName
     * @return

    public static Object getBean(String beanName)
    {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            //1.读取配置文件2.反射的方式创建对象
            bean = Class.forName(beanPath).newInstance();

        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }*/
}
