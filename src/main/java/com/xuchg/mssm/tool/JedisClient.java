package com.xuchg.mssm.tool;

public interface JedisClient {
	public String get(String key);
    public String set(String key, String value);
    public String hget(String hkey, String key);
    public long hset(String hkey, String key, String value);
    public long incr(String key);
    public long expire(String key, int second);
    public long ttl(String key);
    public long del(String key);
    public long hdel(String hkey, String key);
    public String set(byte[] key,byte[] value);
    public byte[] get(byte[] key);
    public long del(byte[] key);
}
