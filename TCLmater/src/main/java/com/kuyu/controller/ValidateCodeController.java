/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kuyu.controller;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.util.DateUtils;
import com.kuyu.util.VerifyCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@AOP_Controller_LOG
@Api(tags = "获取验证码接口")
@RequestMapping("/validateCode")
public class ValidateCodeController extends BaseController{
	
	 private static final Logger log = LoggerFactory.getLogger(ValidateCodeController.class);

    @RequestMapping(value = "/getValidateCode", method = RequestMethod.GET)
    @ApiOperation("获取验证码接口")
    public void getValidateCode(HttpServletResponse response) {
    	
    	
    	 response.setHeader("Pragma", "No-cache");  
         response.setHeader("Cache-Control", "no-cache");  
         response.setDateHeader("Expires", 0);  
         response.setContentType("image/jpeg");
           
         //生成随机字串  
         String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
         Map<String, String> validCodeMap = new HashMap<String,String>(); 
         validCodeMap.put("time", DateUtils.getLongDateStr());
         validCodeMap.put("verifyCode", verifyCode);
         this.getSession().setAttribute("verifyCode", validCodeMap);
         //存入会话session  
//         HttpSession session = request.getSession(true);  
//         session.setAttribute("rand", verifyCode.toLowerCase());  
         //生成图片  
         int w = 200, h = 80;  
         try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}  
    }

    @RequestMapping(value = "/getValidateCodetest", method = RequestMethod.GET)
    @ApiOperation("获取验证码接口测试")
    public String getValidateCodetest(HttpServletResponse response){
    	
    	return (String)this.getSession().getAttribute("verifyCode");
    }
    
}
