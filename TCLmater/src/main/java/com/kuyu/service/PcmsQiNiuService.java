package com.kuyu.service;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by pc on 2018/12/26
 */
public interface PcmsQiNiuService {

    /**
     * 上传文件
     * @param inputStream
     * @return
     */
    String put(InputStream inputStream);


    public Map<String,String> deleteByfileName(String fileName);


    public Map<String,String> getQiniuInfo();
}
