package com.kuyu.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.exception.ParamException;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.service.TpmDeptService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Excel存入数据库")
@AOP_Controller_LOG
@RequestMapping("/excelToSql")
public class ExcelToSqlController {

	@Autowired
	private TpmEmployeeService tpmEmployeeService;
	
	@Autowired
	private TpmDeptService tpmDeptService;
	
	@ApiOperation("导入信息")
	@RequestMapping(value = "/excelToSql" , method = {RequestMethod.GET})
	public ResultVo excelToSql(@ApiParam(value = "文件路径及完整文件名(如d盘下dept.xlsx文件,输入D:\\dept.xlsx)", required = true)@RequestParam String filePath,
			@ApiParam(value = "文件名称(部门：dept，人员：employee)", required = true)@RequestParam String title) throws Exception{
		if(StringUtil.isEmpty(filePath)) {
			 throw new ParamException(ResultVoUtils.fail("文件路径及完整文件名为空:"+filePath));
		}
		if(StringUtil.isEmpty(title)) {
			throw new ParamException(ResultVoUtils.fail("文件名称为空:"+filePath));
		}
		if(!filePath.toLowerCase().endsWith(".xls") && !filePath.toLowerCase().endsWith(".xlsx")) {
			throw new ParamException(ResultVoUtils.fail("文件不是Excel:"+filePath));
		}
		if(!title.equals("dept") && !title.equals("employee")) {
			throw new ParamException(ResultVoUtils.fail("文件名称错误:"+filePath));
		}
		Map<String, String> map = readExcel(filePath);
		if(StringUtil.isNotNull(map)) {
			if(title.equals("dept")) {
				List<TpmDeptModel> list = new ArrayList<>();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String value = entry.getValue();
					TpmDeptModel tdm = new TpmDeptModel();
					String[] dept = value.split(",");
					for (int i = 0; i < dept.length; i++) {
						if(dept[i].equals("NULL")) {
							dept[i] = null;
						}
					}
					tdm.setOrg_code(dept[1]);
					tdm.setOrg_name(dept[2]);
					tdm.setOrg_code_parent(dept[3]);
					tdm.setOrg_name_parent(dept[4]);
					tdm.setOrg_type(dept[5]);
					tdm.setUpdate_flag(dept[6]);
					tdm.setUuid(StringUtil.getUUID());
					list.add(tdm);
				}
				tpmDeptService.insertBatch(list);
			}
			if(title.equals("employee")) {
				Map<String,String> map2 = new HashMap<>();
				List<TpmEmployeeModel> list = new ArrayList<>();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String value = entry.getValue();
					String[] employee = value.split(",");
					map2.put(employee[1], value);
				}
				for (Map.Entry<String, String> entry : map2.entrySet()) {
					String value = entry.getValue();
					TpmEmployeeModel tem = new TpmEmployeeModel();
					String[] employee = value.split(",");
					for (int i = 0; i < employee.length; i++) {
						if(employee[i].equals("NULL")) {
							employee[i] = null;
						}
					}
					tem.setPerson_code(employee[1]);
					tem.setPerson_name(employee[2]);
					tem.setItcode(employee[3]);
					if(tem.getItcode() == null) {
						continue;
					}
					tem.setEmail(employee[4]);
					tem.setMobile(employee[5]);
					tem.setZhiwu_code(employee[6]);
					tem.setZhiwu_name(employee[7]);
					tem.setOrg_code(employee[8]);
					if(tem.getOrg_code() == null) {
						continue;
					}
					tem.setOrg_name(employee[9]);
					tem.setCompany(employee[10]);
					tem.setUuid(StringUtil.getUUID());
					list.add(tem);
				}
				tpmEmployeeService.insertBatch(list);
			}
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}

	public static Map<String, String> readExcel(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		Workbook workbook = null;
		// 创建对Excel工作簿文件的引用
		try {
			// filePath是文件地址。
			boolean isExcel2003 = filePath.toLowerCase().endsWith("xls") ? true : false;
			if (isExcel2003) {
				workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
			} else {
				// workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
				FileInputStream input = new FileInputStream(new File(filePath)); // 读取的文件路径
				workbook = new XSSFWorkbook(new BufferedInputStream(input));
			}

			Sheet sheet = workbook.getSheetAt(0); // 创建对工作表的引用

			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			int max_cells = 0;
			// 获取最长的列，在实践中发现如果列中间有空值的话，那么读到空值的地方就停止了。所以我们需要取得最长的列。
			//如果每个列正好都有一个空值得话，通过这种方式获得的列长会比真实的列要少一列。所以我自己会在将要倒入数据库中的EXCEL表加一个表头
			// //防止列少了，而插入数据库中报错。
			for (int i = 0; i < rows; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					if (max_cells < cells) {
						max_cells = cells;
					}

				}
			}
			// 遍历行
			for (int i = 1; i < rows; i++) {//从1开始循环，去掉表头
				// 读取左上端单元格
				Row row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					String value = "";
					// 遍历列
					String b_id = null;
					for (int j = 0; j < max_cells; j++) {
						// 获取到列的值
						Cell cell = row.getCell(j);// 把所有是空值的都换成NULL
						if (cell == null) {
							value += "NULL,";
						} else {
							switch (cell.getCellType()) {// 如果是公式的话，就读取得出的值
							case HSSFCell.CELL_TYPE_FORMULA:
								try {
									value += "'" + String.valueOf(cell.getNumericCellValue()).replaceAll("'", "")
											+ "',";
								} catch (IllegalStateException e) {
									value += "'" + String.valueOf(cell.getRichStringCellValue()).replaceAll("'", "")
											+ "',";
								}
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								// 如果有日期的话，那么就读出日期格式
								// 如果是数字的话，就写出数字格式
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date date2 = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
									String date1 = dff.format(date2);
									value += "'" + date1.replaceAll("'", "") + "',";

								} else {
									value += "'" + (int) cell.getNumericCellValue() + "',";
								}
								break;
							case HSSFCell.CELL_TYPE_STRING:
								String ss = cell.getStringCellValue().replaceAll("'", "");// 如果文本有空值的话，就把它写成null
								if (ss == null || "".equals(ss)) {
									value += "NULL,";
								} else {
									value += "'" + cell.getStringCellValue().replaceAll("'", "") + "',";
								}

								break;
							default:
								value += "NULL,";
								break;
							}
						}

						if (j == 0) {

							b_id = value.substring(1, value.length() - 2);
						}

					}

					String valueresult = value.substring(0, value.length() - 1);
					valueresult = valueresult.replaceAll("'", "");
					map.put(b_id, valueresult);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
