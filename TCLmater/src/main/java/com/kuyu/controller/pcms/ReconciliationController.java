package com.kuyu.controller.pcms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.exception.ParamException;
import com.kuyu.model.pcms.PcmsCurrentDetailModel;
import com.kuyu.model.pcms.PcmsIinitializationModel;
import com.kuyu.model.pcms.PcmsMessageModel;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.*;
import com.kuyu.vo.query.ReconciliationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2018/12/26
 */
@AOP_Controller_LOG
@Api(tags = "对账接口")
@RequestMapping("/reconciliation")
public class ReconciliationController extends BaseController {
    @Resource
    private PcmsReconciliationService pcmsReconciliationService;

    @ApiOperation(value = "同步对账数据")
    @GetMapping("/synchronizedReconciliation")
    public void synchronizedReconciliation() throws Exception{
        pcmsReconciliationService.selectByTime();
    }

    /**
     * 自动生成未结明细记录
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "自动生成未结明细记录")
    @GetMapping("/automaticDetailList")
    public void automaticDetailList() throws Exception {
        pcmsReconciliationService.automaticDetailList();
    }

    /**
     * 有付款记录,但没有点击对账的,自动对账
     * @throws Exception
     */
    @ApiOperation(value = "有付款记录,但没有点击对账的,自动对账")
    @GetMapping("/automaticReconciliation")
    public void automaticReconciliation() throws Exception {
        pcmsReconciliationService.automaticReconciliation();
    }

