package com.develop.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 金额控制
 * 
 * @author Administrator
 *
 */
public interface AmountMgrDao {

	/**
	 * 改变金额
	 * 
	 * @return
	 */
	public int updateAmount(@Param("userId") String userId, @Param("amount") double amount);

}
