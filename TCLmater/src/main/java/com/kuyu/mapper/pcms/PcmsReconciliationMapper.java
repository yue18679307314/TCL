package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.pcms.CurrentDetailModelVo;
import com.kuyu.vo.pcms.DetailListVo;
import com.kuyu.vo.pcms.PcmsPaymentDetailVo;
import com.kuyu.vo.pcms.PcmsReconciliationVo;
import com.kuyu.vo.query.ReconciliationQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/12/26
 */
public interface PcmsReconciliationMapper extends BaseMapper<PcmsReconciliationModel> {
    /**插入对账信息*/
    int insertReconciliation(PcmsReconciliationModel pcmsReconciliationModel);
    /**分页查询*/
    List<ReconciliationVo> findListPage(@Param("params") ReconciliationQuery query, Page<ReconciliationVo> page);

    List<CurrentDetailModelVo> selectCurrentDetail(@Param("id") Integer id);

    List<CurrentDetailModelVo> selectCurrent(@Param("id") Integer id);

    PcmsPaymentDetailVo selectByFssc(@Param("fsscBill") String fsscBill);

    PcmsReconciliationVo selectByReconciliationId(@Param("id") Integer id);

    List<PcmsReconciliationModel> selectByState();

    List<DetailListVo> selectDetailList(@Param("id") Integer id);

    List<ReconciliationVo> selectByMonth(@Param("month") String month);

}
