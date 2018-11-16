package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itextpdf.text.DocumentException;
import com.kuyu.mapper.pcms.PcmsSupplierMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.PcmsSupplierListVo;
import com.kuyu.vo.PcmsSupplierQuert;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 2018/11/14
 */

@Service
@Transactional
public class PcmsSupplierServiceImpl extends ServiceImpl<PcmsSupplierMapper, PcmsSupplierModel>
        implements PcmsSupplierService {

    @Value("${pcmsSupplierPath}")
    private String pcmsSupplierPath;

    @Value("${pcmsSupplierUrl}")
    private String pcmsSupplierUrl;

    @Autowired
    private TpmOptLogsService tpmOptLogsService;
    @Override
    public void insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception {
        if (StringUtil.isNotNull(pcmsSupplierVo.getCompany())
                && StringUtil.isNotNull(pcmsSupplierVo.getCreate_date())
                && StringUtil.isNotNull(pcmsSupplierVo.getDel_comp())
                && StringUtil.isNotNull(pcmsSupplierVo.getDel_flag())
                && StringUtil.isNotNull(pcmsSupplierVo.getType())
                && StringUtil.isNotNull(pcmsSupplierVo.getVendor_id())
                && StringUtil.isNotNull(pcmsSupplierVo.getVendor_name())
                && StringUtil.isNotNull(pcmsSupplierVo.getPerson_id())){
            baseMapper.insertPcmsSupplier(pcmsSupplierVo);
        }
    }

    @Override
    public PcmsSupplierModel getPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) {
        return baseMapper.getPcmsSupplier(pcmsSupplierVo);
    }

    @Override
    public void updatePcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception {
        if (StringUtil.isNotNull(pcmsSupplierVo.getVendor_id())) {
            baseMapper.updatePcmsSupplier(pcmsSupplierVo);
        }

    }

    @Override
    public void updatePcmsSupplierModel(PcmsSupplierModel pcmsSupplierModel) throws Exception {
        baseMapper.updatePcmsSupplierModel(pcmsSupplierModel);
    }

    @Override
    public ResultVo findPcmsSupplierListByPage(LoginUserInfo userInfo, PcmsSupplierQuert query) throws Exception {
        String person_code = userInfo.getEmployeeModel().getPerson_code();
        query.setPerson_code(person_code);
        query = (PcmsSupplierQuert) CheckParamUtils.trimWithObjectField(query);
        Page<PcmsSupplierListVo> page = new Page<>(query.getCurrent(),query.getSize());
        List<PcmsSupplierListVo> PcmsSupplierListVoList = baseMapper.findPcmsSupplierListByPage(query,page);
        page.setRecords(PcmsSupplierListVoList);
        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public String getPcmsSupplierUrl(List<String> list,String pdfOrxls) throws Exception {
        File tempfile = new File(pcmsSupplierPath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        String path = StringUtil.getUUID();
        String file = pcmsSupplierPath + "/"+ path;
        /*List<TpmUserBaseInfoModel> tubimList = baseMapper.queryBankInfoByOpenids(list);
        if(tubimList != null && tubimList.size() > 0) {
            String personName = userInfo.getEmployeeModel().getPerson_name();
            String personCode = userInfo.getEmployeeModel().getPerson_code();
            for (TpmUserBaseInfoModel tubim : tubimList) {
                TpmOptLogsModel tolm = new TpmOptLogsModel();
                tolm.setType(12);
                String opt_user = personName + "(" + personCode + ")";
                tolm.setOptUser(opt_user);
                tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
                String content = "临促" + tubim.getName() +"(" +tubim.getOpenid()+")的银行账户信息已下载";
                tolm.setContent(content);
                tpmOptLogsService.insertAllColumn(tolm);
            }
            if("xls".equals(pdfOrxls)) {
                file += ".xls";
                createBankInfoXls(tubimList,file);
                return  pcmsSupplierUrl+"/"+path+".xls";
            }
        }*/
        return null;
    }

    public void createBankInfoXls(List<TpmUserBaseInfoModel> tubimList, String url)
            throws Exception, DocumentException {
        File file = new File(url);
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("银行信息表");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row.createCell(0);
        // 设置单元格内容
        cell.setCellValue("临促人员银行信息");
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont  fontStyle=wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints((short)16);
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(fontStyle);
        cell.setCellStyle(style);
        HSSFCellStyle  style2 = wb.createCellStyle();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short)12);
        HSSFFont  fontStyle2 = wb.createFont();
        style2.setFont(fontStyle2);
        List<String> headList = getTableHead();
        row = sheet.createRow(1);
        for(int i = 0; i < headList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
            if(i == 1 || i == 5) {
                sheet.setColumnWidth(cell.getColumnIndex(), 256 * 20);
            }else {
                sheet.setColumnWidth(cell.getColumnIndex(), 256 * 15);
            }
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cell.setCellStyle(style2);
        }
        int rowNo = 1;
        for (TpmUserBaseInfoModel tubim : tubimList) {
            rowNo++;
            row = sheet.createRow(rowNo);
            List<String> list = getTableContent(tubim);
            for(int i = 0; i < list.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(list.get(i));
                cell.setCellStyle(style2);
            }
        }
        FileOutputStream fout = new FileOutputStream(file);
        wb.write(fout);
        fout.close();
    }

    public List<String> getTableHead() {
        String[] arr = new String[] { "姓名", "身份证号码", "手机号", "户名", "开户银行名", "银行账号", "开户省",
                "开户市", "开户支行" };
        List<String> list = Arrays.asList(arr);
        return list;
    }
    public List<String> getTableContent(TpmUserBaseInfoModel tubim) {
        String[] arr = new String[] {tubim.getName(),tubim.getId_card(),tubim.getMobile(),tubim.getAccount_name(),
                tubim.getAccount_bank_name(),tubim.getAccount_value(),tubim.getOpen_province(),tubim.getOpen_city(),
                tubim.getOpen_branch()};
        List<String> list = Arrays.asList(arr);
        return list;
    }
}
