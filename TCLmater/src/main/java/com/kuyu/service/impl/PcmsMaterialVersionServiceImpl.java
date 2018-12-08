package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.PcmsMaterialVersionMapper;
import com.kuyu.mapper.pcms.PcmsSupplierMaterialMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.service.PcmsMaterialVersionService;
import com.kuyu.service.PcmsSupplierMaterialService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pc on 2018/11/26
 */
@Service
@Transactional
public class PcmsMaterialVersionServiceImpl extends ServiceImpl<PcmsMaterialVersionMapper, PcmsMaterialVersionModel>
        implements PcmsMaterialVersionService {

    @Resource
    private PcmsSupplierMaterialService pcmsSupplierMaterialService;

    @Resource
    private PcmsSupplierMaterialMapper pcmsSupplierMaterialMapper;

    @Value("${excel.path}")
    private String filePath;
    @Value("${excel.url}")
    private String fileUrl;
    @Override
    public ResultVo uploadAndInsert(MultipartFile file, String vendor_id, LoginUserInfo userInfo) throws Exception {

        String xls = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        String paths = StringUtil.getUUID();
        String nameXls = paths+xls;
        String finalPath = filePath+ "/"+nameXls;
        String suffix = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        File tempfile = new File(filePath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        File newFile = new File(finalPath);
        file.transferTo(newFile);
        Map<String, String> map  = readExcel(finalPath);
        if(map.size() == 0 || null == map){
            newFile.delete();
            throw new ParamException("数据为空");
        }
//        List<PcmsSupplierMaterialModel> list = new ArrayList<>();
        List<PcmsSupplierMaterialModel> psmm = pcmsSupplierMaterialMapper.findSupplierMaterialByVendorAndCompany(vendor_id,userInfo.getEmployeeModel().getCompany());
        PcmsMaterialVersionModel pcmsMaterialVersionModel = new PcmsMaterialVersionModel();
        if(null != psmm && psmm.size()>0){
            List<PcmsSupplierMaterialModel> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue();
                PcmsSupplierMaterialModel tdm = new PcmsSupplierMaterialModel();
                String[] psm = value.split(",");
                for (int i = 0; i < psm.length; i++) {
                    if(psm[i].equals("NULL")) {
                        psm[i] = null;
                    }
                }
                tdm.setVendor_name(psm[0]);
                tdm.setCategory(psm[1]);
                tdm.setSpecifications(psm[2]);
                tdm.setUnit(psm[3]);
                tdm.setRanges(psm[4]);
                if(psm[5] != null){
                    tdm.setComparison_price(Double.parseDouble(psm[5]));
                }
                tdm.setNote(psm[6]);
                tdm.setVendor_id(vendor_id);
                tdm.setCreate_time(new Date());
                tdm.setCompany(userInfo.getEmployeeModel().getCompany());
                tdm.setVersion(psmm.get(0).getVersion()+1);
                tdm.setUrl(finalPath);
                tdm.setState(0);
                list.add(tdm);
            }
            /*List<Integer> listId = new ArrayList<>();
            for (PcmsSupplierMaterialModel id : psmm){
                Integer ids = id.getId();
                listId.add(ids);
            }
            pcmsSupplierMaterialService.deleteBatchIds(listId);*/
//            pcmsSupplierMaterialService.insertBatch(list);
            pcmsMaterialVersionModel.setCompany(userInfo.getEmployeeModel().getCompany());
            pcmsMaterialVersionModel.setCreate_time(new Date());
            pcmsMaterialVersionModel.setName(suffix);
            pcmsMaterialVersionModel.setVendor_id(vendor_id);
            pcmsMaterialVersionModel.setUrl(fileUrl+"/"+nameXls);
            pcmsMaterialVersionModel.setVersion(psmm.get(0).getVersion()+1);
            pcmsMaterialVersionModel.setState(2);
            baseMapper.insert(pcmsMaterialVersionModel);
            return ResultVo.getDataWithSuccess(list);
        }else{
            List<PcmsSupplierMaterialModel> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue();
                PcmsSupplierMaterialModel tdm = new PcmsSupplierMaterialModel();
                String[] psm = value.split(",");
                for (int i = 0; i < psm.length; i++) {
                    if(psm[i].equals("NULL")) {
                        psm[i] = null;
                    }
                }
                tdm.setVendor_name(psm[0]);
                tdm.setCategory(psm[1]);
                tdm.setSpecifications(psm[2]);
                tdm.setUnit(psm[3]);
                tdm.setRanges(psm[4]);
                if(psm[5] != null){
                    tdm.setComparison_price(Double.parseDouble(psm[5]));
                }
                tdm.setNote(psm[6]);
                tdm.setVendor_id(vendor_id);
                tdm.setCreate_time(new Date());
                tdm.setCompany(userInfo.getEmployeeModel().getCompany());
                tdm.setVersion(10000);
                tdm.setUrl(finalPath);
                tdm.setState(0);
                list.add(tdm);
            }
//            pcmsSupplierMaterialService.insertBatch(list);
            pcmsMaterialVersionModel.setCompany(userInfo.getEmployeeModel().getCompany());
            pcmsMaterialVersionModel.setCreate_time(new Date());
            pcmsMaterialVersionModel.setName(suffix);
            pcmsMaterialVersionModel.setVendor_id(vendor_id);
            pcmsMaterialVersionModel.setUrl(fileUrl+"/"+nameXls);
            pcmsMaterialVersionModel.setVersion(10000);
            pcmsMaterialVersionModel.setState(2);
            baseMapper.insert(pcmsMaterialVersionModel);
            return ResultVo.getDataWithSuccess(list);
        }
/*        List<PcmsMaterialVersionModel> list = baseMapper.selectMaterialVersion(vendor_id,userInfo.getEmployeeModel().getCompany());
        if(list.size() > 0 && null != list){
            PcmsMaterialVersionModel pcmsMaterialVersionModel1 = list.get(0);
            PcmsMaterialVersionModel pcmsMaterialVersionModel2 = baseMapper.selectById(pcmsMaterialVersionModel1.getId());
            pcmsMaterialVersionModel2.setState(0);
            baseMapper.updateById(pcmsMaterialVersionModel2);
        }*/
//        return ResultVo.getDataWithSuccess(list);
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
                                        value += "'" + cell.getNumericCellValue() + "',";
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
                    map.put(StringUtil.getUUID(), valueresult);
                }
            }
        } catch (Exception e) {
            File newFile = new File(filePath);
            newFile.delete();
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public ResultVo selectMaterialVersion(String vendor_id, LoginUserInfo userInfo) throws Exception {
        List<PcmsMaterialVersionModel> list = baseMapper.selectMaterialVersion(vendor_id,userInfo.getEmployeeModel().getCompany());
        return ResultVo.getDataWithSuccess(list);
    }

    @Override
    public ResultVo confirmSupplierMaterial(List<PcmsSupplierMaterialModel> list, LoginUserInfo userInfo) throws Exception {
        if(list.size()>0||null != list){
            List<PcmsSupplierMaterialModel> psmm = pcmsSupplierMaterialMapper.findSupplierMaterialByVendorAndCompany(list.get(0).getVendor_id(),userInfo.getEmployeeModel().getCompany());
            if(null != psmm && psmm.size()>0){
                List<Integer> listId = new ArrayList<>();
                for (PcmsSupplierMaterialModel id : psmm){
                    Integer ids = id.getId();
                    listId.add(ids);
                }
                pcmsSupplierMaterialService.deleteBatchIds(listId);
            }
//            pcmsSupplierMaterialService.insertBatch(list);
            /*List<PcmsSupplierMaterialModel> listVO = list.stream()
                    .map(pcmsSupplierMaterialVo->{
                        PcmsSupplierMaterialModel pcmsSupplierMaterialModel = new PcmsSupplierMaterialModel();
                        BeanUtils.copyProperties(pcmsSupplierMaterialVo,pcmsSupplierMaterialModel);
                        return pcmsSupplierMaterialModel;
                    }).collect(Collectors.toList());*/
            pcmsSupplierMaterialService.insertBatch(list);
            List<PcmsMaterialVersionModel> list1 = baseMapper.selectMaterialVersion(list.get(0).getVendor_id(),userInfo.getEmployeeModel().getCompany());
            if(list1.size() ==1){
                PcmsMaterialVersionModel pcmsMaterialVersionModel = baseMapper.selectById(list1.get(0).getId());
                pcmsMaterialVersionModel.setState(0);
                baseMapper.updateById(pcmsMaterialVersionModel);
            }else if(list1.size() > 1){
//                baseMapper.deleteById(list1.get(0).getId());
                PcmsMaterialVersionModel pcmsMaterialVersionModel = baseMapper.selectById(list1.get(0).getId());
                pcmsMaterialVersionModel.setState(0);
                baseMapper.updateById(pcmsMaterialVersionModel);
                PcmsMaterialVersionModel pcmsMaterialVersionModel2 = baseMapper.selectById(list1.get(1).getId());
                pcmsMaterialVersionModel2.setState(1);
                baseMapper.updateById(pcmsMaterialVersionModel2);
            }
        }
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo giveUpSupplierMaterial(String vendor_id, String url, LoginUserInfo userInfo) throws Exception {
        File newFile = new File(url);
        newFile.delete();
        List<PcmsMaterialVersionModel> list = baseMapper.selectMaterialVersion(vendor_id,userInfo.getEmployeeModel().getCompany());
        baseMapper.deleteById(list.get(0).getId());
        return ResultVo.get(ResultVo.SUCCESS);
    }

}
