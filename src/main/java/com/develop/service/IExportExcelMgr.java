package com.develop.service;

import javax.servlet.http.HttpServletResponse;

import com.develop.model.Model;
import com.develop.model.User;

public interface IExportExcelMgr {
	void exportExcel(HttpServletResponse responseUser, User user);

	int addUser(User user);

	/**
	 * excel 导入用户前，判断该用户是否存在
	 * 
	 * @param user
	 * @return
	 */
	boolean isUserExist(User user);
	
	
	int addJobtitle(Model model);
}
