package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.vo.ReconciliationVo;
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
}
