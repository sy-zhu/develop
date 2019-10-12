package com.develop.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.model.User;
import com.develop.service.IAmountMgr;

/**
 * 金额控制
 * 
 * @author zhusy
 *
 */

@RestController
@RequestMapping
public class AmountRestController {

	@Autowired
	IAmountMgr amountMgr;

	/**
	 * 改变金额
	 * 
	 * @return
	 */
	public int updateAmount(User user, User toUser) {

		amountMgr.updateAmount(user.getId(), toUser.getId(), 0);

		return 0;
	}

}
