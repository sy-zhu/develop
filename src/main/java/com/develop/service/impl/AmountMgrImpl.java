package com.develop.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.dao.AmountMgrDao;
import com.develop.service.IAmountMgr;

/**
 * ������
 * 
 * @author Administrator
 *
 */
@Service
public class AmountMgrImpl implements IAmountMgr {

	@Autowired
	AmountMgrDao amountMgrDao;

	/**
	 * ���ı�
	 */
	@Override
	public int updateAmount(String userId, String toUserId, double amount) {

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(toUserId) || amount==0 ) {
			return 0;
		}

		// ����ĳ�û����
		amountMgrDao.updateAmount(userId, amount);

		// �����û����
		amountMgrDao.updateAmount(toUserId, amount);

		return 0;

	}

}
