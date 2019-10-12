package com.develop.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.dao.AmountMgrDao;
import com.develop.service.IAmountMgr;

/**
 * 金额控制
 * 
 * @author Administrator
 *
 */
@Service
public class AmountMgrImpl implements IAmountMgr {

	@Autowired
	AmountMgrDao amountMgrDao;

	/**
	 * 金额改变
	 */
	@Override
	public int updateAmount(String userId, String toUserId, double amount) {

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(toUserId) || amount==0 ) {
			return 0;
		}

		// 减少某用户金额
		amountMgrDao.updateAmount(userId, amount);

		// 增加用户金额
		amountMgrDao.updateAmount(toUserId, amount);

		return 0;

	}

}
