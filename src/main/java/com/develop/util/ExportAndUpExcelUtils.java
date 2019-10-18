package com.develop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * ���� & �ϴ� excel������
 * 
 * @author Administrator
 *
 */
@Service
public class ExportAndUpExcelUtils {

	/**
	 * ͨ�ã����� �����˳����� ����Դ������˳��һֱ
	 * 
	 * @param response
	 * @param sheetName  sheetҳ����
	 * @param excelTitle excel�ļ�����
	 * @param titleList  excel sheetҳ�ڱ��� ����
	 * @param userList   ����Դ����
	 */
	public void exportExcel(HttpServletResponse response, String sheetName, String excelTitle, List<String> titleList,
			List<List<String>> dataList) {

		// ����HSSFWorkbook
		HSSFWorkbook wb = getHSSFWorkbook(sheetName, titleList, dataList, null);

		OutputStream os = null;
		// ��Ӧ���ͻ���
		try {
			this.setResponseHeader(response, excelTitle + ".xls");
			os = response.getOutputStream();
			wb.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<String> title, List<List<String>> values,
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

			for (int j = 0; j < values.get(i).size(); j++) {
				// �����ݰ�˳�򸳸���Ӧ���ж���
				row.createCell(j).setCellValue(values.get(i).get(j));
			}
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

}
