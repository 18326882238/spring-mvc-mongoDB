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
 * ����
 * ע��@ContextConfiguration��ʾ��ApplicationContext����ע��������Ͳ��������������ڲ��Գ�������new�ˣ�ֱ��ʹ�� 
 */  
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml"})
public class TestController {
	
	// ���κ���Ҫ��¼��־������ 
    private static Logger logger = Logger.getLogger(TestController.class);
	
    @Autowired
    private UserDao userDao;
    
    /** 
     * ����Spring IOC�Ŀ������� 
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
        logger.info("-------��ȡ�˻�����:-------" + user2.getPassword());
    }
    
    @Test
    public void update() {
        User user = userDao.findOneByUsername("skyLine2");
        logger.info("-------����֮ǰ�˻�����:-------" + user.getPassword());
        user.setPassword("9999888");
        userDao.updateFirst(user);
        logger.info("-------����֮���˻�����:-------" + user.getPassword());
    }
    
    @Test
    public void findAll() {
//        List<User> lists = userDao.findAll();
//        for (User user : lists) {
//            logger.info("-------user����:-------" + user.getUsername());
//        }
  
        List<User> lists2= userDao.findList(1,2);
        for (User user : lists2) {
            logger.info("-------user����:-------" + user.getUsername());
        }
    }
  
    @Test
    public void delete() {
        userDao.delete(userDao.findOneByUsername("skyLine2").getId());
    }
    
	
}
