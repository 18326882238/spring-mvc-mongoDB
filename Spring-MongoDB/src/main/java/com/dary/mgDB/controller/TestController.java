package com.dary.mgDB.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dary.mgDB.dao.UserDao;
import com.dary.mgDB.entity.User;

/** 
 * 测试
 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以往那样在测试程序里先new了，直接使用 
 */  
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml"})
public class TestController {
	
	// 在任何需要记录日志的类中 
    private static Logger logger = Logger.getLogger(TestController.class);
	
    @Autowired
    private UserDao userDao;
    
    /** 
     * 测试Spring IOC的开发环境 
     */
    /*
    @Test
    public void springIoc() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-mvc.xml");  
        TestSpringIocController test = (TestSpringIocController) context.getBean("test");  
        test.print();
    }
    */
    
    @Test
    public void save() {
        User user = new User();
        user.setUsername("skyLine2");
        user.setPassword("7777777");
        userDao.store(user);
        User user2 = userDao.findOneByUsername("skyLine2");
        logger.info("-------获取账户密码:-------" + user2.getPassword());
    }
    
    @Test
    public void update() {
        User user = userDao.findOneByUsername("skyLine2");
        logger.info("-------更新之前账户密码:-------" + user.getPassword());
        user.setPassword("9999888");
        userDao.updateFirst(user);
        logger.info("-------更新之后账户密码:-------" + user.getPassword());
    }
    
    @Test
    public void findAll() {
//        List<User> lists = userDao.findAll();
//        for (User user : lists) {
//            logger.info("-------user遍历:-------" + user.getUsername());
//        }
  
        List<User> lists2= userDao.findList(1,2);
        for (User user : lists2) {
            logger.info("-------user遍历:-------" + user.getUsername());
        }
    }
  
    @Test
    public void delete() {
        userDao.delete(userDao.findOneByUsername("skyLine2").getId());
    }
    
	
}
