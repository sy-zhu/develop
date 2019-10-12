package com.develop.service.impl;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.dao.UserInfoDao;
import com.develop.model.Model;
import com.develop.model.User;
import com.develop.service.IExportExcelMgr;

@Service
public class ExportExcelMgrImpl implements IExportExcelMgr {

	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public void exportExcel(HttpServletResponse response, User user) {

		// excel 头
		List<String> title = new ArrayList<String>();
		title.add("用户ID");
		title.add("用户姓名");
		title.add("用户密码");
		title.add("用户年龄");
		title.add("用户地址");

		String name = StringUtils.isBlank(user.getUsername()) ? null : user.getUsername();

		// 导出
		List<User> list = userInfoDao.findForExcel(name);

		// 创建HSSFWorkbook
		HSSFWorkbook wb = getHSSFWorkbook("页码1", title, list, null);

		// 响应到客户端
		try {
			this.setResponseHeader(response, "用户信息.xls");
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导出Excel
	 * 
	 * @param sheetName sheet名称
	 * @param title     标题
	 * @param values    内容
	 * @param wb        HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<String> title, List<User> values,
			HSSFWorkbook wb) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (wb == null) {
			wb = new HSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();

		// 声明列对象
		HSSFCell cell = null;

		// 创建标题
		for (int i = 0; i < title.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(title.get(i));
			cell.setCellStyle(style);
		}

		// 创建内容
		for (int i = 0; i < values.size(); i++) {
			row = sheet.createRow(i + 1);

			// 将内容按顺序赋给对应的列对象
			row.createCell(0).setCellValue(values.get(i).getId());
			row.createCell(1).setCellValue(values.get(i).getUsername());
			row.createCell(2).setCellValue(values.get(i).getPwd());
			row.createCell(3).setCellValue(values.get(i).getAge());
			row.createCell(4).setCellValue(values.get(i).getAddr());

		}
		return wb;
	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public int addUser(User user) {

		return userInfoDao.addUser(user);

	}

	/**
	 * 判断该用户是否已存在
	 * 
	 * true:已存在； false: 不存在
	 */
	@Override
	public boolean isUserExist(User user) {

		User users = userInfoDao.isUserExist(user);

		if (users != null && StringUtils.isNotBlank(users.getId() + "")) {
			return true;
		}
		return false;

	}

	@Override
	public int addJobtitle(Model model) {
		return userInfoDao.addJobtitle(model);
	}

}
