package com.kuyu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


//pcms模块工具类
public class PcmsProjectUtil {

	
	/**
	 * 生成立项单编号

	 */
	public  static String creatItemNumber(){
//		int machineId = 1;//最大支持1-9个集群机器部署
//        int hashCodeV = UUID.randomUUID().toString().hashCode();
//        if(hashCodeV < 0) {//有可能是负数
//            hashCodeV = - hashCodeV;
//        }
//        // 0 代表前面补充0     
//        // 4 代表长度为4     
//        // d 代表参数为正数型
//        return "LX"+machineId + String.format("%04d", hashCodeV);
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	        String newDate=sdf.format(new Date());
	        String result="";
	        Random random=new Random();
	        for(int i=0;i<3;i++){
	            result+=random.nextInt(10);
	        }
	        return "LX"+newDate+result;
	}
	
	/**
	 * 生成结算单编号

	 */
	public static String creatSettNumber(){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	        String newDate=sdf.format(new Date());
	        String result="";
	        Random random=new Random();
	        for(int i=0;i<3;i++){
	            result+=random.nextInt(10);
	        }
	        return "JS"+newDate+result;
	}

	/**
	 * 生成对账编号
	 * @return
	 */
	public static String creatReconciliationId(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate=sdf.format(new Date());
		String result="";
		Random random=new Random();
		for(int i=0;i<3;i++){
			result+=random.nextInt(10);
		}
		return "DZ"+newDate+result;
	}
	
	
	/**
	 * 截取共享传过来的公司编码
	 */
	public static String subCompanyCode(String requestCompanyCode){
		
		int i=requestCompanyCode.lastIndexOf("(");
		
        return requestCompanyCode.substring(i+1,requestCompanyCode.lastIndexOf(")"));
	}
	
	
	public static void main(String[] args) {
//		System.out.println(PcmsProjectUtil.creatItemNumber());
		
//		String test="惠州TCL电器(uytw)销售有限公司(7601)";
//		int i="惠州TCL电器(uytw)销售有限公司(7601)".lastIndexOf("(");
//		System.out.println(i);
//		String aaa=test.substring(i+1,test.lastIndexOf(")"));
//		
//		System.out.println(aaa);
		
//		String text="{\"RET_CODE\":\"9999\",\"RET_MSG\":\"\"}";
//		JSONObject aaa=JSON.parseObject(text);
////		aaa.get("RET_CODE");
//		System.out.println(aaa.get("RET_CODE"));
		
	}
	
	
	//惠州TCL电器销售有限公司(7601)
}
