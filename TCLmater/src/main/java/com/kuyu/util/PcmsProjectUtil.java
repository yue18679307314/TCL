package com.kuyu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kuyu.vo.pcms.PaymentRequest;


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
		
		String json="[{\"FSSC_BILL\":\"M4301812270107\",\"FINANCIAL_LIST\":[{\"FINANCIAL_NUM\":\"1\",\"FINANCIAL_MONEY\":\"1000\",\"FINANCIAL_TIME\":\"2018-12-27 15:40:27\",\"FINANCIAL_STATUS\":\"8\"}]},{\"FSSC_BILL\":\"M4301812270041\",\"FINANCIAL_LIST\":[{\"FINANCIAL_NUM\":\"1\",\"FINANCIAL_MONEY\":\"500\",\"FINANCIAL_TIME\":\"2018-12-27 11:28:03\",\"FINANCIAL_STATUS\":\"8\"}]}]";
		
		
		
		List<PaymentRequest> paymentRequest=JSON.parseArray(json, PaymentRequest.class);
		
//		PaymentRequest paymentRequest=JSONObject.parseObject(json, PaymentRequest.class);
		
		System.out.println(paymentRequest);
		
		
		BigDecimal successMoney=new BigDecimal("5.44");
		BigDecimal failMoney=new BigDecimal("1.12");
		String aaa=successMoney.add(failMoney).toString();
		System.out.println(aaa);
		System.out.println(aaa.equals("6.56"));
		String vendorId="ZX003772";
		if(vendorId.contains("ZX")){
		System.out.println("asfdsahjksdhg");
		}
		
	}

	//获取本月
	public static String getCheckDate() {
		Date time=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		
		return sdf.format(time)+"%";
	}
	
	
	//计算剩余可结算金额
	public static String calculation(String itemPrice,String apply,Integer addOrSub) {
		//汇总信息
		BigDecimal successMoney=new BigDecimal(itemPrice);
		BigDecimal failMoney=new BigDecimal(apply);
		BigDecimal sumMoney=new BigDecimal(0);
		
		if(addOrSub==1){
//			sumMoney=successMoney.add(failMoney).setScale(2,BigDecimal.ROUND_HALF_UP);
			sumMoney=successMoney.add(failMoney);
		}else{
//			sumMoney=successMoney.subtract(failMoney).setScale(2,BigDecimal.ROUND_HALF_UP);
			sumMoney=successMoney.subtract(failMoney);
		}
		
		return sumMoney.toString();
	}
	
	
	//惠州TCL电器销售有限公司(7601)
	
	
//	  /**
//     * 向指定 URL 发送POST方法的请求
//     * 
//     * @param url
//     *            发送请求的 URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     */
//    private static String sendPost(String url, String param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
	
	
}
