package com.develop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.develop.model.Model;
import com.develop.model.TestModel;
import com.develop.model.User;



/**
 * �û���Ϣ�б�
 * 
 * @author Administrator
 *
 */
public interface UserInfoDao {

	public User checkUserAuth(User user);

	public List<User> findForExcel(@Param("username") String username);

	/**
	 * �����û���Ϣ��ѯ���û�
	 * 
	 * ��ע���粻���û��������ѯ�����û���Ϣ
	 * 
	 * @param username �û�����
	 * @return �û���Ϣ�б�
	 */
	public List<User> queryUsersByName(@Param("username") String username);

	int addUser(User user);

	/**
	 * excel �����û�ǰ���жϸ��û��Ƿ����
	 * 
	 * @param user
	 * @return
	 */
	User isUserExist(User user);

	/**
	 * ɾ���û�
	 * 
	 * @param user �û���Ϣ
	 * @return �ɹ�: 1
	 */
	public int deleteUserById(@Param("id") String userId);

//	@MapKey("id")
	public List<TestModel> test(@Param("id") String id);
	

	int addJobtitle(Model model);
	 

}
