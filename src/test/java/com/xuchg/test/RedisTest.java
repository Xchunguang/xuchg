package com.xuchg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xuchg.mssm.tool.JedisClient;

/**
 * @author xuchg1
 * redis连接测试
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:config/spring/springContext.xml"})  
public class RedisTest {

	@Autowired
	JedisClient jedisClient;
	
	@Test
	public void testOrignRedis(){
		jedisClient.set("password1", "13256666666666");
		String password = jedisClient.get("password");
		System.out.println(password);
	}
}
