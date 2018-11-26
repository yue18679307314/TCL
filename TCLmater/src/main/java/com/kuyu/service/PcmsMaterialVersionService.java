package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.vo.PcmsSupplierVo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pc on 2018/11/26
 */
public interface PcmsMaterialVersionService extends IService<PcmsMaterialVersionModel> {

    public void uploadAndInsert(MultipartFile file, String vendor_id, LoginUserInfo userInfo) throws Exception;
}
