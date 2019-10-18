package com.develop.controller.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.develop.model.User;
import com.develop.service.IUserInfoMsg;

/**
 * ��ҳ
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/home")
public class UserAuthAppController {

	private static final Logger LOGGER = Logger.getLogger(UserAuthAppController.class);

	@Autowired
	IUserInfoMsg userInfoMsg;

	@PostMapping("/checkpage")
	public String checkUserAuth(User user, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
 
		LOGGER.info("入参为：" + user);

		// У���û��Ƿ����Ȩ��
		String usersRole = userInfoMsg.checkUserAuth(user);

		if ("1".equals(usersRole)) {
			// ��ͨ�û���������Ϣչʾ����
			return "home";
		}
		else if ("2".equals(usersRole)) {
			// ����Ա�û�������������
			return "treePage";
		}

		// û��Ȩ�ޣ��򷵻ص�½����
		return "redirect:/page/login.html";

	}

}