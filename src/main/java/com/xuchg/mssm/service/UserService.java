package com.xuchg.mssm.service;
import java.util.List;
import com.xuchg.mssm.module.User;
	public interface UserService {

		public List<User> getUserByPage(int page,int sum);

		public int getPage(int sum);

		public void addUser(User user);

		public User getUserById(int id);

		public void changeUserInfo(User user);
		public void deleteUserById(int id);
		public List<User> getAll();
}

