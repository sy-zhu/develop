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
 * 导出 & 上传 excel工具类
 * 
 * @author Administrator
 *
 */
@Service
public class ExportAndUpExcelUtils {

	/**
	 * 通用：导出 标题的顺序，需和 数据源中数据顺序一直
	 * 
	 * @param response
	 * @param sheetName  sheet页名称
	 * @param excelTitle excel文件名称
	 * @param titleList  excel sheet页内标题 集合
	 * @param userList   数据源集合
	 */
	public void exportExcel(HttpServletResponse response, String sheetName, String excelTitle, List<String> titleList,
			List<List<String>> dataList) {

		// 创建HSSFWorkbook
		HSSFWorkbook wb = getHSSFWorkbook(sheetName, titleList, dataList, null);

		OutputStream os = null;
		// 响应到客户端
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
	 * 导出Excel
	 * 
	 * @param sheetName sheet名称
	 * @param title     标题
	 * @param values    内容
	 * @param wb        HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<String> title, List<List<String>> values,
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

			for (int j = 0; j < values.get(i).size(); j++) {
				// 将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values.get(i).get(j));
			}
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

}
