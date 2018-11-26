package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsMaterialVersionMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.service.PcmsMaterialVersionService;
import com.kuyu.util.StringUtil;
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
    @Value("${excel.path}")
    private String filePath;
    @Value("${excel.url}")
    private String fileUrl;
    @Override
    public void uploadAndInsert(MultipartFile file, String vendor_id, LoginUserInfo userInfo) throws Exception {

        /*String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if(".xls".equals(suffix)){

        }else{

        }*/
        String suffix = file.getOriginalFilename();
        Map<String, String> map  = readExcel(suffix);
        if(StringUtil.isNotNull(map)) {
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
                tdm.setComparison_price(Double.parseDouble(psm[5]));
                tdm.setNote(psm[6]);
                tdm.setVendor_id(vendor_id);
                tdm.setCreate_time(new Date());
                tdm.setCompany("5555");
                list.add(tdm);
            }
        }
        File tempfile = new File(filePath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        String path = filePath + file.getOriginalFilename();
        File newFile = new File(path);
        file.transferTo(newFile);
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
