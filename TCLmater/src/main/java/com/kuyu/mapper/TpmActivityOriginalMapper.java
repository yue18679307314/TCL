package com.kuyu.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.TpmActivityOriginalModel;

/**
 * @Author jt_L
 * @Date 2017/9/24
 * @description
 */
public interface TpmActivityOriginalMapper extends BaseMapper<TpmActivityOriginalModel> {
	
	public List<String> selectProjectIdByRequestId(String request_id);
}
