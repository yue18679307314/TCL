package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itextpdf.text.DocumentException;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.PcmsSupplierMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.pcms.PcmsSupplierLogModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.model.pcms.PcmsSupplierUserModel;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.service.*;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.PcmsSupplierListVo;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsSupplierModelVo;
import com.kuyu.vo.pcms.PcmsVendorIdVo;
import com.kuyu.vo.query.PcmsSupplierQuery;
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

    @Autowired
    private PcmsUserService pcmsUserService;

    @Autowired
    private PcmsSupplierUserService pcmsSupplierUserService;

    @Autowired
    private PcmsSupplierLogService pcmsSupplierLogService;

    @Override
    public void insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception {
        if (StringUtil.isNotNull(pcmsSupplierVo.getCreate_date())
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
    public ResultVo getPcmsSupplierFor(PcmsSupplierModel pcmsSupplierModel, PcmsUserModel pcmsUserModel)throws Exception {
        if (StringUtil.isEmpty(pcmsSupplierModel.getMobile())){
            throw new ParamException("手机号不能为空");
        }
        if(StringUtil.isEmpty(pcmsSupplierModel.getLegal_person())){
            throw new ParamException("法人不能为空");
        }
        if(StringUtil.isEmpty(pcmsSupplierModel.getVendor_name())){
            throw new ParamException("公司名称不能为空");
        }
        PcmsSupplierModel pcmsSupplierModel1 =  baseMapper.getPcmsSupplierFor(pcmsSupplierModel);
        if(null == pcmsSupplierModel1){
            throw new ParamException("公司名称或法人或手机号码不正确");
        }
/*        PcmsUserModel pcmsUserModel1 = pcmsUserService.selectPcmsUserModel(pcmsUserModel);
        if(null != pcmsUserModel1){
            throw new ParamException("公司名称不能为空");
        }*/
        pcmsUserModel.setType(1);
        pcmsUserModel.setCreate_time(DateUtils.getLongDateStr());
        PcmsUserModel pcmsUserModel1 =  pcmsUserService.selectPcmsUserModel(pcmsUserModel);
        if(pcmsUserModel1 != null){
            throw new ParamException("该用户已绑定");
        }
        pcmsUserService.insertPcmsUserModel(pcmsUserModel);
        PcmsSupplierUserModel pcmsSupplierUser = new PcmsSupplierUserModel();
        pcmsSupplierUser.setOpenid(pcmsUserModel.getOpenid());
        pcmsSupplierUser.setVendor_id(pcmsSupplierModel1.getVendor_id());
        pcmsSupplierUserService.insertPcmsSupplierUserModel(pcmsSupplierUser);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public void updatePcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception {
        if (StringUtil.isNotNull(pcmsSupplierVo.getVendor_id())) {
            baseMapper.updatePcmsSupplier(pcmsSupplierVo);
        }

    }

    @Override
    public void updatePcmsSupplierModel(PcmsSupplierModel pcmsSupplierModel,LoginUserInfo userInfo) throws Exception {
        /**记录更新日志*/
        if(StringUtil.isEmpty(pcmsSupplierModel.getVendor_id())){
            throw new ParamException("供应商编码为空");
        }
        PcmsSupplierLogModel pcmsSupplierLogModel = new PcmsSupplierLogModel();
        StringBuffer content = new StringBuffer();
        PcmsSupplierVo pcmsSupplierVo = new PcmsSupplierVo();
        pcmsSupplierVo.setVendor_id(pcmsSupplierModel.getVendor_id());
        PcmsSupplierModel pcmsSupplierModel1 = baseMapper.getPcmsSupplier(pcmsSupplierVo);
        if(StringUtil.isNotNull(pcmsSupplierModel.getLegal_person())){
            content.append("原法人为:"+pcmsSupplierModel1.getLegal_person()+",");
            content.append("更改为:"+pcmsSupplierModel.getLegal_person()+";");
        }
        if(StringUtil.isNotNull(pcmsSupplierModel.getMobile())){
            content.append("原电话为"+pcmsSupplierModel1.getMobile()+",");
            content.append("更改为:"+pcmsSupplierModel.getLegal_person()+";");
        }
        pcmsSupplierLogModel.setContent(content.toString());
        pcmsSupplierLogModel.setVendor_id(pcmsSupplierModel.getVendor_id());
        pcmsSupplierLogModel.setCreate_time(DateUtils.getLongDateStr());
        pcmsSupplierLogModel.setOperation(userInfo.getEmployeeModel().getPerson_name());
        pcmsSupplierLogService.insert(pcmsSupplierLogModel);
        baseMapper.updatePcmsSupplierModel(pcmsSupplierModel);
    }

    @Override
    public ResultVo findPcmsSupplierListByPage(LoginUserInfo userInfo, PcmsSupplierQuery query) throws Exception {
        query.setPerson_code(userInfo.getEmployeeModel().getPerson_code());
        query.setRequest_company(query.getVendor_name());
        query = (PcmsSupplierQuery) CheckParamUtils.trimWithObjectField(query);
        Page<PcmsSupplierListVo> page = new Page<>(query.getCurrent(),query.getSize());
        List<PcmsSupplierListVo> pcmsSupplierList = baseMapper.findPcmsSupplierListByPage(query,page);
        page.setRecords(pcmsSupplierList);
        return ResultVo.getDataWithSuccess(page);
    }


    @Override
    public String getPcmsSupplierUrl(List<PcmsVendorIdVo> list, String pdfOrxls, LoginUserInfo userInfo) throws Exception {
        File tempfile = new File(pcmsSupplierPath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        String path = StringUtil.getUUID();
        String file = pcmsSupplierPath + "/"+ path;
        List<PcmsSupplierModelVo> supplierList = baseMapper.getPcmsSupplierForIds(list);
        if(supplierList != null && supplierList.size() > 0) {
            String personName = userInfo.getEmployeeModel().getPerson_name();
            String personCode = userInfo.getEmployeeModel().getPerson_code();
            for (PcmsSupplierModelVo tubim : supplierList) {
                TpmOptLogsModel tolm = new TpmOptLogsModel();
                tolm.setType(12);
                String opt_user = personName + "(" + personCode + ")";
                tolm.setOptUser(opt_user);
                tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
                String content = "供应商" + tubim.getVendor_name() +"的信息已下载";
                tolm.setContent(content);
                tpmOptLogsService.insertAllColumn(tolm);
            }
            if("xls".equals(pdfOrxls)) {
                file += ".xls";
                createBankInfoXls(supplierList,file);
                return  pcmsSupplierPath+"/"+path+".xls";
            }
        }
        return null;
    }

    public void createBankInfoXls(List<PcmsSupplierModelVo> supplierList, String url)
            throws Exception, DocumentException {
        File file = new File(url);
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("供应商信息表");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row.createCell(0);
        // 设置单元格内容
        cell.setCellValue("供应商信息");
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
        for (PcmsSupplierModelVo tubim : supplierList) {
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
        String[] arr = new String[] { "供应商名称", "往来业务公司", "法定代表人", "联系电话" };
        List<String> list = Arrays.asList(arr);
        return list;
    }
    public List<String> getTableContent(PcmsSupplierModelVo tubim) {
        String[] arr = new String[] {tubim.getVendor_name(),tubim.getCompany(),tubim.getLegal_person(),tubim.getMobile()};
        List<String> list = Arrays.asList(arr);
        return list;
    }
}
