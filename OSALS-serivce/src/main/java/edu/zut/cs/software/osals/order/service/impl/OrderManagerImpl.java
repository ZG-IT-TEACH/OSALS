package edu.zut.cs.software.osals.order.service.impl;


import edu.zut.cs.software.base.service.impl.GenericTreeManagerImpl;
import edu.zut.cs.software.osals.order.dao.OrderDao;
import edu.zut.cs.software.osals.order.domain.Order;
import edu.zut.cs.software.osals.order.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderManager")
@Transactional
public class OrderManagerImpl extends GenericTreeManagerImpl<Order, Long> implements OrderManager {

    OrderDao orderDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
        this.treeDao = this.orderDao;
        this.dao = this.treeDao;
    }

    @Override
    public List<Order> findByName(String name) {
        Order queryObject = new Order();
        queryObject.setName(name);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith()).withIgnorePaths("dateCreated", "dateModified");
        Example<Order> example = Example.of(queryObject, exampleMatcher);
        List<Order> result = this.dao.findAll(example);
        return result;

    }

}