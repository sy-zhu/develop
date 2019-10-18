package com.develop.service;

import java.util.List;
import java.util.Map;

import com.develop.model.RestBody;
import com.develop.model.TestModel;
import com.develop.model.User;

/**
 * 用户信息
 * 
 * @author Administrator
 *
 */
public interface IUserInfoMsg {

	/**
	 * 根据用户信息查询该用户
	 * 
	 * 备注：如不传用户名，则查询所有用户信息
	 * 
	 * @param username 用户姓名
	 * @return 用户信息列表
	 */
	public List<User> queryUsersByName(String username);

	/**
	 * 校验用户是否具有权限
	 * 
	 * @param user
	 * @return 1:普通用户，2：管理员用户, 3:查无此人
	 */
	public String checkUserAuth(User user);

	/**
	 * 新增用户
	 * 
	 * @param user 用户信息
	 * @return 成功: 1
	 */
	public int addUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param user 用户信息
	 * @return 成功: 1
	 */
	public RestBody deleteUserById(String userIds);

	public List<TestModel> test(User user);

}
