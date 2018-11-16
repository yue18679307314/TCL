
package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.vo.PcmsSupplierListVo;
import com.kuyu.vo.PcmsSupplierQuert;
import com.kuyu.vo.PcmsSupplierVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/14
 */
public interface PcmsSupplierMapper extends BaseMapper<PcmsSupplierModel> {
    /**新增供应商*/
    public Boolean insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo);
    /**查询供应商*/
    public PcmsSupplierModel getPcmsSupplier(PcmsSupplierVo pcmsSupplierVo);
    /**更新供应商*/
    public Boolean updatePcmsSupplier(PcmsSupplierVo pcmsSupplierVo);

    /**更新供应商*/
    public Boolean updatePcmsSupplierModel(PcmsSupplierModel pcmsSupplierModel);

    List<PcmsSupplierListVo> findPcmsSupplierListByPage(@Param("params") PcmsSupplierQuert query, Page<PcmsSupplierListVo> page);
}
