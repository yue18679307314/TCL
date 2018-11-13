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

package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.WeixinEmployeeBindingMapper;
import com.kuyu.model.WeixinEmployeeBinding;
import com.kuyu.service.WeixinEmployeeBindingService;

import org.springframework.stereotype.Service;

/**
 * @author zkw
 * @since 2017-09-19 11:09
 */
@Service
public class WeixinEmployeeBindingServiceImpl extends ServiceImpl<WeixinEmployeeBindingMapper,WeixinEmployeeBinding> implements WeixinEmployeeBindingService{

	@Override
	public void insertWeixinEmployeeBinding(WeixinEmployeeBinding weixinEmployeeBinding){
		weixinEmployeeBinding.insert();
	}
	
	@Override
	public boolean deleteByPersonCode(String person_code){
		 baseMapper.deleteByPersonCode(person_code);
		 return true;
	}
	
	@Override
	public WeixinEmployeeBinding selectByCode(String person_code){
		return baseMapper.selectByCode(person_code);
	}
	
	@Override
	public WeixinEmployeeBinding selectByOpenid(String openId){
		return baseMapper.selectByOpenid(openId);
	}
}
