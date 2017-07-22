package com.xuchg.mssm.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchg.mssm.dao.UserDao;
import com.xuchg.mssm.module.User;
import com.xuchg.mssm.service.UserService;
@Service
public class UserServiceImpl implements UserService {
@Autowired
	UserDao userDao;
	public List<User> getUserByPage(int page, int sum) {
		int startIndex = (page-1)*sum;
		List<User> list=userDao.getUserByPage(startIndex, sum);
		return list;
	}
	public int getPage(int sum) {
		List<User> list=userDao.getPage();
		int pagenum = (int)list.size()/sum+1;
		return pagenum;
	}
	public void addUser(User user) {
		userDao.addUser(user);
	}
	public User getUserById(int id) {
		return userDao.getUserById(id).get(0);
	}
	public void changeUserInfo(User user) {
		userDao.changeUserInfo(user);
	}
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}
	public List<User> getAll() {
	return userDao.getPage();
	}
}	
