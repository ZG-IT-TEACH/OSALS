package edu.zut.cs.software.ui;

import edu.zut.cs.software.factory.BeanFactory;
import edu.zut.cs.software.service.AccountService;
import edu.zut.cs.software.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层
 * 用于调用业户层
 */

public class Client {
    public static void main(String[] args) {
//        AccountService accountService = new AccountServiceImpl();
        for(int i = 0; i <5; i++) {
            AccountService accountService = (AccountService) BeanFactory.getBean("accountService");
            System.out.println(accountService);
            accountService.saveAccount();
        }
    }
}
