package com.develop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.develop.model.Model;
import com.develop.model.RestBody;
import com.develop.model.User;
import com.develop.service.impl.ExportExcelMgrImpl; 

/**
 * ��ҳ - excel ���ݴ���
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/develop/testforexcel")
public class TestForExcel {

	@Autowired
	ExportExcelMgrImpl excelMgrImpl; 

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

			Model model = new Model();
			model.setAae100((String)user.get("aae100"));
			model.setAza010((String)user.get("aza010"));
			model.setAza011((String)user.get("aza011"));
			model.setAza012((String)user.get("aza012"));
			model.setAza013((String)user.get("aza013"));
			model.setAza014((String)user.get("aza014"));
			model.setAza015((String)user.get("aza015"));
			model.setIsleaf((String)user.get("isleaf"));
 
			boolean falg =true;
			// falg = excelMgrImpl.isUserExist(userNew);

			if (falg) {

				int addRes = excelMgrImpl.addJobtitle(model);
 
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