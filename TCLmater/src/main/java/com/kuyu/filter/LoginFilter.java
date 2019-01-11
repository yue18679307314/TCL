package com.kuyu.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kuyu.common.CommonConstants;
import com.kuyu.common.DataCache;
import com.kuyu.controller.BaseController;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.service.PropertiesConfigService;
import com.kuyu.util.StringUtil;

@Component("loginFilter")
public class LoginFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(LoginFilter.class);
    /**
     *
     * 直接放行的uri集合
     */
    private List<String> needLoginUris = new ArrayList<String>();
   
    @Value("${allow.multLogin}")
    private String multlogin; //1 允许，0 不允许
   
    @Value("${cache.expireAfterWrite}")
    private String expireTime; //单位 s
   
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        ServletRequest requestWrapper = null; 
        String param ="";
        if(!uri.startsWith("/tpm/fileupload/upload") && !uri.startsWith("/tpm/materialVersion/uploadExcel") && !uri.startsWith("/tpm/photoUpload/upload") && !uri.startsWith("/tpm/qiNiuUpload/uploadFile") && !uri.startsWith("/tpm/reconciliation/synchronousBalance") && !uri.startsWith("/tpm/reconciliation/importProfitCenter")){
        	if(req instanceof HttpServletRequest) {    
        		requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) req);  //替换  
        	} 
        	
        	if("POST".equalsIgnoreCase(req.getMethod())){
        		param = this.getBodyString(requestWrapper.getReader());
        		log.info("requestBody参数为{}",param);
        	}	
        }else{
        	requestWrapper = request;
        }
