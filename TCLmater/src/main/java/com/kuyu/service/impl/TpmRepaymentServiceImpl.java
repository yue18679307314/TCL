package com.kuyu.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.TpmAttendenceMapper;
import com.kuyu.mapper.TpmEmployeeMapper;
import com.kuyu.mapper.TpmLoanBillMapper;
import com.kuyu.mapper.TpmRepaymentMapper;
import com.kuyu.mapper.TpmUserStatementMapper;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmAttendenceModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.TpmLoanBillModel;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmRepaymentFeeDetailModel;
import com.kuyu.model.TpmRepaymentModel;
import com.kuyu.model.TpmRepaymentPayInfoModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.service.TpmAttendenceService;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.service.TpmRepaymentService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.RSAUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.RepaymentManagementReturnVo;
import com.kuyu.vo.RepaymentManagementVo;
import com.kuyu.vo.RepaymentVo;
import com.kuyu.vo.ResultVo;

/**
 * @Author tang_zhen
 * @Date 2017/9/30
 * @Description 报销单操作的实现类
 */
@Service
@Transactional
public class TpmRepaymentServiceImpl extends ServiceImpl<TpmRepaymentMapper, TpmRepaymentModel>
		implements TpmRepaymentService {

	/**
	 * 接收报销单数据存入数据库
	 * 
	 * @param tpmOptLogsModel
	 * @return ResultVo
	 */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${attendence_path}")
	private String attendencePath;
	
	@Value("${attendence_url}")
	private String attendenceUrl;

	@Autowired
	private TpmRepaymentMapper tpmRepaymentMapper;

	@Autowired
	private UserRoleInfoMapper userRoleInfoMapper;

	@Autowired
	private TpmAttendenceService tpmAttendenceService;

	@Autowired
	private TpmAttendenceMapper tpmAttendenceMapper;

	@Autowired
	private TpmEmployeeMapper tpmEmployeeMapper;

	@Autowired
	private TpmOptLogsService tpmOptLogsService;

	@Autowired
	private TpmLoanBillMapper tpmLoanBillMapper;
	
	@Autowired
	private TpmUserStatementMapper tpmUserStatementMapper;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;

	private static List<String> tusUuidList = new ArrayList<>();

	private static int no = 1;

	private static String date = DateUtils.today();

	@Override
	public FinancialResultVo insertRepayment(TpmRepaymentModel tpmRepaymentModel) throws Exception {
		// 调用借款单接口，将数据库表TPM_LOAN_BILL中status字段标记为已报销（）
		List<TpmRepaymentFeeDetailModel> tpmRepaymentFeeDetailModelList = tpmRepaymentModel
				.getTpmRepaymentFeeDetailModelList();
		List<TpmRepaymentPayInfoModel> tpmRepaymentPayInfoModelList = tpmRepaymentModel
				.getTpmRepaymentPayInfoModelList();
		tpmRepaymentModel.setNote("费用已报销");
		tpmRepaymentMapper.insertRepaymentData(tpmRepaymentModel);
		for (TpmRepaymentFeeDetailModel tpmRepaymentFeeDetailModel : tpmRepaymentFeeDetailModelList) {
			tpmRepaymentFeeDetailModel.setRequestId(tpmRepaymentModel.getRequestId());
			tpmRepaymentMapper.insertRepaymentFeeDetailData(tpmRepaymentFeeDetailModel);
		}
		for (TpmRepaymentPayInfoModel tpmRepaymentPayInfoModel : tpmRepaymentPayInfoModelList) {
			tpmRepaymentPayInfoModel.setRequestId(tpmRepaymentModel.getRequestId());
			tpmRepaymentMapper.insertRepaymentPayInfoData(tpmRepaymentPayInfoModel);
		}

		return FinancialResultVo.get(FinancialResultVo.SUCCESS);
	}

	@Override
	public Page<TpmRepaymentModel> queryRepayment(RepaymentVo repaymentVo, LoginUserInfo loginUserInfo) {
		Integer personType = CheckParamUtils.getPersonType(loginUserInfo);
		Page<TpmRepaymentModel> page = new Page<TpmRepaymentModel>(repaymentVo.getCurrent(), repaymentVo.getSize());
		repaymentVo.setPersonCode(loginUserInfo.getEmployeeModel().getPerson_code());
		repaymentVo.setRequestTitle(StringUtil.trim(repaymentVo.getRequestTitle()));
		if (personType == 1) {
			List<TpmRepaymentModel> tpmRepaymentModelList = tpmRepaymentMapper.selectRepaymentList(repaymentVo);
			if (tpmRepaymentModelList == null) {
				page.setRecords(null);
				return page;
			}

			for (TpmRepaymentModel tpmRepaymentModel : tpmRepaymentModelList) {
				String requestUserName = tpmRepaymentMapper.selectRequestUserName(tpmRepaymentModel.getRequestUser());
				if (requestUserName == null) {

				} else {
					tpmRepaymentModel.setRequestUser(requestUserName);
				}

				tpmRepaymentModel.setTpmRepaymentFeeDetailModelList(
						tpmRepaymentMapper.selectRepaymentFeeDetailList(tpmRepaymentModel.getRequestId()));

				tpmRepaymentModel.setTpmRepaymentPayInfoModelList(
						tpmRepaymentMapper.selectRepaymentPayInfoList(tpmRepaymentModel.getRequestId()));
			}
			page.setRecords(tpmRepaymentModelList);
			return page;
		} else if (personType == 2) {
			List<TpmRepaymentModel> tpmRepaymentModelList = tpmRepaymentMapper.financeSelectRepaymentList(repaymentVo);
			if (tpmRepaymentModelList == null) {
				page.setRecords(null);
				return page;
			}

			for (TpmRepaymentModel tpmRepaymentModel : tpmRepaymentModelList) {
				tpmRepaymentModel.setTpmRepaymentFeeDetailModelList(
						tpmRepaymentMapper.selectRepaymentFeeDetailList(tpmRepaymentModel.getRequestId()));
				tpmRepaymentModel.setTpmRepaymentPayInfoModelList(
						tpmRepaymentMapper.selectRepaymentPayInfoList(tpmRepaymentModel.getRequestId()));
			}
			page.setRecords(tpmRepaymentModelList);
			return page;
		} else if (personType == 4) {
			List<TpmRepaymentModel> tpmRepaymentModelList = tpmRepaymentMapper
					.activityOrProjectSelectRepaymentList(repaymentVo);
			if (tpmRepaymentModelList == null) {
				page.setRecords(null);
				return page;
			}

			for (TpmRepaymentModel tpmRepaymentModel : tpmRepaymentModelList) {
				tpmRepaymentModel.setTpmRepaymentFeeDetailModelList(
						tpmRepaymentMapper.selectRepaymentFeeDetailList(tpmRepaymentModel.getRequestId()));
				tpmRepaymentModel.setTpmRepaymentPayInfoModelList(
						tpmRepaymentMapper.selectRepaymentPayInfoList(tpmRepaymentModel.getRequestId()));
			}
			page.setRecords(tpmRepaymentModelList);
			return page;
		}
		page.setRecords(null);
		return page;
	}

	@Override
	public Page<RepaymentManagementReturnVo> queryRepaymentManagementList(RepaymentManagementVo rmv,
			LoginUserInfo userInfo) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if (personType == -1) {
			log.info(CommonConstants.NOT_PERMISSION_MSG);
			return null;
		} 
		if (personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if (childsDeptList == null || childsDeptList.size() == 0) {
				log.info("部门列表错误，为{}", childsDeptList);
				return null;
			}
			rmv.setList(childsDeptList);
		}
		if(personType == 0 || personType == 6){
		  	List<String> branchDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(branchDeptList == null || branchDeptList.size() == 0) {
			log.info("部门列表branchDeptList错误，为{}",branchDeptList);
			return null;
			}
			rmv.setList(branchDeptList);
		}

		rmv.setPersonCode(userInfo.getEmployeeModel().getPerson_code());
		rmv.setPersonType(personType);
		Page<RepaymentManagementReturnVo> page = new Page<RepaymentManagementReturnVo>(rmv.getCurrent(), rmv.getSize());
		List<RepaymentManagementReturnVo> rmrvList = baseMapper.queryRepaymentManagementList(rmv, page);
		for (RepaymentManagementReturnVo repaymentManagementReturnVo : rmrvList) {
			String mobile = repaymentManagementReturnVo.getMobile();
			if (StringUtil.isNotNull(mobile)) {
				byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
				BASE64Encoder enc = new BASE64Encoder();
				mobile = enc.encode(decodedPwds2);
				repaymentManagementReturnVo.setMobile(mobile);
			}
			String manager = repaymentManagementReturnVo.getManager();
			if (userInfo.getEmployeeModel().getPerson_code().equals(manager)) {
				repaymentManagementReturnVo.setIsManager(true);
			}
			String uuid = repaymentManagementReturnVo.getTusUuid();
			TpmAttendenceModel t = tpmAttendenceMapper.selectByTusUuid(uuid);
			if (t != null) {
				repaymentManagementReturnVo.setAttendenceUuid(t.getAttendence_uuid());
				repaymentManagementReturnVo.setUrl(t.getUrl());
				repaymentManagementReturnVo.setIsAttendence(true);
			}
		}
		page.setRecords(rmrvList);
		return page;
	}

	@Override
	public RepaymentManagementReturnVo queryRepaymentManagementDetails(String uuid) throws Exception {
		RepaymentManagementReturnVo rmrv = baseMapper.queryRepaymentManagementDetails(uuid);
		String mobile = rmrv.getMobile();
		if (StringUtil.isNotNull(mobile)) {
			byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
			BASE64Encoder enc = new BASE64Encoder();
			mobile = enc.encode(decodedPwds2);
			rmrv.setMobile(mobile);
		}

		double real_salary = rmrv.getUnitPrice();
		int hours = rmrv.getValidWorkHours();
		if (hours < rmrv.getWorkHours()) {
			real_salary = real_salary / rmrv.getWorkHours() * hours;
			double minSalary = rmrv.getValidWorkMinute() / 60.0 * (rmrv.getUnitPrice() / rmrv.getWorkHours());
			real_salary = real_salary + minSalary;
		}
		real_salary = Math.ceil(real_salary);
		rmrv.setValidPrice(real_salary);
		if (rmrv.getLoadBillUuid() != null) {
			TpmLoanBillModel tlbm = tpmLoanBillMapper.selectById(rmrv.getLoadBillUuid());
			if (tlbm != null) {
				rmrv.setLoadBillUuid(tlbm.getFssc_loan_bill_no());
			} else {
				rmrv.setLoadBillUuid(null);
			}
		}

		TpmAttendenceModel tam = tpmAttendenceService.selectByTusUuid(uuid);
		if (tam != null) {
			rmrv.setOperPersonName(tam.getPerson_name());
			rmrv.setCreateTime(tam.getCreate_time());
			rmrv.setAttendenceUuid(tam.getAttendence_uuid());
		}
		return rmrv;
	}

	@Override
	public ResultVo importAttendence(RepaymentManagementVo rmv, LoginUserInfo userInfo) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		if (personType == -1 || personType == 1 || personType == 0) {
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}
		if (rmv != null) {
			List<TpmAttendenceModel> tamList = rmv.getTpmAttendenceModelList();
			if (tamList != null && tamList.size() > 0) {
				String personCode = userInfo.getEmployeeModel().getPerson_code();
				String personName = userInfo.getEmployeeModel().getPerson_name();
				TpmEmployeeModel tem = tpmEmployeeMapper.getTpmEmployeebyPersonCode(personCode);
				String code = "";
				if (tem == null) {
					return ResultVo.getByEnumCode(CommonConstants.PERSONCODE_NOT_FOUND_CODE);
				}
				if (!StringUtil.isEmpty(tem.getCompany())) {
					code = tem.getCompany();
				} else {
					code = tem.getOrg_code();
				}
				if (!date.equals(DateUtils.today())) {
					date = DateUtils.today();
					no = 1;
				}
				String time = DateUtils.currentTime();
				String timeNo = time.replaceAll("[-: ]", "").substring(0, 12);
				String No = no + "";
				if (No.length() < 4) {
					int length = 4 - No.length();
					for (int i = 0; i < length; i++) {
						No = "0" + No;
					}
				}
				String attendentUuid = code + timeNo + No;
				File tempfile = new File(attendencePath);
				if (!tempfile.exists()) {
					tempfile.mkdirs();
				}
				String path = attendencePath +"/"+ attendentUuid;
				String url = attendenceUrl + "/" + attendentUuid;
				// String attendentUuid = StringUtil.getUUID();
				for (TpmAttendenceModel tam : tamList) {
					if (tusUuidList.contains(tam.getTus_uuid())) {
						return ResultVo.getByEnumCode(CommonConstants.ATTENDENCE_IS_EXIST_CODE);
					} else {
						tusUuidList.add(tam.getTus_uuid());
					}
				}
				for (TpmAttendenceModel tam : tamList) {
					tam.setPerson_code(personCode);
					tam.setPerson_name(personName);
					tam.setAttendence_uuid(attendentUuid);
					tam.setUrl(path);
					tam.setUuid(StringUtil.getUUID());
					TpmOptLogsModel tolm = new TpmOptLogsModel();
					tolm.setType(10);
					String opt_user = personName + "(" + personCode + ")";
					tolm.setOptUser(opt_user);
					tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
					String content = "结算单(" + tam.getTus_uuid() + ")已导出到考勤表中，考勤表单号为：" + attendentUuid;
					tolm.setContent(content);
					tpmAttendenceService.insertAllColumn(tam);
					tpmOptLogsService.insertAllColumn(tolm);
					TpmUserStatementModel tusm = tpmUserStatementMapper.selectById(tam.getTus_uuid());
					tusm.setIs_attendence("1");
					tpmUserStatementMapper.updateById(tusm);
				}
				for (TpmAttendenceModel tam : tamList) {
					tusUuidList.remove(tam.getTus_uuid());
				}
				cresteAttendences(attendentUuid, path);
				no++;
			}
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}

	public void cresteAttendences(String uuid, String url) throws DocumentException, Exception {

		List<RepaymentManagementReturnVo> rmrvList = baseMapper.queryDownloadAttendences(uuid);
		if (rmrvList != null && rmrvList.size() > 0) {
			int id = 1;
			for (RepaymentManagementReturnVo rmrv : rmrvList) {
				if (rmrv.getLoadBillUuid() != null) {
					TpmLoanBillModel tlbm = tpmLoanBillMapper.selectById(rmrv.getLoadBillUuid());
					if (tlbm != null) {
						rmrv.setLoadBillUuid(tlbm.getFssc_loan_bill_no());
					} else {
						rmrv.setLoadBillUuid(null);
					}
				}
				double real_salary = rmrv.getUnitPrice();
				int hours = rmrv.getValidWorkHours();
				if (hours < rmrv.getWorkHours()) {
					real_salary = real_salary / rmrv.getWorkHours() * hours;
					double minSalary = rmrv.getValidWorkMinute() / 60.0 * (rmrv.getUnitPrice() / rmrv.getWorkHours());
					real_salary = real_salary + minSalary;
				}
				real_salary = Math.ceil(real_salary);
				rmrv.setValidPrice(real_salary);
				rmrv.setId(id + "");
				id++;
			}
			createAttendencesPdf(rmrvList, url);
			createAttendencesXls(rmrvList, url);
		}
	}

	public void createAttendencesPdf(List<RepaymentManagementReturnVo> rmvList, String url)
			throws Exception, DocumentException {
		File file = new File(url+".pdf");
		Document document = new Document(PageSize.A4, 5, 5, 10, 10);

		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();

		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font chineseFont = new Font(bfChinese, 5);

		PdfPTable table = new PdfPTable(19);
		table.setWidthPercentage(100);
		List<PdfPRow> listRow = table.getRows();
		float[] columnWidths = { 3, 7, 7, 4, 5, 7, 5, 5, 7, 5, 5, 6, 5, 5, 5, 5, 5, 4, 5 };
		table.setWidths(columnWidths);

		PdfPCell[] head = createPdfPCellContent(getTableHead(), chineseFont);
		PdfPRow headRow = new PdfPRow(head);
		listRow.add(headRow);
		for (RepaymentManagementReturnVo rmrv : rmvList) {
			List<String> contentList = getTableContent(rmrv);
			PdfPCell[] cells = createPdfPCellContent(contentList, chineseFont);
			PdfPRow row = new PdfPRow(cells);
			listRow.add(row);
		}
		List<String> tailList = getTableTail(rmvList);
		PdfPCell[] tail = createPdfPCellContent(tailList, chineseFont);
		PdfPRow tailRow = new PdfPRow(tail);
		listRow.add(tailRow);
		chineseFont = new Font(bfChinese, 10);
		Paragraph pa = new Paragraph("临促费用考勤明细表", chineseFont);
		pa.setAlignment(Paragraph.ALIGN_CENTER);
		chineseFont = new Font(bfChinese, 5);
		Paragraph pa2 = new Paragraph("单位：人民币/元", chineseFont);
		pa2.setAlignment(Paragraph.ALIGN_RIGHT);
		Paragraph pa3 = new Paragraph(" ", new Font(bfChinese, 2));
		pa2.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(pa);
		document.add(pa2);
		document.add(pa3);
		document.add(table);
		document.close();
		pdfWriter.close();
	}

	public void createAttendencesXls(List<RepaymentManagementReturnVo> rmvList, String url) throws IOException {
		File file = new File(url+".xls");
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("考勤表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row.createCell(0);
		// 设置单元格内容
		cell.setCellValue("临促费用考勤明细表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));
		HSSFCellStyle  style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont  fontStyle = wb.createFont(); 
		fontStyle.setFontName("微软雅黑"); 
		fontStyle.setFontHeightInPoints((short)16);
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fontStyle);  
		cell.setCellStyle(style);
		
		row = sheet.createRow(1);
		HSSFCell cell2 = row.createCell(0);
		cell2.setCellValue("单位：人民币/元");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 18));
		HSSFCellStyle  style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		fontStyle.setFontName("宋体"); 
		fontStyle.setFontHeightInPoints((short)12);
		HSSFFont  fontStyle2 = wb.createFont(); 
		style2.setFont(fontStyle2);  
		cell2.setCellStyle(style2);
		List<String> headList = getTableHead();
		row = sheet.createRow(2);
		for(int i = 0; i < headList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(headList.get(i));
			if(i == 1 || i == 2 || i == 7 || i == 11) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 18);
			}
			if(i == 5 || i == 8) {
				sheet.setColumnWidth(cell.getColumnIndex(), 256 * 25);
			}
			style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(style2);
		}
		int rowNo = 2;
		for (RepaymentManagementReturnVo rmrv : rmvList) {
			rowNo++;
			row = sheet.createRow(rowNo);
			List<String> list = getTableContent(rmrv);
			for(int i = 0; i < list.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(list.get(i));
				cell.setCellStyle(style2);
			}
		}
		row = sheet.createRow(rowNo+1);
		List<String> tailList = getTableTail(rmvList);
		for(int i = 0; i < tailList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(tailList.get(i));
			cell.setCellStyle(style2);
		}
		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		fout.close();
	}

	public List<String> getTableHead() {
		String[] arr = new String[] { "序号", "借款单号", "立项单号", "立项人", "结算方式", "考勤表单号", "生成人员", "生成时间", "活动名称", "临促姓名",
				"活动负责人", "临促联系电话", "考勤日期", "标准工时", "实际工时", "标准日薪", "实际日薪", "奖罚", "结算费用" };
		List<String> list = Arrays.asList(arr);
		return list;
	}

	public List<String> getTableTail(List<RepaymentManagementReturnVo> rmvList) {
		double totalUnitPrice = 0;
		double totalValidPrice = 0;
		double totalAdjustMoney = 0;
		double totalRealSalary = 0;
		for (RepaymentManagementReturnVo rmrv : rmvList) {
			totalUnitPrice += rmrv.getUnitPrice();
			totalRealSalary += rmrv.getRealSalary();
			totalValidPrice += rmrv.getValidPrice();
			if (rmrv.getIncentiveAmount() != null) {
				totalAdjustMoney += rmrv.getIncentiveAmount();
			}
			if (rmrv.getReduceMoney() != null) {
				totalAdjustMoney -= rmrv.getReduceMoney();
			}
		}
		String adjustMoney;
		if (totalAdjustMoney >= 0) {
			adjustMoney = "+" + totalAdjustMoney;
		} else {
			adjustMoney = "" + totalAdjustMoney;
		}
		String[] arr = new String[] { "合计", "-", "-", "-", "-", "-", "-", "-", "-", "-",
				"-","-", "-", "-", "-","-", "-", adjustMoney, totalRealSalary + "" };
		List<String> list = Arrays.asList(arr);
		return list;
	}

	public List<String> getTableContent(RepaymentManagementReturnVo rmrv) {
		String adjustMoney = "+0.0";
		if (rmrv.getIncentiveAmount() != null) {
			adjustMoney = "+" + rmrv.getIncentiveAmount();
		}
		if (rmrv.getReduceMoney() != null) {
			adjustMoney = "-" + rmrv.getReduceMoney();
		}
		String validWorkTime = rmrv.getValidWorkHours() + "时" + rmrv.getValidWorkMinute() + "分";
		String[] arr = new String[] { rmrv.getId(), rmrv.getLoadBillUuid(), rmrv.getRequestId(), rmrv.getRequestUser(),
				rmrv.getSettlementMethod(), rmrv.getAttendenceUuid(), rmrv.getOperPersonName(), rmrv.getCreateTime(),
				rmrv.getActivityName(), rmrv.getOpenName(), rmrv.getManagerName(), rmrv.getMobile(),
				rmrv.getActivityTime(), rmrv.getWorkHours() + "时", validWorkTime, rmrv.getUnitPrice() + "",
				rmrv.getValidPrice() + "", adjustMoney, rmrv.getRealSalary() + "" };
		List<String> list = Arrays.asList(arr);
		return list;
	}

	public PdfPCell[] createPdfPCellContent(List<String> list, Font chineseFont) {

		PdfPCell[] cells = new PdfPCell[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Paragraph pa = new Paragraph(list.get(i), chineseFont);
			cells[i] = new PdfPCell(pa);
		}

		return cells;
	}

	@Override
	public ResultVo queryAttendences(RepaymentManagementVo rmv, LoginUserInfo userInfo) throws Exception {
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		String personCode = userInfo.getEmployeeModel().getPerson_code();
		List<String> deptList = new ArrayList<>();
		if (personType == -1) {
			return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
		}
		if(personType == 2) {
			deptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if(deptList == null || deptList.size() == 0) {
				log.info("部门列表错误，为{}",deptList);
				return ResultVo.get(ResultVo.SUCCESS);
			}
		}
		if(personType == 0 || personType == 6){
			deptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if(deptList == null || deptList.size() == 0) {
				log.info("部门列表branchDeptList错误，为{}",deptList);
				return ResultVo.get(ResultVo.SUCCESS);
			}
		}
		List<String> requestUsers = baseMapper.queryRequestUsers(deptList);
		rmv.setList(requestUsers);
		if (StringUtil.isNotNull(rmv.getStartTime())){
			String time = rmv.getStartTime();
			time += " 00:00:00";
			rmv.setStartTime(time);
		}
		if (StringUtil.isNotNull(rmv.getEndTime())) {
			String time = rmv.getEndTime();
			time += " 23:59:59";
			rmv.setEndTime(time);
		}
		rmv.setPersonType(personType);
		rmv.setPersonCode(personCode);
		Page<TpmAttendenceModel> page = new Page<>(rmv.getCurrent(), rmv.getSize());
		page.setRecords(baseMapper.queryAttendences(rmv, page));
		return ResultVo.getData(ResultVo.SUCCESS, page);
	}

	@Override
	public String getDownloadUrl(String uuid, LoginUserInfo userInfo) throws Exception {
		File tempfile = new File(attendencePath);
		if (!tempfile.exists()) {
			tempfile.mkdirs();
		}
		String personCode = userInfo.getEmployeeModel().getPerson_code();
		String personName = userInfo.getEmployeeModel().getPerson_name();
		TpmOptLogsModel tolm = new TpmOptLogsModel();
		tolm.setType(11);
		String opt_user = personName + "(" + personCode + ")";
		tolm.setOptUser(opt_user);
		tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
		String content = "考勤表单号(" + uuid + ")已下载";
		tolm.setContent(content);
		tpmOptLogsService.insertAllColumn(tolm);
		// tempfile.getName().replaceAll("\\", "/");
		String filePath = attendenceUrl + "/" + uuid;
		return filePath;
	}

}
