package com.xuchg.mssm.tool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * redis 操作service.
 * 假设将用户的登录信息存储在redis中，初始设置一个失效时间，
 * 如果在失效时间内用户有请求信息，则刷新失效时间，如果在失
 * 效时间内用户未再有任何请求，则视为用户下线，key被自动删除
 * @author xuchg1
 *
 */
@Service
public class JedisClientSingle implements JedisClient {

	/**
	 * redis 的key过期时间
	 */
	protected static Integer KEY_ALIVE_SECOND = 60*60;
	
	@Bean
    public JedisPool getJedisPool(){
        return new JedisPool("47.94.217.138",6379);
    }
    public String get(String key) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.get(key);
        if(string != null){
        	jedis.expire(key, KEY_ALIVE_SECOND);
        }
        jedis.close();
        return string;
    }

    public String set(String key, String value) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.set(key, value);
        /**
         * 思路：当存储进redis，设置其key存活时间，而当get key的时候再刷新其存活时间
         */
        if(string.toLowerCase().equals("ok")){
        	jedis.expire(key, KEY_ALIVE_SECOND);
        }
        jedis.close();
        return string;
    }

    public String hget(String hkey, String key) {
        System.out.println("jedisPool   "+getJedisPool());
        Jedis jedis = getJedisPool().getResource();
        System.out.println("jedis   "+jedis);
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    public long hset(String hkey, String key, String value) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    public long incr(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    public long expire(String key, int second) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    public long ttl(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    public long del(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    public long hdel(String hkey, String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.hdel(hkey,key);
        jedis.close();
        return result;
    }
	public String set(byte[] key, byte[] value) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.set(key, value);
        if(string.toLowerCase().equals("ok")){
        	jedis.expire(key, KEY_ALIVE_SECOND);
        }
        jedis.close();
        return string;
	}
	public byte[] get(byte[] key) {
		Jedis jedis = getJedisPool().getResource();
        byte[] value = jedis.get(key);
        if(value.length>0){
        	jedis.expire(key, KEY_ALIVE_SECOND);
        }
        jedis.close();
        return value;
	}
	public long del(byte[] key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
	}

}
