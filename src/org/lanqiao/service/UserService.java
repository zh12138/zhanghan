package org.lanqiao.service;

import org.lanqiao.Dao.UserDao;
import org.lanqiao.entity.User;

public class UserService {
	public User findUserByUsername(String username) {
		UserDao dao=new UserDao();
		return dao.findUserByUsername(username);
	}

}
