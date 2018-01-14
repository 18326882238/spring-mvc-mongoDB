package com.dary.mgDB.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.dary.mgDB.entity.User;

/** 
 * UserDaoImpl 
 * Created by dary on 18/01/14. 
 */  
@Service
public class UserDaoImpl implements UserDao{

	//MongoTemplate是数据库和代码之间的接口，对数据库的操作都在它里面  
    @Autowired
    private MongoTemplate mongoTemplate;
    
	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.mongoTemplate.find(new Query(), User.class);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<User> findList(int skip, int limit) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.with(new Sort((List<Order>) new Order(Direction.ASC, "_id")));
		query.skip(skip).limit(limit);
		return this.mongoTemplate.find(query, User.class);
	}

	public void store(User user) {
		// TODO Auto-generated method stub
		mongoTemplate.save(user);
	}

	public User findOne(String id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		Criteria criteria = Criteria.where("_id").is(id);
		query.addCriteria(criteria);
		return this.mongoTemplate.findOne(query, User.class);
	}

	/** 
     * Criteria.where("username").is(username) 
     * 前一个是数据库的字段,后一个是java类的字段 
     * 
     * @param username 
     * @return 
     */
	public User findOneByUsername(String username) {
		// TODO Auto-generated method stub
		Query query = new Query();
		Criteria criteria = Criteria.where("username").is(username);
		query.addCriteria(criteria);
		return this.mongoTemplate.findOne(query, User.class);
	}

	public void updateFirst(User user) {
		// TODO Auto-generated method stub
		Update update = new Update();
		Query query = new Query();
		update.set("username", user.getUsername());
		update.set("password", user.getPassword());
		this.mongoTemplate.updateFirst(query
				.addCriteria(Criteria.where("_id")
						.is(user.getId())), update, user.getClass());
	}

	public void delete(String... ids) {
		// TODO Auto-generated method stub
		if(ids == null || ids.length == 0){
			return;
		}
		for(String id : ids){
			Query query = new Query(Criteria.where("_id").is(id));
			this.mongoTemplate.remove(query, User.class);
		}
		
	}
	
	
	
}
