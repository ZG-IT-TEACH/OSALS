package edu.zut.cs.software.service.impl;

import edu.zut.cs.software.dao.AccountDao;
import edu.zut.cs.software.dao.impl.AccountDaoImpl;
import edu.zut.cs.software.factory.BeanFactory;
import edu.zut.cs.software.service.AccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

//    private AccountDao accountDao = new AccountDaoImpl();

    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

//    private int i = 1;

    public void saveAccount() {
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
