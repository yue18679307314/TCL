package com.kuyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmAttendenceModel;
import com.kuyu.model.TpmRepaymentFeeDetailModel;
import com.kuyu.model.TpmRepaymentModel;
import com.kuyu.model.TpmRepaymentPayInfoModel;
import com.kuyu.vo.RepaymentManagementReturnVo;
import com.kuyu.vo.RepaymentManagementVo;
import com.kuyu.vo.RepaymentVo;

/**
 * Created by tang_zhen on 2017/9/21.
 */
@Mapper
public interface TpmRepaymentMapper extends BaseMapper<TpmRepaymentModel>{
     Integer insertRepaymentData(TpmRepaymentModel tpmRepaymentModel);
     Integer insertRepaymentPayInfoData(TpmRepaymentPayInfoModel tpmRepaymentPayInfoModel);
     Integer insertRepaymentFeeDetailData(TpmRepaymentFeeDetailModel tpmRepaymentFeeDetailModel);

     List<TpmRepaymentModel> selectRepaymentList(RepaymentVo repaymentVo);
     List<TpmRepaymentFeeDetailModel> selectRepaymentFeeDetailList(String requestId);
     List<TpmRepaymentPayInfoModel> selectRepaymentPayInfoList(String requestId);
     List<TpmRepaymentModel> financeSelectRepaymentList(RepaymentVo repaymentVo);
     List<TpmRepaymentModel> activityOrProjectSelectRepaymentList(RepaymentVo repaymentVo);

     String selectRequestUserName(String requestUser);
     
     List<RepaymentManagementReturnVo> queryRepaymentManagementList(RepaymentManagementVo rmv,Page<RepaymentManagementReturnVo> page);
     
     RepaymentManagementReturnVo queryRepaymentManagementDetails(String uuid);
     
     List<RepaymentManagementReturnVo> queryDownloadAttendences(String uuid);
     
     List<String> queryRequestUsers(List<String> list);
     
     List<TpmAttendenceModel> queryAttendences(RepaymentManagementVo rmv,Page<TpmAttendenceModel> page);

}
