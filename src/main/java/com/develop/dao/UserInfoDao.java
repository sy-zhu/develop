package com.develop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.develop.model.Model;
import com.develop.model.TestModel;
import com.develop.model.User;



/**
 * 用户信息列表
 * 
 * @author Administrator
 *
 */
public interface UserInfoDao {

	public User checkUserAuth(User user);

	public List<User> findForExcel(@Param("username") String username);

	/**
	 * 根据用户信息查询该用户
	 * 
	 * 备注：如不传用户名，则查询所有用户信息
	 * 
	 * @param username 用户姓名
	 * @return 用户信息列表
	 */
	public List<User> queryUsersByName(@Param("username") String username);

	int addUser(User user);

	/**
	 * excel 导入用户前，判断该用户是否存在
	 * 
	 * @param user
	 * @return
	 */
	User isUserExist(User user);

	/**
	 * 删除用户
	 * 
	 * @param user 用户信息
	 * @return 成功: 1
	 */
	public int deleteUserById(@Param("id") String userId);

//	@MapKey("id")
	public List<TestModel> test(@Param("id") String id);
	

	int addJobtitle(Model model);
	 

}