//        chain.doFilter(request, response);//测试中以下代码已注释,所有请求均放行
        if(uri.startsWith("redirect")||"/favicon.ico".equals(uri)){
        	chain.doFilter(requestWrapper, response);
        }
        LoginUserInfo loginUserInfo = (LoginUserInfo)req.getSession().getAttribute(CommonConstants.LOGIN_USER_INFO);
        String loginIndex = String.valueOf(req.getSession().getAttribute("loginIndex"));
        String loginedUserIndex ="";
        if(loginUserInfo != null&&loginUserInfo.getEmployeeModel()!=null){
        	loginedUserIndex = String.valueOf(BaseController.userLoginIndexMap.get(loginUserInfo.getEmployeeModel().getPerson_code()));        	
        }
        //针对一个账号只允许在一个地方登陆处理
        if("0".equals(multlogin)&&!uri.startsWith("/tpm/weixin/unbinding")&&!uri.startsWith("/tpm/v2/api-docs")
        		&&!uri.startsWith("/tpm/weixin/binding")&&!uri.startsWith("/tpm/validateCode/getValidateCode")
        		&&!uri.startsWith("/tpm/swagger")&&loginUserInfo != null&&loginUserInfo.getWeixinUserInfo()==null
        		&&loginUserInfo.getEmployeeModel()!=null
        		&&StringUtil.isNotBlank(loginUserInfo.getEmployeeModel().getPerson_code())
        		&&loginedUserIndex!=null&&!loginedUserIndex.equals(loginIndex))
        {
        		req.getSession().removeAttribute("loginIndex");
        		req.getSession().invalidate();
        		forceLogoutSendResult(resp);
        }else{
        	//针对重定向的处理
        	if(StringUtils.isNotEmpty(uri)&&this.stsrtsWith(needLoginUris,uri)) {//
        		if(loginUserInfo == null||loginUserInfo.getEmployeeModel()==null||StringUtil.isBlank(loginUserInfo.getEmployeeModel().getPerson_code())){
        			sendResult(resp);
        		}else{
        			chain.doFilter(requestWrapper, response);
        		}
        	}else{
        		chain.doFilter(requestWrapper, response);
        	}
        }
        
    }
    
        //获取request请求body中参数  
    public String getBodyString(BufferedReader br) {  
    	String inputLine;  
    	     String str = "";  
    	   try {  
    	     while ((inputLine = br.readLine()) != null) {  
    	      str += inputLine;  
    	     }  
    	     br.close();  
    	   } catch (IOException e) {  
    	     System.out.println("IOException: " + e);  
    	   }  
    	   return str;  
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    	needLoginUris.add("/qrcode/brand/selectByCode");
        needLoginUris.add("/tpm/tpmProject/project");
        needLoginUris.add("/tpm/tpmActivity/manager");
        needLoginUris.add("/tpm/tpmActivity/promotion");
        needLoginUris.add("/tpm/tpmActivity/activity");
    	needLoginUris.add("/tpm/tpmActualActivityData/manager");
    	needLoginUris.add("/tpm/tpmFinancial");
    	needLoginUris.add("/tpm/workAttendenceDetail/attendanceManagement");
    	needLoginUris.add("/tpm/workAttendenceDetail/adjustWorkingHours");
    	needLoginUris.add("/tpm/workAttendenceDetail/queryAttendanceManagement");
    	needLoginUris.add("/tpm/workAttendenceDetail/queryAttendanceDetails");
    	needLoginUris.add("/tpm/workAttendenceDetail/checkAttendanceManagement");
    	needLoginUris.add("/tpm/tpmUserStatement/queryStatement");
    	needLoginUris.add("/tpm/tpmUserStatement/adjustMoney");
    	needLoginUris.add("/tpm/tpmUserStatement/querySettlementManagement");
    	needLoginUris.add("/tpm/tpmUserStatement/querySettlementDetails");
    	needLoginUris.add("/tpm/tpmUserStatement/checkSettlementManagement");
    	needLoginUris.add("/tpm/tpmUserStatement/getUserRole");
    	needLoginUris.add("/tpm/tpmUserStatement/getToDoList");
    	needLoginUris.add("/tpm/tpmSingleUser/createSingleUser");
    	needLoginUris.add("/tpm/deptEmployee/getEmployeeByPersonCode");
    	needLoginUris.add("/tpm/deptEmployee/getDeptByOrgCode");
    	needLoginUris.add("/tpm/deptEmployee/getEmployeeList");
    	needLoginUris.add("/tpm/deptEmployee/getDeptList");
    	needLoginUris.add("/tpm/deptEmployee/queryChildsDept");

    	needLoginUris.add("/tpm/tpmOtpLogs/findLogs");
    	needLoginUris.add("/tpm/tpmUserBaseInfo/queryUserInfoList");
    	needLoginUris.add("/tpm/tpmUserBaseInfo/outputBankInfo");
    	needLoginUris.add("/tpm/tpmUserBaseInfo/getBankInfoUrl");
    	needLoginUris.add("/tpm/tpmUserBaseInfo/downloadBankInfo");
    	
    	needLoginUris.add("/tpm/tpmRepayment/queryRepaymentInfo");
    	needLoginUris.add("/tpm/tpmRepayment/queryRepaymentManagementList");
    	needLoginUris.add("/tpm/tpmRepayment/queryRepaymentManagementDetails");
    	needLoginUris.add("/tpm/tpmRepayment/importAttendence");
    	needLoginUris.add("/tpm/tpmRepayment/queryAttendences");
    	needLoginUris.add("/tpm/tpmRepayment/downloadUrl");
    	needLoginUris.add("/tpm/tpmRepayment/downloadAttendences");


		needLoginUris.add("/tpm/supplier/findPcmsSupplierListByPage");
//		needLoginUris.add("/tpm/market/getItemDetail");

    }

    private boolean stsrtsWith(List<String> list,String url){
    	for(String str:list){
    		if(url.startsWith(str)){
    			return true;
    		}
    	}
    	return false;
    }
    
    private void sendResult(HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "GET,OPTIONS,POST");
        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,uid,ihome-token,deviceToken,ihome-timestamp,ihome-imei,ihome-version,appversion");

        PrintWriter writer = resp.getWriter();
        writer.write("{\"code\":\"" +"403" + "\", \"msg\": \"会话失效或未登录\"}");
        writer.flush();
    }

    private void forceLogoutSendResult(HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "GET,OPTIONS,POST");
        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,uid,ihome-token,deviceToken,ihome-timestamp,ihome-imei,ihome-version,appversion");

        PrintWriter writer = resp.getWriter();
        writer.write("{\"code\":\"" +"403" + "\", \"msg\": \"此账号已在别处登录\"}");
        writer.flush();
    }
}
