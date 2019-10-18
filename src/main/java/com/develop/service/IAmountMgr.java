package com.develop.service;

/**
 * 金额管理
 * 
 * @author Administrator
 *
 */
public interface IAmountMgr {

	/**
	 * 待改变的金额
	 * 
	 * @return
	 */
	public int updateAmount(String userId, String toUserId, double amount);

}
