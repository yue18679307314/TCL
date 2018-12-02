package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itextpdf.text.DocumentException;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.PcmsSupplierMaterialMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.service.PcmsSupplierMaterialService;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsSupplierModelVo;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PcmsSupplierMaterialServiceImpl extends ServiceImpl<PcmsSupplierMaterialMapper, PcmsSupplierMaterialModel>
        implements PcmsSupplierMaterialService {

    @Value("${pcmsSupplierPath}")
    private String pcmsSupplierPath;

    @Value("${pcmsSupplierUrl}")
    private String pcmsSupplierUrl;
    @Autowired
    private PcmsSupplierService pcmsSupplierService;

    @Autowired
    private TpmOptLogsService tpmOptLogsService;

    @Override
    public void insertPcmsSupplierMaterial(PcmsSupplierMaterialModel pcmsSupplierMaterialModel, LoginUserInfo userInfo) throws Exception {
        PcmsSupplierVo supplier = new PcmsSupplierVo();
        supplier.setVendor_id(pcmsSupplierMaterialModel.getVendor_id());
        PcmsSupplierModel pcmsSupplierModel = pcmsSupplierService.getPcmsSupplier(supplier);
        if(null == pcmsSupplierModel){
            throw new ParamException("供应商不存在");
        }
        PcmsSupplierMaterialModel psm = baseMapper.findSupplierMaterialByVendorAndCompany(pcmsSupplierMaterialModel.getVendor_id(),userInfo.getEmployeeModel().getCompany());
        if(null == psm){
            pcmsSupplierMaterialModel.setCreate_time(new Date());
            pcmsSupplierMaterialModel.setVersion(10000);
            pcmsSupplierMaterialModel.setCompany(userInfo.getEmployeeModel().getCompany());
            baseMapper.insert(pcmsSupplierMaterialModel);
        }else{
            pcmsSupplierMaterialModel.setCreate_time(new Date());
            pcmsSupplierMaterialModel.setVersion(psm.getVersion()+1);
            pcmsSupplierMaterialModel.setCompany(userInfo.getEmployeeModel().getCompany());
            baseMapper.insert(pcmsSupplierMaterialModel);
        }

    }

    @Override
    public void deletePcmsSupplierMaterial(Integer id) throws Exception {
        PcmsSupplierMaterialModel pcmsSupplierMaterialModel = baseMapper.selectById(id);
        if(null == pcmsSupplierMaterialModel){
            throw new ParamException("该物料不存在");
        }
        baseMapper.deleteById(id);
    }

    @Override
    public ResultVo findSupplierMaterialByPage(LoginUserInfo userInfo, SupplierMaterialQuery query) throws Exception {
        query.setCompany(userInfo.getEmployeeModel().getCompany());
        query = (SupplierMaterialQuery) CheckParamUtils.trimWithObjectField(query);
        Page<SupplierMaterialQuery> page = new Page<>(query.getCurrent(),query.getSize());
        List<SupplierMaterialQuery> list = baseMapper.findSupplierMaterialByPage(query,page);
        page.setRecords(list);
        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public ResultVo querySupplierMaterialList(SupplierMaterialVo query) throws Exception {
        List<PcmsSupplierMaterialModel> list = baseMapper.querySupplierMaterialList(query);
        return ResultVo.getDataWithSuccess(list);
    }

    @Override
    public String getSupplierMaterialUrl(List<PcmsSupplierMaterialModel> supplierMaterialList, LoginUserInfo userInfo) throws Exception {
        File tempfile = new File(pcmsSupplierPath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        String path = StringUtil.getUUID();
        String file = pcmsSupplierPath + "/"+ path;
        List<PcmsSupplierMaterialModel> list = baseMapper.selectBatchIds(supplierMaterialList);
        if(list != null && list.size() > 0) {
            String personName = userInfo.getEmployeeModel().getPerson_name();
            String personCode = userInfo.getEmployeeModel().getPerson_code();
            for (PcmsSupplierMaterialModel tubim : list) {
                TpmOptLogsModel tolm = new TpmOptLogsModel();
                tolm.setType(12);
                String opt_user = personName + "(" + personCode + ")";
                tolm.setOptUser(opt_user);
                tolm.setOptUserDept(userInfo.getEmployeeModel().getOrg_code());
                String content = "供应商物料" + tubim.getVendor_name() +"的信息已下载";
                tolm.setContent(content);
                tpmOptLogsService.insertAllColumn(tolm);
            }
        createBankInfoXls(list,file);
        return  pcmsSupplierPath+"/"+path+".xls";
        }
        return null;
    }
    public void createBankInfoXls(List<PcmsSupplierMaterialModel> list, String url)
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
        for (PcmsSupplierMaterialModel tubim : list) {
            rowNo++;
            row = sheet.createRow(rowNo);
            List<String> lists = getTableContent(tubim);
            for(int i = 0; i < list.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(lists.get(i));
                cell.setCellStyle(style2);
            }
        }
        FileOutputStream fout = new FileOutputStream(file);
        wb.write(fout);
        fout.close();
    }

    public List<String> getTableHead() {
        String[] arr = new String[] { "供应商名称*", "物料/标准件类别*", "材质规格*", "单位*","单价数量范围","参考价（合同约定价：元）","备注描述（非必填）\n" };
        List<String> list = Arrays.asList(arr);
        return list;
    }
    public List<String> getTableContent(PcmsSupplierMaterialModel tubim) {
        String[] arr = new String[] {tubim.getVendor_name(),tubim.getCategory(),tubim.getSpecifications(),tubim.getUnit(),tubim.getRanges(),String.valueOf(tubim.getComparison_price()),tubim.getNote()};
        List<String> list = Arrays.asList(arr);
        return list;
    }
}