    /**
     * 自动统计表上上个月余额为0的
     * @throws Exception
     */
    @ApiOperation(value = "自动统计表上上个月余额为0的")
    @GetMapping("/automaticStatistics")
    public void automaticStatistics() throws Exception {
        pcmsReconciliationService.automaticStatistics();
    }
    /**
     * 对账分页列表
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "对账分页列表",response = ReconciliationQuery.class)
    @PostMapping("/findReconciliationList")
    public ResultVo findReconciliationList(@RequestBody ReconciliationQuery query) throws Exception {
        return pcmsReconciliationService.findReconciliationList(getUserInfo(),query);
    }

    /**
     * 往来数据列表
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "往来数据列表",response = CurrentDetailModelVo.class)
    @GetMapping("/getReconciliation")
    public ResultVo getReconciliation(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.selectCurrentDetail(id,getUserInfo());
    }

    /**
     * 新增往来数据
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增往来数据",response = PcmsCurrentDetailModel.class)
    @PostMapping("/addCurrentDetail")
    public ResultVo addCurrentDetail(@RequestBody PcmsCurrentDetailModel query) throws Exception {
        return pcmsReconciliationService.addCurrentDetail(query);
    }

    /**
     * 确定生成
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定生成",response = PcmsIinitializationModel.class)
    @PostMapping("/addIinitialization")
    public ResultVo addIinitialization(@RequestBody PcmsIinitializationModel query) throws Exception {
        return pcmsReconciliationService.addIinitialization(query,getUserInfo());
    }

    /**
     * 发起对账
     * @param ids
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发起对账",response = PcmsReconciliationVo.class)
    @GetMapping("/reconciliation")
    public ResultVo reconciliation(@RequestParam(value = "ids") String[] ids)throws Exception{
        return pcmsReconciliationService.selectReconciliationList(ids);
    }

    /**
     * 确定发起对账
     * @param reconciliationVo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定发起对账",response = PcmsReconciliationVo.class)
    @GetMapping("/sureReconciliation")
    public ResultVo sureReconciliation(@RequestParam(value = "reconciliationVo") String reconciliationVo)throws Exception{
        String s = reconciliationVo.replace("&quot;","\"");
        ObjectMapper mapper = new ObjectMapper();
        List<PcmsReconciliationVo> list = mapper.readValue(s, new TypeReference<List<PcmsReconciliationVo>>() {});
        return pcmsReconciliationService.sureReconciliation(list);
    }

    /**
     * 获取对账单对账函
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取对账单对账函",response = AccountStatementVo.class)
    @GetMapping("/getAccountStatement")
    public ResultVo getAccountStatement(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.getAccountStatement(id,getUserInfo());
    }

    /**
     * 确定对账
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定对账")
    @GetMapping("/confirmReconciliation")
    public ResultVo confirmReconciliation(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.confirmReconciliation(id);
    }

    /**
     * 发送未结明细
     * @param pcmsMessageModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送未结明细",response = PcmsMessageModel.class)
    @PostMapping("/sendDetail")
    public ResultVo sendDetail(@RequestBody PcmsMessageModel pcmsMessageModel) throws Exception{
        return pcmsReconciliationService.sendDetail(pcmsMessageModel);
    }

    /**
     * 根据OPENID查询消息记录
     * @param openid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据供应商ID查询消息记录",response = PcmsMessageModel.class)
    @GetMapping("/selectByVendorId")
    public ResultVo selectByVendorId(@RequestParam(value = "openid") String openid) throws Exception{
        return pcmsReconciliationService.selectByVendorId(openid);
    }

    /**
     * 根据供应商ID查询历史消息记录
     * @param openid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据供应商ID查询历史消息记录",response = PcmsMessageModel.class)
    @GetMapping("/selectByState")
    public ResultVo selectByState(@RequestParam(value = "openid") String openid,@RequestParam(value = "state") Integer state) throws Exception{
        return pcmsReconciliationService.selectByState(openid,state);
    }

    /**
     * 修改银行信息
     * @param openid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "修改银行信息")
    @GetMapping("/updateBank")
    public ResultVo updateBank(@RequestParam(value = "openid") String openid,@RequestParam(value = "opening_bank") String opening_bank,@RequestParam(value = "opening_account") String opening_account) throws Exception{
        return pcmsReconciliationService.updateBank(openid,opening_bank,opening_account);
    }

    /**
     * 查看银行信息
     * @param openid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看银行信息")
    @GetMapping("/selectBankInfo")
    public ResultVo selectBankInfo(@RequestParam(value = "openid") String openid) throws Exception{
        return pcmsReconciliationService.selectBankInfo(openid);
    }


    /**
     * 查看消息详情
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看消息详情",response = PcmsMessageModel.class)
    @GetMapping("/selectMessageDetail")
    public ResultVo selectMessageDetail(@RequestParam(value = "id") Integer id,@RequestParam(value = "company") String company) throws Exception{
        return pcmsReconciliationService.selectMessageDetail(id,company);
    }

    /**
     * 供应商消息回复
     * @param replyMessageVo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "供应商消息回复",response = ReplyMessageVo.class)
    @PostMapping("/replyMessage")
    public ResultVo replyMessage(@RequestBody ReplyMessageVo replyMessageVo) throws Exception{
        return pcmsReconciliationService.replyMessage(replyMessageVo);
    }

    /**
     * 未结明细列表
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "未结明细列表",response = DetailVo.class)
    @GetMapping("/selectDetailList")
    public ResultVo selectDetailList(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.selectDetailList(id);
    }

    /**
     * 查看物料
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看物料",response = RequestPendingMaterialDetailVo.class)
    @GetMapping("/selectPendingMaterial")
    public ResultVo selectPendingMaterial(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.selectPendingMaterial(id);
    }

    /**
     * 同步期初余额
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "同步期初余额")
    @PostMapping("/synchronousBalance")
    public ResultVo synchronousBalance(@RequestParam(value="file", required=false) MultipartFile file) throws Exception {
        if(file.isEmpty()) {
            throw new ParamException(ResultVoUtils.fail("文件路径及完整文件名为空:"+file));
        }
        if(!file.getOriginalFilename().toLowerCase().endsWith(".xls") && !file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            throw new ParamException(ResultVoUtils.fail("文件不是Excel:"+file));
        }
        return pcmsReconciliationService.synchronousBalance(file,getLoginUserInfo());
    }

    /**
     * 导入利润中心数据
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "导入利润中心数据")
    @PostMapping("/importProfitCenter")
    public ResultVo importProfitCenter(@RequestParam(value="file", required=false) MultipartFile file) throws Exception {
        if(file.isEmpty()) {
            throw new ParamException(ResultVoUtils.fail("文件路径及完整文件名为空:"+file));
        }
        if(!file.getOriginalFilename().toLowerCase().endsWith(".xls") && !file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            throw new ParamException(ResultVoUtils.fail("文件不是Excel:"+file));
        }
        return pcmsReconciliationService.importProfitCenter(file);
    }
}
