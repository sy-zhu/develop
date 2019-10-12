package com.develop.service;

import java.util.List;
import java.util.Map;

import com.develop.model.RestBody;
import com.develop.model.TestModel;
import com.develop.model.User;

/**
 * �û���Ϣ
 * 
 * @author Administrator
 *
 */
public interface IUserInfoMsg {

	/**
	 * �����û���Ϣ��ѯ���û�
	 * 
	 * ��ע���粻���û��������ѯ�����û���Ϣ
	 * 
	 * @param username �û�����
	 * @return �û���Ϣ�б�
	 */
	public List<User> queryUsersByName(String username);

	/**
	 * У���û��Ƿ����Ȩ��
	 * 
	 * @param user
	 * @return 1:��ͨ�û���2������Ա�û�, 3:���޴���
	 */
	public String checkUserAuth(User user);

	/**
	 * �����û�
	 * 
	 * @param user �û���Ϣ
	 * @return �ɹ�: 1
	 */
	public int addUser(User user);

	/**
	 * ɾ���û�
	 * 
	 * @param user �û���Ϣ
	 * @return �ɹ�: 1
	 */
	public RestBody deleteUserById(String userIds);

	public List<TestModel> test(User user);

}
