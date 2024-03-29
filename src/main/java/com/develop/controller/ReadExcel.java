package com.develop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author hewangtong
 * 
 */
public class ReadExcel {
	// 总行数
	private int totalRows = 0;
	// 总条数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;

	// 构造方法
	public ReadExcel() {
	}

	// 获取总行数
	public int getTotalRows() {
		return totalRows;
	}

	// 获取总列数
	public int getTotalCells() {
		return totalCells;
	}

	// 获取错误信息
	public String getErrorInfo() {
		return errorMsg;
	}

	/**
	 * 读EXCEL文件，获取信息集合
	 * 
	 * @param fielName
	 * @return
	 */
	public List<Map<String, Object>> getExcelInfo(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();// 获取文件名
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			return createExcel(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is          输入流
	 * @param isExcel2003 excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> createExcel(InputStream is, boolean isExcel2003) {
		try {
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			return readExcelValue(wb,0);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取Excel里面客户的信息 TODO
	 * 
	 * @param wb
	 * @return
	 */
	private List<Map<String, Object>> readExcelValue(Workbook wb, int column) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 1 && sheet.getRow(0) != null) {
			if (column == 0) {
				this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			} else {
				this.totalCells = column;
			}
		}
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		// 循环Excel行数
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			// 循环Excel的列
			Map<String, Object> map = new HashMap<String, Object>();
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {	//AZA010 '职称信息主键'
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String name = String.valueOf(cell.getNumericCellValue());
							map.put("aza010", name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));// 名称
						} else {
							map.put("aza010", cell.getStringCellValue());// 名称
						}
					} else if (c == 1) {//  
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String sex = String.valueOf(cell.getNumericCellValue());
							map.put("aza011", sex.substring(0, sex.length() - 2 > 0 ? sex.length() - 2 : 1));// 性别
						} else {
							map.put("aza011", cell.getStringCellValue());// 性别
						}
					} else if (c == 2) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String age = String.valueOf(cell.getNumericCellValue());
							map.put("aza015", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
						} else {
							map.put("aza015", cell.getStringCellValue());// 年龄
						}
					} else if (c == 3) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String age = String.valueOf(cell.getNumericCellValue());
							map.put("AZA012", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
						} else {
							map.put("aza012", cell.getStringCellValue());// 年龄
						}
					} else if (c == 4) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String age = String.valueOf(cell.getNumericCellValue());
							map.put("aza013", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
						} else {
							map.put("aza013", cell.getStringCellValue());// 年龄
						}
					}else if (c == 5) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String age = String.valueOf(cell.getNumericCellValue());
							map.put("aza014", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
						} else {
							map.put("aza014", cell.getStringCellValue());// 年龄
						}
					}else if (c == 6) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String age = String.valueOf(cell.getNumericCellValue());
							map.put("aae100", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
						} else {
							map.put("aae100", cell.getStringCellValue());// 年龄
						}
					}else if (c == 7) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String age = String.valueOf(cell.getNumericCellValue());
							map.put("isleaf", age.substring(0, age.length() - 2 > 0 ? age.length() - 2 : 1));// 年龄
						} else {
							map.put("isleaf", cell.getStringCellValue());// 年龄
						}
					}
				}
			}
			// 添加到list
			userList.add(map);
		}
		return userList;
	}

	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";
			return false;
		}
		return true;
	}

	// @描述：是否是2003的excel，返回true是2003
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	// @描述：是否是2007的excel，返回true是2007
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

}