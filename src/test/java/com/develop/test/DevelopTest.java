package com.develop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.develop.model.User;
import com.develop.service.IUserInfoMsg;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:/spring-mybatis.xml", "classpath:spring-mvc.xml" }) // 加载配置文件
public class DevelopTest {

	@Autowired
	IUserInfoMsg userInfoMsg;

	@Test
	public void testProxyTargetClass() {
		User user = new User();
		user.setPwd("123456");
		user.setUsername("张三");

		System.out.println("=====================" + userInfoMsg.checkUserAuth(user));
	}
	
	
}
