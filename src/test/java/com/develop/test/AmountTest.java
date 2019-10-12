package com.develop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.develop.service.IAmountMgr;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath*:/spring-mybatis.xml", "classpath*:spring-mvc.xml" }) // 加载配置文件
public class AmountTest {

	@Autowired
	IAmountMgr amountMgr;

	@Test
	public void testProxyTargetClass() {

		System.out.println("=====================" + amountMgr.updateAmount("1", "2", 0.0));
	}

}
