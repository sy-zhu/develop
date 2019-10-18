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

		// excel ͷ
		List<String> title = new ArrayList<String>();
		title.add("�û�ID");
		title.add("�û�����");
		title.add("�û�����");
		title.add("�û�����");
		title.add("�û���ַ");

		String name = StringUtils.isBlank(user.getUsername()) ? null : user.getUsername();

		// ����
		List<User> list = userInfoDao.findForExcel(name);

		// ����HSSFWorkbook
		HSSFWorkbook wb = getHSSFWorkbook("ҳ��1", title, list, null);

		// ��Ӧ���ͻ���
		try {
			this.setResponseHeader(response, "�û���Ϣ.xls");
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����Excel
	 * 
	 * @param sheetName sheet����
	 * @param title     ����
	 * @param values    ����
	 * @param wb        HSSFWorkbook����
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<String> title, List<User> values,
			HSSFWorkbook wb) {

		// ��һ��������һ��HSSFWorkbook����Ӧһ��Excel�ļ�
		if (wb == null) {
			wb = new HSSFWorkbook();
		}

		// �ڶ�������workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������
		HSSFRow row = sheet.createRow(0);

		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();

		// �����ж���
		HSSFCell cell = null;

		// ��������
		for (int i = 0; i < title.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(title.get(i));
			cell.setCellStyle(style);
		}

		// ��������
		for (int i = 0; i < values.size(); i++) {
			row = sheet.createRow(i + 1);

			// �����ݰ�˳�򸳸���Ӧ���ж���
			row.createCell(0).setCellValue(values.get(i).getId());
			row.createCell(1).setCellValue(values.get(i).getUsername());
			row.createCell(2).setCellValue(values.get(i).getPwd());
			row.createCell(3).setCellValue(values.get(i).getAge());
			row.createCell(4).setCellValue(values.get(i).getAddr());

		}
		return wb;
	}

	// ������Ӧ������
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
	 * �жϸ��û��Ƿ��Ѵ���
	 * 
	 * true:�Ѵ��ڣ� false: ������
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
