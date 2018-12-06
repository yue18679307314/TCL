package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by pc on 2018/11/26
 */
public interface PcmsMaterialVersionService extends IService<PcmsMaterialVersionModel> {

    public ResultVo uploadAndInsert(MultipartFile file, String vendor_id, LoginUserInfo userInfo) throws Exception;
    /**查看历史版本物料清单*/
    public ResultVo selectMaterialVersion(String vendor_id, LoginUserInfo userInfo) throws Exception;

    /**确定导入*/
    public ResultVo confirmSupplierMaterial(List<PcmsSupplierMaterialModel> list, LoginUserInfo userInfo) throws Exception;

    /**放弃导入*/
    public ResultVo giveUpSupplierMaterial(String vendor_id, String url, LoginUserInfo userInfo)throws Exception;
}
