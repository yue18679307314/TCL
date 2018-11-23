package com.kuyu.util;

import java.util.UUID;

//pcms模块工具类
public class PcmsProjectUtil {

	
	/**
	 * 生成立项单编号

	 */
	public synchronized static String creatItemNumber(){
		int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型
        return "LX"+machineId + String.format("%04d", hashCodeV);
	}
	
	public static void main(String[] args) {
		System.out.println(PcmsProjectUtil.creatItemNumber());
	}
}
