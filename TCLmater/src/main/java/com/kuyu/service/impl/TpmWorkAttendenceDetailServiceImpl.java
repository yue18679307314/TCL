package com.kuyu.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.TpmWorkAttendenceDetailMapper;
import com.kuyu.model.TpmWorkAttendenceDetailModel;
import com.kuyu.service.TpmWorkAttendenceDetailService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;

@Service
@Transactional
public class TpmWorkAttendenceDetailServiceImpl
		extends ServiceImpl<TpmWorkAttendenceDetailMapper, TpmWorkAttendenceDetailModel>
		implements TpmWorkAttendenceDetailService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 打卡
	 * 
	 * @param tpmWorkAttendenceDetailModel
	 *  打卡需要的信息：临促openid，参加的活动，城市，打卡地址，上传图片url
	 */
	@Override
	public ResultVo signIn(TpmWorkAttendenceDetailModel tpmWorkAttendenceDetailModel) throws Exception {
		if (StringUtil.isEmpty(tpmWorkAttendenceDetailModel.getOpenid())) {
			log.info(CommonConstants.OPENID_NOT_FOUND_MSG);
//			throw new ParamException(ResultVo.get(ResultVo.PARAM_ERROR));
			return ResultVo.getByEnumCode(CommonConstants.OPENID_NOT_FOUND_CODE);
		}
		baseMapper.signIn(tpmWorkAttendenceDetailModel);
		return ResultVo.get(ResultVo.SUCCESS);
	}

	/**
	 * 查询打卡记录
	 * @param map
	 * @return
	 */
	@Override
	public List<TpmWorkAttendenceDetailModel> queryList(Map<String, Object> map) {
			String dateTime = (String) map.get("dateTime");
			String startTime = new StringBuffer().append(dateTime).append(" 00:00:00").toString();
			String endTime = new StringBuffer().append(dateTime).append(" 23:59:59").toString();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
		return baseMapper.queryList(map);
	}
}
