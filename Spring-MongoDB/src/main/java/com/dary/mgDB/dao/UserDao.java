package com.dary.mgDB.dao;

import java.util.List;

import com.dary.mgDB.entity.User;

/** 
 * UserDao 
 * Created by dary on 18/01/14. 
 */
public interface UserDao {
	
	/** 
     * ��ѯ�������� 
     * 
     * @return 
     */  
    List<User> findAll();  
  
    /** 
     * ���ڷ�ҳ��ѯ 
     * 
     * @param skip(��һ������Ϊ0) 
     * @param limit 
     * @return 
     */  
    List<User> findList(int skip, int limit);  
  
    /** 
     * �����û� 
     * 
     * @param user 
     */  
    void store(User user);  
  
    /** 
     * ����id��ѯ 
     * 
     * @param id 
     * @return 
     */  
    User findOne(String id);  
  
    /** 
     * �����û�����ѯ 
     * 
     * @param username 
     * @return 
     */  
    User findOneByUsername(String username);  
  
    /** 
     * ���� 
     * 
     * @param user 
     */  
    void updateFirst(User user);
  
    /** 
     * ɾ�� 
     * 
     * @param ids 
     */  
    void delete(String... ids);
}
