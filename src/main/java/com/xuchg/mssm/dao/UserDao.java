package com.xuchg.mssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuchg.mssm.module.User;

public interface UserDao {

	public List<User> getUserByPage(@Param("page")int page,@Param("sum")int sum);

	public List<User> getPage();

	public void addUser(@Param("user")User user);

	public List<User> getUserById(@Param("id")int id);

	public void changeUserInfo(@Param("user")User user);

	public void deleteUserById(@Param("id")int id);

}

