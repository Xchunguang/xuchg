package com.xuchg.test;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuchg.mssm.module.User;
import com.xuchg.mssm.tool.JedisClient;

/**
 * 对象转换json测试.
 * @author xuchg1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/springContext.xml"})  
public class JsonConvert {

	@Autowired
	JedisClient jedisClient;
	
	/**
	 * 将对象序列化成json.
	 * @throws IOException 
	 */
	@Test
	public void testConvertToJson() throws IOException{
		User user = new User("xcg","123456");
		ObjectMapper om = new ObjectMapper();
		String result = om.writeValueAsString(user);
		System.out.println(result);
		
		User curUser = om.readValue(result, User.class);
		System.out.println(curUser);
	}
	
	/**
	 * 将json反序列化成对象.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void testConvertFromJson() throws JsonParseException, JsonMappingException, IOException{
		String userJson = "{\"id\":null,\"userName\":\"xcg\",\"passWord\":\"123456\",\"balance\":null}";
		ObjectMapper om = new ObjectMapper();
		User user = om.readValue(userJson, User.class);
		System.out.println(user);
	}
	
	
	/**
	 * 将对象序列化为json并存储进redis.
	 * 从redis中取出json，并将json转化为对象.
	 * @throws IOException
	 */
	@Test
	public void jsonThrRedis() throws IOException{
		User user = new User("xcg","123456");
		ObjectMapper om = new ObjectMapper();
		
		//获得对象的json并存入redis
		String userJson = om.writeValueAsString(user);
		jedisClient.set(user.getUserName(), userJson);
		
		//从redis读取json并转化为对象
		String curUserJson = jedisClient.get("xcg");
		User curUser = (User)om.readValue(curUserJson, User.class);
		System.out.println(curUser);
		
	}
	
}
