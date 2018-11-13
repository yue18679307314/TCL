package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.mapper.TpmActivityMapper;
import com.kuyu.mapper.TpmActivityOriginalMapper;
import com.kuyu.mapper.TpmDeptMapper;
import com.kuyu.mapper.TpmEmployeeMapper;
import com.kuyu.mapper.TpmOtherFeeOriginalMapper;
import com.kuyu.mapper.TpmProjectMapper;
import com.kuyu.mapper.TpmPromotionFeeOriginalMapper;
import com.kuyu.mapper.TpmSingleUserMapper;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmActivityOriginalModel;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.TpmOtherFeeOriginalModel;
import com.kuyu.model.TpmProjectModel;
import com.kuyu.model.TpmPromotionFeeOriginalModel;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmProjectService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.util.RSAUtils;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ProjectActivityQuery;
import com.kuyu.vo.ProjectDetailVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.project.OtherFeeOriginalModelVo;
import com.kuyu.vo.project.ProjectDetialModelVo;
import com.kuyu.vo.project.ProjectModelVo;
import com.kuyu.vo.project.PromotionFeeOriginalModelVo;
import com.kuyu.vo.project.TpmActivityOriginalModelVo;

/**
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 立项单 Service实现类
 */
@Service
@Transactional
public class TpmProjectServiceImpl extends ServiceImpl<TpmProjectMapper, TpmProjectModel> implements TpmProjectService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TpmActivityMapper tpmActivityMapper;

	@Autowired
	private TpmActivityOriginalMapper tpmActivityOriginalMapper;

	@Autowired
	private TpmPromotionFeeOriginalMapper tpmPromotionFeeOriginalMapper;

	@Autowired
	private TpmOtherFeeOriginalMapper tpmOtherFeeOriginalMapper;

	@Autowired
	private TpmEmployeeMapper tpmEmployeeMapper;

	@Autowired
	private TpmDeptMapper tpmDeptMapper;

	@Autowired
	private UserRoleInfoMapper userRoleInfoMapper;

	@Autowired
	private TpmSingleUserMapper tpmSingleUserMapper;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;

	@Override
	public String importProjectDetail(ProjectDetialModelVo vo) throws Exception {
		// 插入立项单
		ProjectModelVo project = new ProjectModelVo();
		BeanUtils.copyProperties(vo, project);

		// 查询立项单是否存在
		TpmProjectModel tpmProjectModel = new TpmProjectModel();
		Integer projectCount = tpmProjectModel.selectCount("request_id={0}", project.getRequestId());
		if (projectCount > 0) {
			log.info(CommonConstants.PROJECT_DATA_EXISTS);
			throw new RuntimeException(CommonConstants.PROJECT_DATA_EXISTS);
		}
		BeanUtils.copyProperties(project, tpmProjectModel);
		tpmProjectModel.setRequestJson(JSON.toJSONString(vo));
		tpmProjectModel.insert();
		log.info("立项主表插入成功！！");

		List<TpmActivityOriginalModelVo> activityOriginalList = vo.getActivityOriginalList();

		if (CollectionUtils.isNotEmpty(activityOriginalList)) {
			// 插入活动元数据
			for (int i = 0; i < activityOriginalList.size(); i++) {
				TpmActivityOriginalModelVo activityOriginalModelVo = activityOriginalList.get(i);
				TpmActivityOriginalModel tpmActivityOriginalModel = new TpmActivityOriginalModel();
				BeanUtils.copyProperties(activityOriginalModelVo, tpmActivityOriginalModel);
				tpmActivityOriginalModel.setRequest_id(vo.getRequestId());
				// 查询立项单活动是否存在
				Integer activityCount = tpmActivityOriginalModel.selectCount("project_id={0}",
						tpmActivityOriginalModel.getProjectId());
				if (activityCount > 0) {
					log.info(CommonConstants.ACTIVITY_DATA_EXISTS);
					throw new RuntimeException(CommonConstants.ACTIVITY_DATA_EXISTS);
				}
				tpmActivityOriginalModel.insert();
				log.info("立项项目元数据插入成功！！");

				List<PromotionFeeOriginalModelVo> promotionFeeOriginalList = activityOriginalModelVo
						.getPromotionFeeOriginalList();
				if (CollectionUtils.isNotEmpty(promotionFeeOriginalList)) {
					// 插入活动(子表1)
					for (int j = 0; j < promotionFeeOriginalList.size(); j++) {
						PromotionFeeOriginalModelVo promotionFeeOriginalModelVo = promotionFeeOriginalList.get(j);
						Date date = DateUtils.getDateByStr(promotionFeeOriginalModelVo.getStartTime());
						Date endDate = DateUtils.addDay(date, promotionFeeOriginalModelVo.getTotalDay() - 1);
						promotionFeeOriginalModelVo
								.setEndTime(DateUtils.formatDate(endDate, DateUtils.DATE_FORMAT_DATEONLY));
						promotionFeeOriginalModelVo.setUuid(IdWorker.get32UUID());
						promotionFeeOriginalModelVo.setFeeDetailType(tpmActivityOriginalModel.getFeeDetailType());
						TpmPromotionFeeOriginalModel promotionFeeOriginalModel = new TpmPromotionFeeOriginalModel();
						BeanUtils.copyProperties(promotionFeeOriginalModelVo, promotionFeeOriginalModel);
						promotionFeeOriginalModel.insert();
						log.info("立项子表1插入成功！！");

						// 插入活动列表
						TpmActivityModel activityModel = new TpmActivityModel();
						BeanUtils.copyProperties(tpmProjectModel, activityModel);
						BeanUtils.copyProperties(tpmActivityOriginalModel, activityModel);
						BeanUtils.copyProperties(promotionFeeOriginalModel, activityModel);
						activityModel.setCreateTime(DateUtils.currentTime());
						activityModel.setActivityUuid(IdWorker.get32UUID());
						activityModel.setProjectName(tpmActivityOriginalModel.getProjectName() + "（"
								+ promotionFeeOriginalModelVo.getDistrict() + "）");
						activityModel.insert();
						log.info("立项活动插入成功！！");
					}
				}

				List<OtherFeeOriginalModelVo> otherFeeOriginalModelList = activityOriginalModelVo
						.getOtherFeeOriginalModelList();
				if (CollectionUtils.isNotEmpty(otherFeeOriginalModelList)) {
					// 插入其它费用(子表2)
					for (int j = 0; j < activityOriginalModelVo.getOtherFeeOriginalModelList().size(); j++) {
						OtherFeeOriginalModelVo otherFeeOriginalModelVo = otherFeeOriginalModelList.get(j);
						TpmOtherFeeOriginalModel tpmOtherFeeOriginalModel = new TpmOtherFeeOriginalModel();
						tpmOtherFeeOriginalModel.setFeeDetailType(tpmActivityOriginalModel.getFeeDetailType());
						BeanUtils.copyProperties(otherFeeOriginalModelVo, tpmOtherFeeOriginalModel);
						tpmOtherFeeOriginalModel.insert();
						log.info("立项子表2插入成功！！");
					}
				}
			}

		}

		log.info("成功导入立项单,单号为:{}", vo.getRequestId());

		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));

	}

	@Override
	public ResultVo findActivityDetailPageByQuery(LoginUserInfo userInfo, ProjectActivityQuery query) throws Exception {
		query = (ProjectActivityQuery) CheckParamUtils.trimWithObjectField(query);
		Page<ActivityDetailVo> page = new Page<>(query.getCurrent(), query.getSize());
		// 角色检查
		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = new ArrayList<>();
		if (personType == -1) {// 临促
			log.info("personType错误,为{}", personType);
			// throw new
			// ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
			return ResultVo.getDataWithSuccess(null);
		}
		if (personType == 2) {// 分公司财务
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if (CollectionUtils.isEmpty(childsDeptList)) {
				log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
				// throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
				return ResultVo.getDataWithSuccess(null);
			}
		}
		if(personType == 0 || personType == 6){
        	childsDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
          	if (CollectionUtils.isEmpty(childsDeptList)){
                log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
                return ResultVo.getDataWithSuccess(null);
            }
        }
		query.setRequestId(StringUtil.escapeStrWithLike(query.getRequestId()));
		query.setCompanyCode(StringUtil.escapeStrWithLike(query.getCompanyCode()));
		query.setRequestDeptName(StringUtil.escapeStrWithLike(query.getRequestDeptName()));
		query.setRequestUserName(StringUtil.escapeStrWithLike(query.getRequestUserName()));
		query.setRequestTitle(StringUtil.escapeStrWithLike(query.getRequestTitle()));
		query.setProjectName(StringUtil.escapeStrWithLike(query.getProjectName()));
		String city = query.getCity();
		if (StringUtils.isNotEmpty(city)) {
			if (city.endsWith(CommonConstants.CITY)) {
				int index = city.lastIndexOf(CommonConstants.CITY);
				city = city.substring(0, index);
			}
			query.setCity(StringUtil.escapeStrWithLike(city));
		}

		List<ActivityDetailVo> list = tpmActivityMapper.findActivityDetailPage(personType,
				userInfo.getEmployeeModel().getPerson_code(), childsDeptList, query, page);
		if (CollectionUtils.isEmpty(list)) {
			log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
			// throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
			return ResultVo.getDataWithSuccess(null);
		}
		for (ActivityDetailVo activityDetailVo : list) {
			// Integer count =
			// tpmSingleUserMapper.selectCount(Condition.create().eq("fssc_bill",
			// activityDetailVo
			if (personType == 1 || personType == 0 || personType == 6) {
				activityDetailVo.setHasCreateUser(1);
			} else {
				activityDetailVo.setHasCreateUser(0);
			}
			/*
			 * String person_code = userInfo.getEmployeeModel().getPerson_code();
			 * List<UserRoleInfoModel> userRoleInfoModels =
			 * userRoleInfoMapper.selectByPersonCode(person_code);
			 * activityDetailVo.setActivityRole(4); if
			 * (CollectionUtils.isEmpty(userRoleInfoModels)){ TpmActivityModel activityModel
			 * = tpmActivityMapper.selectById(activityDetailVo.getActivityUuid()); if
			 * (activityModel.getManager().equals(person_code)){
			 * activityDetailVo.setActivityRole(3); } }else { String type =
			 * userRoleInfoModels.get(0).getType(); if ("2".equals(type)){
			 * activityDetailVo.setActivityRole(2); }else if ("".equals(type)){
			 * activityDetailVo.setActivityRole(1);//admin } }
			 */

		}
		page.setRecords(list);
		return ResultVo.getDataWithSuccess(page);
	}

	@Override
	public ResultVo findProjectDetailByRequestId(LoginUserInfo userInfo, String requestId) throws Exception {
		requestId = CheckParamUtils.trimWithString(requestId);

		Integer personType = CheckParamUtils.getPersonType(userInfo);
		List<String> childsDeptList = null;
		TpmProjectModel project = baseMapper.selectById(requestId);
		if (null == project) {
			log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
			return ResultVo.getDataWithSuccess(null);
			// throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
		}
		String mobile = project.getRequestTelphone();
		// 加密电话号
		if (mobile != null) {
			byte[] decodedPwds2 = RSAUtils.encryptByPublicKey(mobile, RSAUtils.PUBLIC_KEY);
			BASE64Encoder enc = new BASE64Encoder();
			String newMobile = enc.encode(decodedPwds2);
			project.setRequestTelphone(newMobile);
		}
		if (personType == -1) {
			log.info("personType错误,为{}", personType);
			log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
			return ResultVo.getDataWithSuccess(null);
			// throw new
			// ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
		} else if (personType == 2) {
			childsDeptList = userRoleInfoMapper.findFinanceOrgCodeList(userInfo.getEmployeeModel().getPerson_code());
			if (CollectionUtils.isEmpty(childsDeptList) || !childsDeptList.contains(project.getRequestDept())) {
				log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
				// throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
				return ResultVo.getDataWithSuccess(null);
			}
		}else if (personType == 0 || personType == 6) {
			childsDeptList = tpmBranchAdminService.branchDeptList(userInfo.getEmployeeModel().getPerson_code());
			if (CollectionUtils.isEmpty(childsDeptList) || !childsDeptList.contains(project.getRequestDept())) {
				log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
				// throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
				return ResultVo.getDataWithSuccess(null);
			}
		}  
		else if (personType == 4) {
			String requestUser = project.getRequestUser();
			String person_code = userInfo.getEmployeeModel().getPerson_code();
			if (person_code.equals(requestUser)) {
				personType = 5;
			} else {
				personType = 3;
			}
		}

		TpmEmployeeModel tpmEmployeeModel = new TpmEmployeeModel();
		project.setRequestUserName(tpmEmployeeModel.getPerson_name());

		EntityWrapper<TpmActivityModel> ew = new EntityWrapper<>();
		ew.setSqlSelect("DISTINCT(project_id) projectId");
		if (personType == 1 || personType == 2 || personType == 5 || personType == 0 || personType == 6) {
			ew.where("request_id={0}", requestId);
		} else if (personType == 3) {
			ew.where("request_id={0} and manager={1}", requestId, userInfo.getEmployeeModel().getPerson_code());
		}
		List<TpmActivityModel> activityList = tpmActivityMapper.selectList(ew);
		if (CollectionUtils.isEmpty(activityList)) {
			log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
			// throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
			// return ResultVo.getDataWithSuccess(null);
		}
		List<TpmActivityOriginalModel> activityOriginalList = new ArrayList<>();
		List<TpmPromotionFeeOriginalModel> promotionFeeOriginalList = new ArrayList<>();
		List<TpmOtherFeeOriginalModel> otherFeeOriginalModelList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(activityList)) {
			for (TpmActivityModel activityModel : activityList) {
				String orgCode = activityModel.getDept();
				if (orgCode != null) {
					TpmDeptModel tdm = new TpmDeptModel();
					tdm.setOrg_code(orgCode);
					TpmDeptModel t = tpmDeptMapper.getTpmDept(tdm);
					if (t != null) {
						activityModel.setDeptName(t.getOrg_name());
					}
				}
				String projectId = activityModel.getProjectId();
				List<TpmPromotionFeeOriginalModel> promotionFeeOriginalModelList = tpmPromotionFeeOriginalMapper
						.selectList(Condition.create().eq("project_id", projectId));
				for (TpmPromotionFeeOriginalModel tpfom : promotionFeeOriginalModelList) {
					TpmActivityOriginalModel tpmActivityOriginal = tpmActivityOriginalMapper.selectById(projectId);
					tpfom.setProjectName(tpmActivityOriginal.getProjectName());
					String activityName = tpfom.getProjectName()+ "（"+ tpfom.getDistrict() + "）";
					Map<String,String> map = new HashedMap();
					map.put("projectId", projectId);
					map.put("activityName", activityName);
					String manager = tpmActivityMapper.selectManager(map);
					TpmEmployeeModel e = tpmEmployeeMapper.getTpmEmployeebyPersonCode(manager);
					if (e != null) {
						tpfom.setManagerName(e.getPerson_name());
					}
				}
				promotionFeeOriginalList.addAll(promotionFeeOriginalModelList);
			}
		}
		List<String> projectList = tpmActivityOriginalMapper.selectProjectIdByRequestId(requestId);
		if (projectList != null && projectList.size() > 0) {
			for (String projectID : projectList) {
				TpmActivityOriginalModel tpmActivityOriginal = tpmActivityOriginalMapper.selectById(projectID);
				
				List<TpmDeptModel> tpmDeptModelList = tpmDeptMapper
						.selectList(Condition.create().eq("org_code", tpmActivityOriginal.getDept()));
				if (CollectionUtils.isNotEmpty(tpmDeptModelList)) {
					tpmActivityOriginal.setDeptName(tpmDeptModelList.get(0).getOrg_name());
				}
				activityOriginalList.add(tpmActivityOriginal);
				
				List<TpmOtherFeeOriginalModel> tpmOtherFeeOriginalModelList = tpmOtherFeeOriginalMapper
						.selectList(Condition.create().eq("project_id", projectID));
				for (TpmOtherFeeOriginalModel tpmOtherFeeOriginalModel : tpmOtherFeeOriginalModelList) {
					tpmOtherFeeOriginalModel.setProjectName(tpmActivityOriginal.getProjectName());
				}
				otherFeeOriginalModelList.addAll(tpmOtherFeeOriginalModelList);
			}
		}
		// 封装返回数据
		ProjectDetailVo projectDetailVo = new ProjectDetailVo();
		List<TpmEmployeeModel> tpmEmployeeModelList = tpmEmployeeMapper
				.selectList(Condition.create().eq("person_code", project.getRequestUser()));
		if (CollectionUtils.isNotEmpty(tpmEmployeeModelList)) {
			project.setRequestUserName(tpmEmployeeModelList.get(0).getPerson_name());
		}
		List<TpmDeptModel> tpmDeptModelList = tpmDeptMapper
				.selectList(Condition.create().eq("org_code", project.getRequestDept()));
		if (CollectionUtils.isNotEmpty(tpmDeptModelList)) {
			project.setRequestDeptName(tpmDeptModelList.get(0).getOrg_name());
		}
		projectDetailVo.setProject(project);
		projectDetailVo.setActivityOriginalList(activityOriginalList);
		projectDetailVo.setPromotionFeeOriginalList(promotionFeeOriginalList);
		projectDetailVo.setOtherFeeOriginalModelList(otherFeeOriginalModelList);

		return ResultVo.getDataWithSuccess(projectDetailVo);
	}

}
