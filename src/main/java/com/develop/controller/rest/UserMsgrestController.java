package com.develop.controller.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.develop.model.RestBody;
import com.develop.model.User;
import com.develop.service.IUserInfoMsg;
import com.develop.service.impl.ExportExcelMgrImpl;

/**
 * ��ҳ - �û�����չʾ
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/develop/userMsgRestController")
public class UserMsgrestController {

	@Autowired
	IUserInfoMsg userInfoMsg;

	@Autowired
	ExportExcelMgrImpl excelMgrImpl;

	/**
	 * ��ѯ�û���Ϣ
	 * 
	 * @param user
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/findUsers")
	public List<User> findUsers(User user, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String name = StringUtils.isBlank(user.getUsername()) ? null : user.getUsername();

		List<User> userList = userInfoMsg.queryUsersByName(name);

		System.out.println(userList);

		return userList;
	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public RestBody addUser(User user) throws IOException, ServletException {

		// У����û��Ƿ��Ѵ���
		boolean falg = excelMgrImpl.isUserExist(user);

		RestBody restBody = new RestBody();
		if (falg) {

			restBody.setCode(-1);
			restBody.setBody("���û��Ѵ���");

			return restBody;
		}

		// �������û�
		int addRes = userInfoMsg.addUser(user);

		if (addRes == 1) {
			restBody.setCode(addRes);
			restBody.setBody("�����û��ɹ�");
		} else {
			restBody.setCode(addRes);
			restBody.setBody("�����û�ʧ��");
		}

		return restBody;
	}

	/**
	 * ɾ���û���Ϣ
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public RestBody deleteUser(String userIds) throws IOException, ServletException {

		// ɾ�����û�
		RestBody restBody = userInfoMsg.deleteUserById(userIds);
    
		return restBody;
	}
}