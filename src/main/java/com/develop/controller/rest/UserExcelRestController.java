package com.develop.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.develop.dao.UserInfoDao;
import com.develop.model.RestBody;
import com.develop.model.User;
import com.develop.service.impl.ExportExcelMgrImpl;
import com.develop.util.ExportAndUpExcelUtils;
import com.develop.util.ReadExcel;

/**
 * 首页 - excel 数据处理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/develop/excelRestController")
public class UserExcelRestController {

	@Autowired
	ExportExcelMgrImpl excelMgrImpl;

	@Autowired
	ExportAndUpExcelUtils exportAndUpExcelUtils;

	@Autowired
	UserInfoDao userInfoDao;

	/**
	 * 导出
	 * 
	 * @param user
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
//	@RequestMapping("/exportExcel")
//	public String exportExcel(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//
//		excelMgrImpl.exportExcel(response, user);
//		
//		
//		// excel 头
//		List<String> title = new ArrayList<String>();
//		title.add("用户ID");
//		title.add("用户姓名");
//		title.add("用户密码");
//		title.add("用户年龄");
//		title.add("用户地址");
//		exportAndUpExcelUtils.exportExcel(response, title, userList);
//
//		return "导出成功";
//	}

	/**
	 * 通用导出-测试
	 * 
	 * @param user
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/exportExcel")
	public String exportExcelTest(User user, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> title = new ArrayList<String>();
		title.add("用户ID");
		title.add("用户姓名");
		title.add("用户密码");
		title.add("用户年龄");
		title.add("用户地址");

		// 导出
		List<User> list = userInfoDao.findForExcel(user.getUsername());
		if (list.isEmpty()) {
			return "没有该数据，无法导出";
		}

		List<List<String>> dataList = new ArrayList<>();
		for (User data : list) {
			List<String> listData = new ArrayList<>();
			listData.add(data.getId());
			listData.add(data.getUsername());
			listData.add(data.getPwd());
			listData.add(data.getAge());
			listData.add(data.getAddr());

			dataList.add(listData);
		}

		exportAndUpExcelUtils.exportExcel(response, "首页", "用户信息列表", title, dataList);

		return "导出成功";
	}

	// 导入excel
	@RequestMapping(value = "/improtExcel", method = RequestMethod.POST)
	public RestBody importExcel(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		String result = readExcelFile(file);

		RestBody restBody = new RestBody();

		restBody.setBody(result);

		return restBody;
	}

	private String readExcelFile(MultipartFile file) {
		String result = "";
		// 创建处理EXCEL的类
		ReadExcel readExcel = new ReadExcel();
		// 解析excel，获取上传的事件单
		List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
		// 至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
		for (Map<String, Object> user : userList) {

			// 插入数据库
			System.out.println("User入数据库:=======================" + user);

			User userNew = new User();

			userNew.setUsername((String) user.get("username"));
			userNew.setPwd((String) user.get("pwd"));
			userNew.setAge((String) user.get("age"));
			userNew.setAddr((String) user.get("addr"));

			boolean falg = excelMgrImpl.isUserExist(userNew);

			if (!falg) {

				int addRes = excelMgrImpl.addUser(userNew);

				System.out.println("excel 导入:" + userNew + "  结果为：" + addRes);
			}

		}
		if (userList != null && !userList.isEmpty()) {
			result = "上传成功";
		} else {
			result = "上传失败";
		}
		return result;
	}

}