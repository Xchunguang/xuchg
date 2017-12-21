package com.xuchg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xuchg.mssm.module.User;
import com.xuchg.mssm.tool.JedisClient;
import com.xuchg.mssm.tool.SerializeUtil;

/**
 * 测试Java序列化.
 * Java序列化主要应用对象持久化，远程传输对象.
 * 
 * @author xuchg1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:config/spring/springContext.xml"})  
public class SerializeTest {

	@Autowired
	JedisClient jedisClient;
	
	/**
	 * 将对象序列化为byte[]，进行持久化和远程传输.
	 * toString()是不对的<p>
	 */
	@Test
	public void serializeToRedis(){
		User user = new User("xcg","123456");
		//序列化对象
		byte[] userByte = SerializeUtil.serialize(user);
		jedisClient.set("xuchg".getBytes(),userByte);
		byte[] resultUserByte = jedisClient.get("xuchg".getBytes());
		//反序列化对象
		User toUser = (User)SerializeUtil.unserialize(userByte);
		
		User fromRedisUser = (User)SerializeUtil.unserialize(resultUserByte);
		System.out.println(toUser);
		System.out.println(fromRedisUser);
	}
	

}
