package com.develop.util;

import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import test.RedisPool;

public class RedisUtil {

	private static ShardedJedisPool pool;

	static {
		JedisPoolConfig config = new JedisPoolConfig();

		// 最大空闲连接数, 默认100个
		config.setMaxIdle(100);

		// 最小空闲连接数, 默认10个
		config.setMinIdle(0);

		// 最大连接数, 默认150个
		config.setMaxTotal(150);

		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(true);

		// 向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除
		// 默认值false; 业务量很大时候建议设置为false(多一次ping的开销)。
		config.setTestOnBorrow(true);

		// 向资源池归还连接时是否做连接有效性检测(ping)，无效连接会被移除
		// 默认值false; 业务量很大时候建议设置为false(多一次ping的开销)。
		config.setTestOnReturn(true);

		// 集群
		JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.130.64", 6379);
		jedisShardInfo1.setPassword("123456");
		List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
		list.add(jedisShardInfo1);
		pool = new ShardedJedisPool(config, list);
	}

	private RedisUtil() {
	}

	public static ShardedJedis getInstance() {

		return pool.getResource();
	}

	public void demo() {

		RedisPool.getJedis().set("name", "测试-value");
		System.out.println(RedisPool.getJedis().get("name"));
	}
}
