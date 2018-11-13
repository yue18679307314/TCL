package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.common.LogTypeEnum;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmOptLogsMapper;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.OperateLogsVo;
import com.kuyu.vo.ResultVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @Author tang_zhen
 * @Date 2017/9/26
 * @Description 日志操作的实现类
 */
@Service
public class TpmOptLogsServiceImpl extends ServiceImpl<TpmOptLogsMapper,TpmOptLogsModel>
        implements TpmOptLogsService {

    /**
     * 将日志操作的记录插入数据库
     * @param tpmOptLogsModel
     * @return ResultVo
     */
    @Autowired
    private TpmOptLogsMapper tpmOptLogsMapper;

    @Value("${logExcel.path}")
    private String filePath;

    @Value("${logExcel.url}")
    private String fileUrl;

    @Override
    public ResultVo insertOptLogs(TpmOptLogsModel tpmOptLogsModel) throws  Exception {
        if(tpmOptLogsModel.getType()==null //type必须在1-5中，其他数字错误
            ||tpmOptLogsModel.getCreateTime()==null)//创建时间不能为空
        {
            throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
        }
        tpmOptLogsMapper.insertOptLogsData(tpmOptLogsModel);
       return ResultVo.get(ResultVo.INSERT_SUCCESS);
    }

    /**
     * 按照类型或者日志内容从数据库中查出日志记录信息
     *
     *
     * @param person_code
     * @param personType
     * @param  operateLogsVo
     * @return list<TpmOptLogsModel>
     */
    @Override
    public Page<TpmOptLogsModel> selectLogsData(String person_code, Integer personType, OperateLogsVo operateLogsVo) throws Exception {
        //type ，startTime和endTime可以为空，或者同时为空都能查询数据
        //使用系统给定的分页工具
        Page<TpmOptLogsModel> page = new Page<>(operateLogsVo.getCurrent(),operateLogsVo.getSize());
        if(StringUtil.isNotNull(operateLogsVo.getStartTime()))
        {
            operateLogsVo.setStartTime(operateLogsVo.getStartTime()+" 00:00:00");
        }
        if(StringUtil.isNotNull(operateLogsVo.getEndTime()))
        {
            operateLogsVo.setEndTime(operateLogsVo.getEndTime()+" 23:59:59");
        }
        //处理下模糊查询日志的文字
        String content = "%"+StringUtil.trim(operateLogsVo.getContent())+"%";
            operateLogsVo.setContent(content);
        List<TpmOptLogsModel> list = null;
        if (personType==0||personType==6)
        {
            list = tpmOptLogsMapper.branchAdminSelectLogsByTypeOrTime(person_code,operateLogsVo,page);
        }else {
             list = tpmOptLogsMapper.selectLogsByTypeOrTime(operateLogsVo, page);
        }
        //List<TpmOptLogsModel> list = tpmOptLogsMapper.selectLogsByTypeOrTime(operateLogsVo);
        page.setRecords(list);
        return page;
    }

    @Override
    public ResultVo importService(OperateLogsVo operateLogsVo) throws Exception {

       /* if("".equals(logIds)||logIds==null)
        {
            return ResultVo.getByEnumCode(ResultVo.SUCCESS);
        }*/
//        String[] logIdsArray = logIds.trim().split(",");
 /*       if(logIdsArray.length==0)
        {
            return ResultVo.getByEnumCode(CommonConstants.SELECT_IS_NULL_CODE);
        }*/
        ResultVo resultVo = new ResultVo("0", "success");


        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet= wb.createSheet("test");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        XSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("操作人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("内容");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        //operateLogsVo.setType(8);
        String download = operateLogsVo.getDownloadFlag();
        List<TpmOptLogsModel> list = new ArrayList<TpmOptLogsModel>();
        Page<TpmOptLogsModel> page = new Page(operateLogsVo.getCurrent(),operateLogsVo.getSize());
    //处理下数据
        if(StringUtil.isNotNull(operateLogsVo.getStartTime()))
        {
            operateLogsVo.setStartTime(operateLogsVo.getStartTime()+" 00:00:00");
        }
        if(StringUtil.isNotNull(operateLogsVo.getEndTime()))
        {
            operateLogsVo.setEndTime(operateLogsVo.getEndTime()+" 23:59:59");
        }
        //处理下模糊查询日志的文字
        String content = "%"+StringUtil.trim(operateLogsVo.getContent())+"%";
        operateLogsVo.setContent(content);

        if("0".equals(download)) {
            list = tpmOptLogsMapper.selectLogsPages(operateLogsVo,page);
        }else {
           list = tpmOptLogsMapper.selectLogs(operateLogsVo);
        }

        for (int i = 0; i < list.size(); i++)
        {
            //枚举类获得日志类型对应的汉字标题
           String type = LogTypeEnum.getTypeName(list.get(i).getType());

            row = sheet.createRow((int) i + 1);
           TpmOptLogsModel tpmOptLogsModel = (TpmOptLogsModel)list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(type);
            row.createCell((short) 1).setCellValue(tpmOptLogsModel.getOptUser());
            row.createCell((short) 2).setCellValue(tpmOptLogsModel.getCreateTime());
            cell = row.createCell((short) 3);
            cell.setCellValue(tpmOptLogsModel.getContent());
        }
        // 第六步，将文件存到指定位置
        String filename = UUID.randomUUID().toString().replace("-", "")+"日志记录";
        try
        {
            File tempfile = new File(filePath);
            if(!tempfile.exists()){
                tempfile.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(filePath+"/"+filename+".xls");
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  resultVo.get(ResultVo.FAIL);
        }
        Map<String, Object> resultData = new HashMap<String, Object>();
        resultData.put("fileName",fileUrl+"/"+filename+".xls");
        resultVo.setData(resultData);
        return resultVo;
    }



}


