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

		// ������������, Ĭ��100��
		config.setMaxIdle(100);

		// ��С����������, Ĭ��10��
		config.setMinIdle(0);

		// ���������, Ĭ��150��
		config.setMaxTotal(150);

		// ���Ӻľ�ʱ�Ƿ�����, false���쳣,ture����ֱ����ʱ, Ĭ��true
		config.setBlockWhenExhausted(true);

		// ����Դ�ؽ�������ʱ�Ƿ���������Ч�Լ��(ping)����Ч���ӻᱻ�Ƴ�
		// Ĭ��ֵfalse; ҵ�����ܴ�ʱ��������Ϊfalse(��һ��ping�Ŀ���)��
		config.setTestOnBorrow(true);

		// ����Դ�ع黹����ʱ�Ƿ���������Ч�Լ��(ping)����Ч���ӻᱻ�Ƴ�
		// Ĭ��ֵfalse; ҵ�����ܴ�ʱ��������Ϊfalse(��һ��ping�Ŀ���)��
		config.setTestOnReturn(true);

		// ��Ⱥ
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

		RedisPool.getJedis().set("name", "����-value");
		System.out.println(RedisPool.getJedis().get("name"));
	}
}
