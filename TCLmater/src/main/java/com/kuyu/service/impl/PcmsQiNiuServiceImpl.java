package com.kuyu.service.impl;

import com.google.gson.Gson;
import com.kuyu.service.PcmsQiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2018/12/26
 */
@Service
@Transactional
public class PcmsQiNiuServiceImpl implements PcmsQiNiuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket_1}")
    private String bucket_1;

    @Value("${qiniu.domain_1}")
    private String domain_1;

    private UploadManager uploadManager;

    public PcmsQiNiuServiceImpl() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
    }

    @Override
    public String put(InputStream inputStream) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = domain + putRet.key;
            logger.info("qiniu put success, url:" + url);
            return url;
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.error(r.toString());
            try {
                logger.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    @Override
    public Map<String, String> deleteByfileName(String fileName) {
        Map<String,String> result=new HashMap();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Long timeStr=new Date().getTime();
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone2());
            //实例化一个BucketManager对象
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取七牛信息
     * @return
     */
    @Override
    public Map<String, String> getQiniuInfo() {
        Map<String,String> result=new HashMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        result.put("upToken",upToken);
        result.put("domain",domain);
        return result;
    }
}
