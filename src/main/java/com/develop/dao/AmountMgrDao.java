package com.develop.dao;

import org.apache.ibatis.annotations.Param;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public interface AmountMgrDao {

	/**
	 * �ı���
	 * 
	 * @return
	 */
	public int updateAmount(@Param("userId") String userId, @Param("amount") double amount);

}
