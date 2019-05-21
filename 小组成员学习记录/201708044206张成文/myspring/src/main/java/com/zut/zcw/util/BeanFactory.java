package com.zut.zcw.util;

import java.util.List;

public class BeanFactory {

	private List<BeanDefined> beanDefinedList;

	public List<BeanDefined> getBeanDefinedList() {
		return beanDefinedList;
	}

	public void setBeanDefinedList(List<BeanDefined> beanDefinedList) {
		this.beanDefinedList = beanDefinedList;
	}
	public Object getBean(String beanId) throws Exception
	{
		Object instance;
		for(BeanDefined beanObj:beanDefinedList)//�ܹ��޶�getBean�õ���bendId��beanDefinedList�е�
		{
			if(beanId.equals(beanObj.getBeanId()))
			{
				String classPath = beanObj.getClassPath();
				Class classFile = Class.forName(classPath);
				//��Ĭ������£�spring����ͨ�����õ�ǰ�׵�Ĭ�Ϲ��췽������ʵ������
				instance = classFile.newInstance();
				return instance;
			}
		}
		return null;
	}
}
