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
 * ��ҳ - excel ���ݴ���
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
	 * ����
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
//		// excel ͷ
//		List<String> title = new ArrayList<String>();
//		title.add("�û�ID");
//		title.add("�û�����");
//		title.add("�û�����");
//		title.add("�û�����");
//		title.add("�û���ַ");
//		exportAndUpExcelUtils.exportExcel(response, title, userList);
//
//		return "�����ɹ�";
//	}

	/**
	 * ͨ�õ���-����
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
		title.add("�û�ID");
		title.add("�û�����");
		title.add("�û�����");
		title.add("�û�����");
		title.add("�û���ַ");

		// ����
		List<User> list = userInfoDao.findForExcel(user.getUsername());
		if (list.isEmpty()) {
			return "û�и����ݣ��޷�����";
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

		exportAndUpExcelUtils.exportExcel(response, "��ҳ", "�û���Ϣ�б�", title, dataList);

		return "�����ɹ�";
	}

	// ����excel
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
		// ��������EXCEL����
		ReadExcel readExcel = new ReadExcel();
		// ����excel����ȡ�ϴ����¼���
		List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
		// �����Ѿ���excel�е�����ת����list������,�������Ϳ��Բ���list,���Խ��б��浽���ݿ�,������������,
		for (Map<String, Object> user : userList) {

			// �������ݿ�
			System.out.println("User�����ݿ�:=======================" + user);

			User userNew = new User();

			userNew.setUsername((String) user.get("username"));
			userNew.setPwd((String) user.get("pwd"));
			userNew.setAge((String) user.get("age"));
			userNew.setAddr((String) user.get("addr"));

			boolean falg = excelMgrImpl.isUserExist(userNew);

			if (!falg) {

				int addRes = excelMgrImpl.addUser(userNew);

				System.out.println("excel ����:" + userNew + "  ���Ϊ��" + addRes);
			}

		}
		if (userList != null && !userList.isEmpty()) {
			result = "�ϴ��ɹ�";
		} else {
			result = "�ϴ�ʧ��";
		}
		return result;
	}

}