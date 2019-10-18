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
 * 首页 - excel 数据处理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/develop/testforexcel")
public class TestForExcel {

	@Autowired
	ExportExcelMgrImpl excelMgrImpl; 

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
			result = "上传成功";
		} else {
			result = "上传失败";
		}
		return result;
	}

}