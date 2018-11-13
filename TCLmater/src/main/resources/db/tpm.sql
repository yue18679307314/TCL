
create database tpm;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_name` varchar(100) DEFAULT NULL COMMENT '省名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT '省名称表';

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '重庆市');
INSERT INTO `province` VALUES ('2', '浙江省');
INSERT INTO `province` VALUES ('3', '云南省');
INSERT INTO `province` VALUES ('4', '新疆');
INSERT INTO `province` VALUES ('5', '西藏');
INSERT INTO `province` VALUES ('6', '天津市');
INSERT INTO `province` VALUES ('7', '四川');
INSERT INTO `province` VALUES ('8', '上海市');
INSERT INTO `province` VALUES ('9', '陕西省');
INSERT INTO `province` VALUES ('10', '山西省');
INSERT INTO `province` VALUES ('11', '山东省');
INSERT INTO `province` VALUES ('12', '青海省');
INSERT INTO `province` VALUES ('13', '宁夏');
INSERT INTO `province` VALUES ('14', '内蒙古');
INSERT INTO `province` VALUES ('15', '江苏');
INSERT INTO `province` VALUES ('16', '吉林省');
INSERT INTO `province` VALUES ('17', '湖南省');
INSERT INTO `province` VALUES ('18', '湖北省');
INSERT INTO `province` VALUES ('19', '黑龙江');
INSERT INTO `province` VALUES ('20', '河南');
INSERT INTO `province` VALUES ('21', '河北省');
INSERT INTO `province` VALUES ('22', '海南省');
INSERT INTO `province` VALUES ('23', '贵州省');
INSERT INTO `province` VALUES ('24', '广西');
INSERT INTO `province` VALUES ('25', '广东省');
INSERT INTO `province` VALUES ('26', '甘肃省');
INSERT INTO `province` VALUES ('27', '福建');
INSERT INTO `province` VALUES ('28', '北京市');
INSERT INTO `province` VALUES ('29', '安徽省');
INSERT INTO `province` VALUES ('30', '辽宁省');
INSERT INTO `province` VALUES ('31', '江西');
INSERT INTO `province` VALUES ('32', '台湾');


DROP TABLE IF EXISTS `tpm_activity`;
CREATE TABLE `tpm_activity` (
  `activity_uuid` varchar(32) NOT NULL COMMENT '活动唯一标示',
  `activity_name` varchar(255) NOT NULL COMMENT '活动名称',
  `request_id` varchar(32) NOT NULL COMMENT '立项申请单编号',
  `project_id` varchar(200) NOT NULL COMMENT '立项申请单活动id',
  `request_create_time` varchar(20) NOT NULL COMMENT '申请日期',
  `dept` varchar(32) DEFAULT NULL COMMENT '预算部门',
  `cost_center` varchar(32) DEFAULT NULL COMMENT '成本中心',
  `wbs` varchar(32) DEFAULT NULL COMMENT '项目（WBS）',
  `city` varchar(20) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(32) DEFAULT NULL COMMENT '门店/区域',
  `manager` varchar(20) DEFAULT NULL COMMENT '现场负责人',
  `start_time` varchar(20) DEFAULT NULL COMMENT '开始日期(YYYY-MM-DD HH(24)-mm-ss)',
  `end_time` varchar(20) DEFAULT NULL COMMENT '结束日期(YYYY-MM-DD HH(24)-mm-ss)',
  `total_day` int(11) DEFAULT NULL COMMENT '总天数',
  `user_no` int(11) DEFAULT NULL COMMENT '人数',
  `work_hours` int(11) DEFAULT NULL COMMENT '工作时长',
  `unit_price` double DEFAULT NULL COMMENT '人天单价',
  `incentive_amount` double DEFAULT NULL COMMENT '其它激励金额',
  `settlement_method` varchar(32) DEFAULT NULL COMMENT '结算方式',
  `settlement_cycle` varchar(32) DEFAULT '' COMMENT '结算周期',
  `total_fee` double NOT NULL COMMENT '费用总金额',
  `create_time` varchar(20) NOT NULL COMMENT '生成时间(YYYY-MM-DD HH(24)-mm-ss)',
  PRIMARY KEY (`activity_uuid`),
  KEY `tpm_activity_index_request_id` (`request_id`),
  KEY `tpm_activity_index_project_id` (`project_id`),
  KEY `tpm_activity_index_manager` (`manager`),
  KEY `tpm_activity_index_city` (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动列表';

DROP TABLE IF EXISTS `tpm_activity_original`;
CREATE TABLE `tpm_activity_original` (
  `project_id` varchar(32) NOT NULL COMMENT '活动id',
  `request_id` varchar(32) NOT NULL COMMENT '申请单id',
  `project_name` varchar(32) NOT NULL COMMENT '活动名称',
  `fee_detail_type` varchar(255) DEFAULT NULL COMMENT '费用细类',
  `dept` varchar(40) NOT NULL COMMENT '预算部门',
  `city` varchar(32) DEFAULT NULL COMMENT '所在城市',
  `cost_center` varchar(32) DEFAULT NULL COMMENT '成本中心',
  `wbs` varchar(32) DEFAULT NULL COMMENT '项目（WBS）',
  `order_no` varchar(32) DEFAULT NULL COMMENT '渠道/内部订单',
  `total_fee` double DEFAULT NULL COMMENT '促销费用总金额',
  `projected_sales_no` int(11) DEFAULT NULL COMMENT '预计零售量',
  `projected_sales_money` double DEFAULT NULL COMMENT '预计零售额',
  `estimated_cost_rate` double DEFAULT NULL COMMENT '预计费用率',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注（申请人填写）',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='立项关联的活动元数据';

DROP TABLE IF EXISTS `tpm_actual_activity_data`;
CREATE TABLE `tpm_actual_activity_data` (
  `activity_uuid` varchar(32) NOT NULL COMMENT '活动uuid',
  `actual_sales_no` int(11) NOT NULL COMMENT '实际销售量',
  `actual_sales_money` double DEFAULT NULL COMMENT '实际销售额',
  `actual_cost` double DEFAULT NULL COMMENT '实际花费',
  `actual_cost_rate` double(255,0) DEFAULT NULL COMMENT '实际费用率',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `lastupdate_user` varchar(32) NOT NULL COMMENT '最后修改用户',
  PRIMARY KEY (`activity_uuid`),
  UNIQUE KEY `activity_uuid` (`activity_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动实际上报数据表';

DROP TABLE IF EXISTS `tpm_dept`;
CREATE TABLE `tpm_dept` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `org_code` varchar(24) NOT NULL COMMENT '部门编码',
  `org_name` varchar(100) NOT NULL COMMENT '部门名称',
  `org_code_parent` varchar(24) NOT NULL COMMENT '上级部门编码',
  `org_name_parent` varchar(100) NOT NULL COMMENT '上级部门名称',
  `org_type` varchar(90) NOT NULL COMMENT '组织类型',
  `update_flag` varchar(24) DEFAULT NULL COMMENT '更新标识',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `org_code` (`org_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

DROP TABLE IF EXISTS `tpm_employee`;
CREATE TABLE `tpm_employee` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `person_code` varchar(15) NOT NULL COMMENT '员工工号',
  `person_name` varchar(180) NOT NULL COMMENT '员工姓名',
  `itcode` varchar(90) NOT NULL COMMENT '员工账号',
  `email` varchar(90) DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(90) DEFAULT NULL COMMENT '电话号码',
  `zhiwu_code` varchar(32) DEFAULT NULL COMMENT '职务编码',
  `zhiwu_name` varchar(240) DEFAULT NULL COMMENT '职务名称',
  `org_code` varchar(32) NOT NULL COMMENT '所在部门编码',
  `org_name` varchar(240) NOT NULL COMMENT '所在部门名称',
  `company` varchar(32) DEFAULT NULL COMMENT '公司代码',
  `del_flag` varchar(3) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `person_code` (`person_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';

DROP TABLE IF EXISTS `tpm_loan_bill`;
CREATE TABLE `tpm_loan_bill` (
  `load_bill_uuid` varchar(32) NOT NULL DEFAULT '' COMMENT '唯一值',
  `fssc_bill` varchar(32) DEFAULT NULL COMMENT '立项申请单编号',
  `fssc_loan_bill_no` varchar(32) NOT NULL COMMENT '共享借款的编号',
  `payment_id` varchar(30) NOT NULL COMMENT '共享付款的id',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间：YYYY-MM-DD HH(24):mm:ss',
  `status` int(11) DEFAULT NULL COMMENT '借款单状态:1 财务审核中；2 审核通过；3 已付款；4 已报销；',
  `resultvojson` text COMMENT '付款成功后返回的json数据',
  PRIMARY KEY (`load_bill_uuid`),
  KEY `tpm_loan_bill_index_fssc_loan_bill_no` (`fssc_loan_bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款单表';

DROP TABLE IF EXISTS `tpm_logs`;
CREATE TABLE `tpm_logs` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(3) NOT NULL COMMENT '日志操作类型：1 修改银行信息；2 pc端考勤审核；3 移动端考勤申请；4 pc端结算审核；5 移动端结算审核；6 修改立项单可使用人;7修改活动负责人',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `opt_user` varchar(20) DEFAULT NULL COMMENT '操作人',
  `content` varchar(255) DEFAULT NULL COMMENT '修改内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

DROP TABLE IF EXISTS `tpm_other_fee_original`;
CREATE TABLE `tpm_other_fee_original` (
  `project_id` varchar(32) NOT NULL COMMENT '活动ID',
  `fee_detail_type` varchar(255) DEFAULT NULL COMMENT '费用细类',
  `material_category` varchar(32) DEFAULT NULL COMMENT '物料类别',
  `specifications` varchar(32) DEFAULT NULL COMMENT '规格',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `unit_price` double DEFAULT NULL COMMENT '单价',
  `other_total_fee` double DEFAULT NULL COMMENT '其它促销费用总金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='立项关联的其它费用表(子表项目2)';

DROP TABLE IF EXISTS `tpm_project`;
CREATE TABLE `tpm_project` (
  `request_id` varchar(32) NOT NULL COMMENT '申请单ID',
  `request_title` varchar(255) NOT NULL COMMENT '申请标题',
  `request_user` varchar(30) NOT NULL COMMENT '申请人',
  `request_create_time` varchar(20) NOT NULL COMMENT '申请时间(YYYY-MM-DD)',
  `request_end_time` varchar(20) DEFAULT NULL COMMENT '申请结束日期(YYYY-MM-DD)',
  `request_telphone` varchar(20) NOT NULL COMMENT '申请人手机',
  `request_email` varchar(50) DEFAULT NULL COMMENT '申请人邮箱',
  `request_dept` varchar(40) DEFAULT NULL COMMENT '申请人部门',
  `request_info` varchar(255) DEFAULT NULL COMMENT '申请事由',
  `request_company_code` varchar(40) DEFAULT NULL COMMENT '公司代码',
  `request_json` text COMMENT '立项json数据',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='立项单';

DROP TABLE IF EXISTS `tpm_promotion_fee_original`;
CREATE TABLE `tpm_promotion_fee_original` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '活动ID',
  `fee_detail_type` varchar(255) DEFAULT NULL COMMENT '费用细类',
  `district` varchar(20) DEFAULT NULL COMMENT '门店/区域',
  `manager` varchar(10) DEFAULT NULL COMMENT '现场负责人',
  `start_time` varchar(20) NOT NULL COMMENT '开始日期(YYYY-MM-DD)',
  `end_time` varchar(20) NOT NULL COMMENT '结束日期(YYYY-MM-DD)',
  `total_day` int(2) DEFAULT NULL COMMENT '总天数',
  `user_no` int(5) DEFAULT NULL COMMENT '人数',
  `work_hours` int(2) DEFAULT NULL COMMENT '工作时长',
  `unit_price` double DEFAULT NULL COMMENT '人天单价',
  `incentive_amount` double DEFAULT NULL COMMENT '其它激励金额',
  `settlement_method` varchar(64) DEFAULT NULL COMMENT '结算方式',
  `settlement_cycle` varchar(64) DEFAULT NULL COMMENT '结算周期',
  `total_fee` double DEFAULT NULL COMMENT '临促费用总金额',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='立项关联的临促费用表(子表项目1)';

DROP TABLE IF EXISTS `tpm_properties_config`;
CREATE TABLE `tpm_properties_config` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pkey` varchar(50) DEFAULT NULL COMMENT '查询key',
  `pvalue` varchar(255) DEFAULT NULL COMMENT 'value值',
  `note` varchar(255) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='配置信息表';

-- ----------------------------
-- Records of tpm_properties_config
-- ----------------------------
INSERT INTO `tpm_properties_config` VALUES ('1', 'token', 'wxtpm', '腾讯后台设置的token');
INSERT INTO `tpm_properties_config` VALUES ('2', 'appid', 'wx962f424e953c00f7', '腾讯后台设置的token');
INSERT INTO `tpm_properties_config` VALUES ('3', 'appsecret', '7e8daaa64fc36e4242f5a1bc0ef84309', '腾讯后台设置的token');
INSERT INTO `tpm_properties_config` VALUES ('4', 'accesstoken', '', '腾讯后台设置的token');
INSERT INTO `tpm_properties_config` VALUES ('5', 'get_token_url', 'https://api.weixin.qq.com/sns/oauth2/access_token', '网页鉴权获取access_token及openid的url');
INSERT INTO `tpm_properties_config` VALUES ('6', 'publicid', 'gh_e50a6bbace03', '');
INSERT INTO `tpm_properties_config` VALUES ('7', 'getweixinUserInfoUrl', 'https://api.weixin.qq.com/sns/userinfo', '网页鉴权获取微信用户信息');
INSERT INTO `tpm_properties_config` VALUES ('8', 'subscribeGetWeiXinUserInfoUrl', 'https://api.weixin.qq.com/cgi-bin/user/info', '获取微信用户信息');
INSERT INTO `tpm_properties_config` VALUES ('9', 'importBorrowCronTime', '50 15 15', '修改导入借款单任务时间');
INSERT INTO `tpm_properties_config` VALUES ('10', 'title', '临促新增银行账号', '导入财务共享的标题');
INSERT INTO `tpm_properties_config` VALUES ('11', 'request_user', '00024518', '申请人员工号');
INSERT INTO `tpm_properties_config` VALUES ('12', 'request_depart', '90001279', '申请人部门编号');
INSERT INTO `tpm_properties_config` VALUES ('13', 'getweixinAccessTokenUrl', 'https://api.weixin.qq.com/cgi-bin/token', '获取微信用户信息');
INSERT INTO `tpm_properties_config` VALUES ('14', 'managerUrl', 'http://sctgf.tcl.com/manger/index', '现场负责人首页');
INSERT INTO `tpm_properties_config` VALUES ('15', 'lincuUrl', 'http://sctgf.tcl.com/', '临促首页');
INSERT INTO `tpm_properties_config` VALUES ('16', 'financialUrl', 'http://sctgf.tcl.com/finance/projectList', '财务负责人首页');
INSERT INTO `tpm_properties_config` VALUES ('17', 'jssdkticket', '', '微信jssdk使用凭证');
INSERT INTO `tpm_properties_config` VALUES ('18', 'sendTemplateMsgUrl', 'https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=', '发送模板信息');
INSERT INTO `tpm_properties_config` VALUES ('19', 'userssoFlag', '1', '是否调用IDM登陆认证验证密码');
INSERT INTO `tpm_properties_config` VALUES ('20', 'allow_project_ids', '', '借款单允许导入的key');
INSERT INTO `tpm_properties_config` VALUES ('21', 'access_token_update_time', '', 'access_token更新时间');

DROP TABLE IF EXISTS `tpm_repayment_fee_detail`;
CREATE TABLE `tpm_repayment_fee_detail` (
  `detail_id` varchar(32) NOT NULL COMMENT '财务共享导入的detail_id(即财务共享导过来的request_id)',
  `request_id` varchar(32) NOT NULL COMMENT '报销单编号',
  `request_no` varchar(32) NOT NULL COMMENT '序号',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `project_request_no` varchar(32) DEFAULT NULL COMMENT '事项(立项)申请单号',
  `cost_category` varchar(32) DEFAULT NULL COMMENT '费用明细',
  `accounting_subject` varchar(32) DEFAULT NULL COMMENT '核算科目',
  `cost_dept` varchar(32) DEFAULT NULL COMMENT '预算部门',
  `cost_center` varchar(32) DEFAULT NULL COMMENT '成本中心',
  `wbs` varchar(32) DEFAULT NULL COMMENT '项目（wbs）',
  `channel` varchar(32) DEFAULT NULL COMMENT '渠道/内部订单',
  `Invoice_type` varchar(32) NOT NULL COMMENT '发票类型',
  `Invoice_no` varchar(20) DEFAULT NULL COMMENT '发票号',
  `amount` double DEFAULT NULL COMMENT '原币金额',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销单费用明细表';

DROP TABLE IF EXISTS `tpm_repayment_main`;
CREATE TABLE `tpm_repayment_main` (
  `request_id` varchar(32) NOT NULL COMMENT '申请单编号',
  `request_title` varchar(200) NOT NULL COMMENT '申请标题',
  `request_user` varchar(10) NOT NULL COMMENT '申请人',
  `request_create_time` varchar(20) NOT NULL COMMENT '申请日期',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `note` varchar(255) DEFAULT NULL COMMENT '事项说明',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销单表';

DROP TABLE IF EXISTS `tpm_repayment_pay_info`;
CREATE TABLE `tpm_repayment_pay_info` (
  `detail_id` varchar(32) NOT NULL COMMENT '财务共享导入的detail_id(即财务共享导过来的request_id)',
  `request_id` varchar(32) NOT NULL COMMENT '报销单编号',
  `repayment_no` varchar(32) NOT NULL COMMENT '序号',
  `loan_bill_no` varchar(32) DEFAULT NULL COMMENT '借款单/预付款编号',
  `loan_amount` double DEFAULT NULL COMMENT '借款（预付款）金额',
  `supplier` varchar(32) DEFAULT NULL COMMENT '供应商',
  `available_amount` double DEFAULT NULL COMMENT '可用金额',
  `original_currency_amount` double DEFAULT NULL COMMENT '本次还款原币金额',
  `repayment_amount` double DEFAULT NULL COMMENT '还款本币金额',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销单付款信息';

DROP TABLE IF EXISTS `tpm_single_user`;
CREATE TABLE `tpm_single_user` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `fssc_bill` varchar(64) NOT NULL COMMENT '共享申请单单据号',
  `person_code` varchar(15) NOT NULL COMMENT '员工工号',
  `op_time` varchar(32) DEFAULT NULL COMMENT '操作时间',
  `request_user_original` varchar(32) DEFAULT NULL COMMENT '原立项人工号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='立项单可使用人';

DROP TABLE IF EXISTS `tpm_user_activity`;
CREATE TABLE `tpm_user_activity` (
  `uuid` varchar(32) NOT NULL COMMENT '临促参加编号',
  `activity_uuid` varchar(32) NOT NULL COMMENT '活动id',
  `openid` varchar(32) NOT NULL COMMENT '临促openid',
  `request_time` varchar(20) NOT NULL COMMENT '申请时间(YYYY-MM-DD HH(24)-mm-ss)',
  `request_state` int(2) NOT NULL COMMENT '参加的状态(0 现场负责人已确认拒绝参加；1 申请中； 2 现场负责人已确认可以参加；默认为1)',
  `reason` varchar(255) DEFAULT NULL COMMENT '审核原因',
  `audit_time` varchar(20) DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`uuid`),
  KEY `tpm_user_activity_activity_uuid` (`activity_uuid`),
  KEY `tpm_user_activity_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临促参加活动';

DROP TABLE IF EXISTS `tpm_user_base_info`;
CREATE TABLE `tpm_user_base_info` (
  `openid` varchar(32) NOT NULL COMMENT 'openID',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证',
  `province` varchar(10) DEFAULT NULL COMMENT '所在省',
  `city` varchar(10) DEFAULT NULL COMMENT '所在市',
  `district` varchar(10) DEFAULT NULL COMMENT '所在区县',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `account_name` varchar(20) NOT NULL COMMENT '户名',
  `account_bank_name` varchar(64) NOT NULL COMMENT '开户银行名称',
  `account_value` varchar(32) NOT NULL COMMENT '银行账号',
  `open_branch` varchar(30) NOT NULL COMMENT '开户网点',
  `open_province` varchar(10) NOT NULL COMMENT '开户省',
  `open_city` varchar(20) NOT NULL COMMENT '开户市',
  `idimgurl_front` varchar(355) DEFAULT NULL COMMENT '身份证正面照片url',
  `idimgurl_back` varchar(355) DEFAULT NULL COMMENT '身份证背面照片url',
  `status` varchar(3) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `modify_time` varchar(20) DEFAULT NULL COMMENT '最后一次修改时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `bank_account_verify` varchar(3) DEFAULT NULL COMMENT '银行卡账号核实，财务共享回调，通过初步审核后设为1,未通过设为0,首次填写银行卡还未审核设为2（审核中）',
  `binding_verify` varchar(3) DEFAULT '0' COMMENT '用户是否绑定核实',
  `req_fssc_bill` varchar(30) DEFAULT NULL COMMENT '财务共享的流水单号',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

DROP TABLE IF EXISTS `tpm_user_role`;
CREATE TABLE `tpm_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `person_code` varchar(15) NOT NULL COMMENT '员工工号',
  `roleType` varchar(90) NOT NULL COMMENT '1 admin;2 分公司财务负责人',
  `org_code` varchar(90) DEFAULT NULL COMMENT '当type为1时，值为空。当type为2时，表示财务负责的分公司代码',
  `create_time` varchar(90) NOT NULL COMMENT '设置时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_code` (`org_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色权限表';


DROP TABLE IF EXISTS `tpm_user_statement`;
CREATE TABLE `tpm_user_statement` (
  `uuid` varchar(32) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '结算单编号',
  `activity_uuid` varchar(32) NOT NULL COMMENT '活动id',
  `activity_time` varchar(10) DEFAULT NULL COMMENT '参加日期',
  `openid` varchar(32) NOT NULL COMMENT '临促openid',
  `request_state` int(2) DEFAULT NULL COMMENT '参加的状态：0未通过审核(现场负责人审核) 1 已生成结算单； 2 现场负责人已确认申请单； 4 财务审核通过；5 财务审核不通过；',
  `reason` varchar(255) DEFAULT NULL COMMENT '通过或未通过的原因',
  `pay_state` int(1) NOT NULL COMMENT '付款状态：-1未付款；1 申请付款中；0失败；2 付款成功；',
  `load_bill_uuid` varchar(32) DEFAULT NULL COMMENT '借款单id ，在未生成借款单前为空',
  `work_start_time` varchar(20) NOT NULL COMMENT '上班打卡时间',
  `work_start_imgurl` varchar(300) DEFAULT NULL COMMENT '上班打卡上传图片URL',
  `work_end_time` varchar(20) NOT NULL COMMENT '下班打卡时间',
  `work_end_imgurl` varchar(300) DEFAULT NULL COMMENT '下班打卡上传图片URL',
  `work_hours` int(11) DEFAULT NULL COMMENT '工作时长',
  `work_minute` int(11) DEFAULT NULL COMMENT '分钟',
  `valid_work_hours` int(11) DEFAULT NULL COMMENT '有效的结算工作时长',
  `valid_work_minute` int(11) DEFAULT NULL,
  `reduce_hours_reason` varchar(255) DEFAULT NULL COMMENT '修改工时原因',
  `adjust_hours_note` varchar(255) DEFAULT NULL COMMENT '审核工时备注',
  `check_time` varchar(20) DEFAULT NULL COMMENT '审核时间',
  `hours_state` int(2) DEFAULT NULL COMMENT '修改工时的状态:0未审核； 1通过； 2不通过',
  `reduce_money` double DEFAULT NULL COMMENT '扣减工时报酬',
  `reduce_money_reason` varchar(255) DEFAULT NULL COMMENT '扣减工时报酬事由',
  `incentive_amount` double DEFAULT NULL COMMENT '其它激励金额',
  `adjust_money_note` varchar(255) DEFAULT NULL COMMENT '审核工资备注',
  `incentive_reason` varchar(255) DEFAULT NULL COMMENT '其它激励事由',
  `real_salary` double DEFAULT NULL COMMENT '实际报酬',
  `payment_id` varchar(64) DEFAULT NULL COMMENT '共享付款单流水号',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `payment_id` (`payment_id`),
  KEY `index_activity_uuid` (`activity_uuid`),
  KEY `index_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临促结算单表';

DROP TABLE IF EXISTS `tpm_user_wx_info`;
CREATE TABLE `tpm_user_wx_info` (
  `openid` varchar(32) NOT NULL COMMENT '主键',
  `sex` char(2) DEFAULT NULL COMMENT '性别',
  `nikeName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `headImgUrl` varchar(255) DEFAULT NULL COMMENT '头像url',
  `city` varchar(32) DEFAULT NULL COMMENT '所在城市',
  `province` varchar(32) DEFAULT NULL COMMENT '所在省',
  `country` varchar(32) DEFAULT NULL COMMENT '所在国家',
  `subscribeTime` varchar(20) DEFAULT NULL COMMENT '关注时间',
  `unionid` varchar(32) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `groupid` varchar(32) DEFAULT NULL,
  `tagidList` varchar(255) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注用户微信信息表';

DROP TABLE IF EXISTS `tpm_weixin_employee_binding`;
CREATE TABLE `tpm_weixin_employee_binding` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `person_code` varchar(15) NOT NULL COMMENT '员工工号',
  `openid` varchar(90) NOT NULL COMMENT '微信openid',
  `create_time` varchar(90) NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='现场活动负责人和分公司财务微信绑定员工工号';

DROP TABLE IF EXISTS `tpm_weixin_employee_binding_log`;
CREATE TABLE `tpm_weixin_employee_binding_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `person_code` varchar(15) NOT NULL COMMENT '员工工号',
  `openid` varchar(90) NOT NULL COMMENT '微信openid',
  `operate_type` varchar(90) NOT NULL COMMENT '1 绑定；2 解绑',
  `create_time` varchar(90) NOT NULL COMMENT '绑定或解绑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='现场活动负责人和分公司财务微信绑定或解绑员工工号记录';

DROP TABLE IF EXISTS `tpm_work_attendence_detail`;
CREATE TABLE `tpm_work_attendence_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `openid` varchar(32) NOT NULL COMMENT '临促openid',
  `activity_uuid` varchar(32) DEFAULT NULL COMMENT '参加的活动',
  `clock_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `clock_address` varchar(32) DEFAULT NULL COMMENT '打卡地址',
  `clock_time` varchar(20) DEFAULT NULL COMMENT '打卡时间',
  `url` varchar(300) DEFAULT NULL COMMENT '上传图片url',
  PRIMARY KEY (`id`),
  KEY `index_openid` (`openid`),
  KEY `index_activity_uuid` (`activity_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='打卡明细表';

DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bankname` varchar(100) DEFAULT NULL COMMENT '银行名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=utf8 COMMENT='银行名称表';

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES ('3', '中国工商银行');
INSERT INTO `bank` VALUES ('4', '中国银行');
INSERT INTO `bank` VALUES ('5', '中国农业银行');
INSERT INTO `bank` VALUES ('6', '中国建设银行');
INSERT INTO `bank` VALUES ('7', '交通银行');
INSERT INTO `bank` VALUES ('8', '承德银行');
INSERT INTO `bank` VALUES ('9', '山西省晋城市城区信用社');
INSERT INTO `bank` VALUES ('10', '吉林九台农村商业银行');
INSERT INTO `bank` VALUES ('11', '广东发展银行');
INSERT INTO `bank` VALUES ('12', '哈尔滨市城郊信用联社营业部转朝阳信用社');
INSERT INTO `bank` VALUES ('13', '中信银行');
INSERT INTO `bank` VALUES ('14', '招商银行');
INSERT INTO `bank` VALUES ('15', '兴业银行');
INSERT INTO `bank` VALUES ('16', '广西北部湾银行');
INSERT INTO `bank` VALUES ('17', '内蒙古银行');
INSERT INTO `bank` VALUES ('18', '杭州银行');
INSERT INTO `bank` VALUES ('19', '中国农业发展银行');
INSERT INTO `bank` VALUES ('20', '平安银行');
INSERT INTO `bank` VALUES ('21', '焦作市商业银行');
INSERT INTO `bank` VALUES ('22', '华兴银行');
INSERT INTO `bank` VALUES ('23', '贵阳银行');
INSERT INTO `bank` VALUES ('24', '柳州银行');
INSERT INTO `bank` VALUES ('25', '中国邮政储蓄银行');
INSERT INTO `bank` VALUES ('26', '烟台银行');
INSERT INTO `bank` VALUES ('27', '山西省晋城市城区农村信用合作联社');
INSERT INTO `bank` VALUES ('28', '德州银行');
INSERT INTO `bank` VALUES ('29', '惠州农村商业银行');
INSERT INTO `bank` VALUES ('30', '山西省农村商业银行');
INSERT INTO `bank` VALUES ('31', '吉林公主岭农村合作银行');
INSERT INTO `bank` VALUES ('32', '攀枝花市商业银行');
INSERT INTO `bank` VALUES ('33', '郑州银行');
INSERT INTO `bank` VALUES ('34', '四川省农村信用社');
INSERT INTO `bank` VALUES ('35', '云南省农村信用社联合社');
INSERT INTO `bank` VALUES ('36', '介休市农村信用合作联社');
INSERT INTO `bank` VALUES ('37', '上海银行');
INSERT INTO `bank` VALUES ('38', '中山农村商业银行');
INSERT INTO `bank` VALUES ('39', '张家口市商业银行');
INSERT INTO `bank` VALUES ('40', '长春农村商业银行');
INSERT INTO `bank` VALUES ('41', '湖北银行');
INSERT INTO `bank` VALUES ('42', '大连银行股份有限公司');
INSERT INTO `bank` VALUES ('43', '新疆天山农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('44', '宁武农村信用合作联社');
INSERT INTO `bank` VALUES ('45', '九江银行');
INSERT INTO `bank` VALUES ('46', '商丘银行');
INSERT INTO `bank` VALUES ('47', '潍坊市农村商业银行');
INSERT INTO `bank` VALUES ('48', '齐鲁银行');
INSERT INTO `bank` VALUES ('49', '福建海峡银行');
INSERT INTO `bank` VALUES ('50', '甘肃银行');
INSERT INTO `bank` VALUES ('51', '富滇银行');
INSERT INTO `bank` VALUES ('52', '天津银行');
INSERT INTO `bank` VALUES ('53', '安阳银行');
INSERT INTO `bank` VALUES ('54', '山东圣泰农村合作银行');
INSERT INTO `bank` VALUES ('55', '上海浦东发展银行');
INSERT INTO `bank` VALUES ('56', '江苏银行');
INSERT INTO `bank` VALUES ('57', '重庆三峡银行');
INSERT INTO `bank` VALUES ('58', '桂林银行');
INSERT INTO `bank` VALUES ('59', '哈尔滨银行');
INSERT INTO `bank` VALUES ('60', '沧州银行');
INSERT INTO `bank` VALUES ('61', '宁夏银行');
INSERT INTO `bank` VALUES ('62', '成都农商银行');
INSERT INTO `bank` VALUES ('63', '潍坊银行');
INSERT INTO `bank` VALUES ('64', '重庆银行');
INSERT INTO `bank` VALUES ('65', '上饶银行');
INSERT INTO `bank` VALUES ('66', '重庆农村商业银行');
INSERT INTO `bank` VALUES ('67', '华夏银行');
INSERT INTO `bank` VALUES ('68', '深圳农村商业银行');
INSERT INTO `bank` VALUES ('69', '湖南农村商业银行');
INSERT INTO `bank` VALUES ('70', '汉口银行');
INSERT INTO `bank` VALUES ('71', '长治农商银行');
INSERT INTO `bank` VALUES ('72', '恩施农村商业银行');
INSERT INTO `bank` VALUES ('73', '中国民生银行');
INSERT INTO `bank` VALUES ('74', '东营银行股份有限公司');
INSERT INTO `bank` VALUES ('75', '天津农商银行');
INSERT INTO `bank` VALUES ('76', 'TCL集团股份有限公司财务公司');
INSERT INTO `bank` VALUES ('77', '乌海银行股份有限公司');
INSERT INTO `bank` VALUES ('78', '栖霞市农村信用合作社寨里信用社');
INSERT INTO `bank` VALUES ('79', '中国光大银行');
INSERT INTO `bank` VALUES ('80', '许昌银行');
INSERT INTO `bank` VALUES ('81', '浙江泰隆商业银行');
INSERT INTO `bank` VALUES ('82', '温州银行');
INSERT INTO `bank` VALUES ('83', '江苏灌云农商银行');
INSERT INTO `bank` VALUES ('84', '山东济南润丰农村合作银行');
INSERT INTO `bank` VALUES ('85', '北京农商银行');
INSERT INTO `bank` VALUES ('86', '深圳发展银行');
INSERT INTO `bank` VALUES ('87', '上海农商银行');
INSERT INTO `bank` VALUES ('88', '晋商银行');
INSERT INTO `bank` VALUES ('89', '青岛城阳农商银行');
INSERT INTO `bank` VALUES ('90', '晋城银行');
INSERT INTO `bank` VALUES ('91', '鄂尔多斯农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('92', '广州银行');
INSERT INTO `bank` VALUES ('93', '晋中银行');
INSERT INTO `bank` VALUES ('94', '吉林双阳农村商业银行');
INSERT INTO `bank` VALUES ('95', '太原市城区农村信用合作联社');
INSERT INTO `bank` VALUES ('96', '河北银行');
INSERT INTO `bank` VALUES ('97', '调兵山市农村信用合作社');
INSERT INTO `bank` VALUES ('98', '鄯善县农村信用合作联社');
INSERT INTO `bank` VALUES ('99', '铁岭银行');
INSERT INTO `bank` VALUES ('100', '兰州银行');
INSERT INTO `bank` VALUES ('101', '龙江银行');
INSERT INTO `bank` VALUES ('102', '廊坊银行');
INSERT INTO `bank` VALUES ('103', '贵州银行');
INSERT INTO `bank` VALUES ('104', '西安银行');
INSERT INTO `bank` VALUES ('105', '秦皇岛银行');
INSERT INTO `bank` VALUES ('106', '昆明市商业银行');
INSERT INTO `bank` VALUES ('107', '凤阳县农村信用合作联社');
INSERT INTO `bank` VALUES ('108', '城市商业银行');
INSERT INTO `bank` VALUES ('109', '大同银行');
INSERT INTO `bank` VALUES ('110', '农村合作银行');
INSERT INTO `bank` VALUES ('111', '徽商银行');
INSERT INTO `bank` VALUES ('112', '东莞农村商业银行东联支行营业部');
INSERT INTO `bank` VALUES ('113', '浦发银行');
INSERT INTO `bank` VALUES ('114', '金华银行');
INSERT INTO `bank` VALUES ('115', '临商银行');
INSERT INTO `bank` VALUES ('116', '东莞银行');
INSERT INTO `bank` VALUES ('117', '吉林银行');
INSERT INTO `bank` VALUES ('118', '南昌银行');
INSERT INTO `bank` VALUES ('119', '包商银行');
INSERT INTO `bank` VALUES ('120', '花旗银行');
INSERT INTO `bank` VALUES ('121', '华融湘江银行');
INSERT INTO `bank` VALUES ('122', '南充市商业银行');
INSERT INTO `bank` VALUES ('123', '保定银行');
INSERT INTO `bank` VALUES ('124', '杭州联合农村商业银行');
INSERT INTO `bank` VALUES ('125', '德丰村镇银行');
INSERT INTO `bank` VALUES ('126', '内蒙古农村信用社');
INSERT INTO `bank` VALUES ('127', '新乡银行');
INSERT INTO `bank` VALUES ('128', '六盘水市钟山区农村信用合作联社');
INSERT INTO `bank` VALUES ('129', '长安银行');
INSERT INTO `bank` VALUES ('130', '长沙银行');
INSERT INTO `bank` VALUES ('131', '邯郸银行');
INSERT INTO `bank` VALUES ('132', '农安县农村信用合作联社');
INSERT INTO `bank` VALUES ('133', '大连农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('134', '浙江民泰商业银行');
INSERT INTO `bank` VALUES ('135', '东营莱商村镇银行');
INSERT INTO `bank` VALUES ('136', '北京银行');
INSERT INTO `bank` VALUES ('137', '锦州银行');
INSERT INTO `bank` VALUES ('138', '曲靖市商业银行');
INSERT INTO `bank` VALUES ('139', '汇银通');
INSERT INTO `bank` VALUES ('140', '其它银行');
INSERT INTO `bank` VALUES ('141', 'TCL集团财务有限公司');
INSERT INTO `bank` VALUES ('142', '宁波银行');
INSERT INTO `bank` VALUES ('143', '南粤银行');
INSERT INTO `bank` VALUES ('144', '浙商银行');
INSERT INTO `bank` VALUES ('145', '渤海银行');
INSERT INTO `bank` VALUES ('146', '邮储银行');
INSERT INTO `bank` VALUES ('147', '华润银行');
INSERT INTO `bank` VALUES ('148', 'commerz Nürnberg');
INSERT INTO `bank` VALUES ('149', '湖北农村商业银行');
INSERT INTO `bank` VALUES ('150', '兰州市七里河区农村信用合作联社');
INSERT INTO `bank` VALUES ('151', '中国农村合作银行');
INSERT INTO `bank` VALUES ('152', '邢台银行');
INSERT INTO `bank` VALUES ('153', '菏泽市牡丹区农村信用合作联社');
INSERT INTO `bank` VALUES ('154', '齐商银行');
INSERT INTO `bank` VALUES ('155', '任城区农村信用社合作联社');
INSERT INTO `bank` VALUES ('156', '营口银行股份有限公司');
INSERT INTO `bank` VALUES ('157', '金谷农村合作银行');
INSERT INTO `bank` VALUES ('158', '沧州融信农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('159', '台州银行');
INSERT INTO `bank` VALUES ('160', '宜宾市商业银行');
INSERT INTO `bank` VALUES ('161', '中原银行');
INSERT INTO `bank` VALUES ('162', '唐山市商业银行');
INSERT INTO `bank` VALUES ('163', '佳木斯市郊区农村信用合作社联合社');
INSERT INTO `bank` VALUES ('164', '昆山农村商业银行');
INSERT INTO `bank` VALUES ('165', '福州农村商业银行');
INSERT INTO `bank` VALUES ('166', ' of China (Hong Kong) Ltd.');
INSERT INTO `bank` VALUES ('167', 'Silicon Valley ');
INSERT INTO `bank` VALUES ('168', '集友银行');
INSERT INTO `bank` VALUES ('169', '渣打银行（中国）有限公司');
INSERT INTO `bank` VALUES ('170', '绵阳市涪城信用联社');
INSERT INTO `bank` VALUES ('171', '汇丰银行（中国）有限公司');
INSERT INTO `bank` VALUES ('172', '江苏扬州农村商业银行');
INSERT INTO `bank` VALUES ('173', '中国建设银行股份有限公司');
INSERT INTO `bank` VALUES ('174', '宜兴农村商业银行');
INSERT INTO `bank` VALUES ('175', '莱商银行');
INSERT INTO `bank` VALUES ('176', '汇丰银行');
INSERT INTO `bank` VALUES ('177', '融信银行');
INSERT INTO `bank` VALUES ('178', '齐齐哈尔市市区农村信用合作联社');
INSERT INTO `bank` VALUES ('179', '广西农村信用社');
INSERT INTO `bank` VALUES ('180', '十堰农村商业银行');
INSERT INTO `bank` VALUES ('181', '瑞穗银行');
INSERT INTO `bank` VALUES ('182', '苏州银行');
INSERT INTO `bank` VALUES ('183', '迁安支行');
INSERT INTO `bank` VALUES ('184', '三菱东京日联银行');
INSERT INTO `bank` VALUES ('185', '荆州市农村信用联社');
INSERT INTO `bank` VALUES ('186', '杭州联合银行');
INSERT INTO `bank` VALUES ('187', '住友银行');
INSERT INTO `bank` VALUES ('188', '长春发展农村商业银行');
INSERT INTO `bank` VALUES ('189', '自贡市大安区农村信息合作联社');
INSERT INTO `bank` VALUES ('190', '亳州市谯城区农村信用合作联社');
INSERT INTO `bank` VALUES ('191', '丹东银行');
INSERT INTO `bank` VALUES ('192', '农村信用合作联社');
INSERT INTO `bank` VALUES ('193', '广州农商银行');
INSERT INTO `bank` VALUES ('194', '甘肃省农村信用社');
INSERT INTO `bank` VALUES ('195', '宜宾农村信用社');
INSERT INTO `bank` VALUES ('196', '中国工商银行威海分行营业部');
INSERT INTO `bank` VALUES ('197', '美国银行有限公司');
INSERT INTO `bank` VALUES ('198', '东莞市农村信用合作社');
INSERT INTO `bank` VALUES ('199', '东莞农村商业银行');
INSERT INTO `bank` VALUES ('200', '农业银行');
INSERT INTO `bank` VALUES ('201', '广东揭西农村商业银行河镇支行');
INSERT INTO `bank` VALUES ('202', '中国农村商业银行');
INSERT INTO `bank` VALUES ('203', '合肥科技农村商业银行');
INSERT INTO `bank` VALUES ('204', '河北唐山农村商业银行');
INSERT INTO `bank` VALUES ('205', '中国农商银行');
INSERT INTO `bank` VALUES ('206', '福建省农村信用社');
INSERT INTO `bank` VALUES ('207', '泉州银行');
INSERT INTO `bank` VALUES ('208', '美国银行');
INSERT INTO `bank` VALUES ('209', '宜宾农村商业银行');
INSERT INTO `bank` VALUES ('210', '宜兴农村合作银行');
INSERT INTO `bank` VALUES ('211', '大新银行');
INSERT INTO `bank` VALUES ('212', '南京银行');
INSERT INTO `bank` VALUES ('213', '呼和浩特金谷农村商业合作银行');
INSERT INTO `bank` VALUES ('214', '珠海华润银行');
INSERT INTO `bank` VALUES ('215', '唐山银行');
INSERT INTO `bank` VALUES ('216', '盛京银行');
INSERT INTO `bank` VALUES ('217', '成都银行');
INSERT INTO `bank` VALUES ('218', '福建晋江农村商业银行');
INSERT INTO `bank` VALUES ('219', '贵州省农村信用社');
INSERT INTO `bank` VALUES ('220', '厦门国际银行');
INSERT INTO `bank` VALUES ('221', '恒丰银行');
INSERT INTO `bank` VALUES ('222', '中国平安银行');
INSERT INTO `bank` VALUES ('223', '中国农行银行(禁用)');
INSERT INTO `bank` VALUES ('224', '中国兴业银行');
INSERT INTO `bank` VALUES ('225', '中国招商银行');
INSERT INTO `bank` VALUES ('226', '民生银行');
INSERT INTO `bank` VALUES ('227', '农村商业银行');
INSERT INTO `bank` VALUES ('228', '合浦县农村信用合作联社');
INSERT INTO `bank` VALUES ('229', '建设银行');
INSERT INTO `bank` VALUES ('230', '工商银行');
INSERT INTO `bank` VALUES ('231', '邮政储蓄银行');
INSERT INTO `bank` VALUES ('233', '中国交通银行');
INSERT INTO `bank` VALUES ('234', '国外银行');
INSERT INTO `bank` VALUES ('235', '韩亚银行');
INSERT INTO `bank` VALUES ('236', '昆仑银行');
INSERT INTO `bank` VALUES ('237', 'WOORI');
INSERT INTO `bank` VALUES ('238', '德意志银行（中国）有限公司');
INSERT INTO `bank` VALUES ('239', '泉州农商银行');
INSERT INTO `bank` VALUES ('240', '农村信用合作社');
INSERT INTO `bank` VALUES ('241', '杭州农村商业银行');
INSERT INTO `bank` VALUES ('242', '日照银行');
INSERT INTO `bank` VALUES ('243', '营口银行');
INSERT INTO `bank` VALUES ('244', '瑞安农村商业银行');
INSERT INTO `bank` VALUES ('245', '阜宁农村商业银行');
INSERT INTO `bank` VALUES ('246', '湖南星沙农村商业银行');
INSERT INTO `bank` VALUES ('247', '浙江嘉善农村商业银行');
INSERT INTO `bank` VALUES ('248', '广州农村商业银行');
INSERT INTO `bank` VALUES ('249', '江苏江南农村商业银行');
INSERT INTO `bank` VALUES ('250', 'Sumitomo mitsui ing');
INSERT INTO `bank` VALUES ('251', '三井住友银行');
INSERT INTO `bank` VALUES ('252', '郑州市市郊农村信用合作联社');
INSERT INTO `bank` VALUES ('253', '三清山村镇银行');
INSERT INTO `bank` VALUES ('254', '浙江瑞安农村商业银行');
INSERT INTO `bank` VALUES ('255', '南洋商业银行');
INSERT INTO `bank` VALUES ('256', '天津银行融源支行');
INSERT INTO `bank` VALUES ('257', '河南省农村信用社（农商银行）');
INSERT INTO `bank` VALUES ('258', 'TCL集团财务有限公司结算中心');
INSERT INTO `bank` VALUES ('259', '江苏省农村信用社联合社');
INSERT INTO `bank` VALUES ('260', '农村信用联合社');
INSERT INTO `bank` VALUES ('261', '福建省建瓯市农村信用社合作联社');
INSERT INTO `bank` VALUES ('262', '河北张家口宣泰农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('263', '福清汇通农商银行');
INSERT INTO `bank` VALUES ('264', 'Citi N.A.');
INSERT INTO `bank` VALUES ('265', '富邦华一银行');
INSERT INTO `bank` VALUES ('266', '东亚银行');
INSERT INTO `bank` VALUES ('267', '呼和浩特金谷农商银行');
INSERT INTO `bank` VALUES ('268', '缙云县农村信用合作联');
INSERT INTO `bank` VALUES ('269', '亳州药都农村合作银行');
INSERT INTO `bank` VALUES ('270', '绍兴市商业银行营业部');
INSERT INTO `bank` VALUES ('271', '江苏宜兴农村商业银行');
INSERT INTO `bank` VALUES ('272', '江苏盐城农村商业银行');
INSERT INTO `bank` VALUES ('273', '新乡市商业银行');
INSERT INTO `bank` VALUES ('274', '青岛银行');
INSERT INTO `bank` VALUES ('275', '滦平农村商业银行');
INSERT INTO `bank` VALUES ('276', 'CREDIT MUTUEL');
INSERT INTO `bank` VALUES ('277', 'AB SEB as');
INSERT INTO `bank` VALUES ('278', 'BANCO POPULAR ESPANOL S/A');
INSERT INTO `bank` VALUES ('279', 'CREDIT AGRICOLE DU FINISTERE');
INSERT INTO `bank` VALUES ('280', 'UBI Banco di Brescia');
INSERT INTO `bank` VALUES ('281', 'CREDIT DU NORD');
INSERT INTO `bank` VALUES ('282', 'NOVA LJUBLJANSKA A d/d/, Ljubljana');
INSERT INTO `bank` VALUES ('283', 'CREDIT AGRICOLE');
INSERT INTO `bank` VALUES ('284', 'LCL');
INSERT INTO `bank` VALUES ('285', 'BRD');
INSERT INTO `bank` VALUES ('286', 'SOCIETE GENERALE');
INSERT INTO `bank` VALUES ('287', 'CREDIT COOPERATIF');
INSERT INTO `bank` VALUES ('288', 'CREDITCOOP');
INSERT INTO `bank` VALUES ('289', 'Nordea');
INSERT INTO `bank` VALUES ('290', 'Credit Agricole');
INSERT INTO `bank` VALUES ('291', 'KBC');
INSERT INTO `bank` VALUES ('292', 'BNP PARIBAS');
INSERT INTO `bank` VALUES ('293', 'SOCIETE MARSEILLAISE DE CREDIT');
INSERT INTO `bank` VALUES ('294', ' POPULAIRE OCCITANE');
INSERT INTO `bank` VALUES ('295', 'Sparkasse Paderborn - Detmold');
INSERT INTO `bank` VALUES ('296', 'Slovenska sporitelna, a/s/ Zvolen');
INSERT INTO `bank` VALUES ('297', 'CAIXA GERAL DE DEPOSITOS');
INSERT INTO `bank` VALUES ('298', 'CAISSE DEPARGNE');
INSERT INTO `bank` VALUES ('299', 'Metro Filinvest Branch');
INSERT INTO `bank` VALUES ('300', '恩施市农村信用合作联社');
INSERT INTO `bank` VALUES ('301', '海口联合农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('302', '江南银行');
INSERT INTO `bank` VALUES ('303', '洛阳银行');
INSERT INTO `bank` VALUES ('304', 'RAIBA');
INSERT INTO `bank` VALUES ('305', 'STADTSPARKASSE DUSSELDORF');
INSERT INTO `bank` VALUES ('306', 'CRCA AUXERRE LEPERE');
INSERT INTO `bank` VALUES ('307', 'DANSKE ');
INSERT INTO `bank` VALUES ('308', 'BANQUE POPULAIRE');
INSERT INTO `bank` VALUES ('309', 'BPCA');
INSERT INTO `bank` VALUES ('310', 'ALIOR  SA');
INSERT INTO `bank` VALUES ('311', 'NORDEA');
INSERT INTO `bank` VALUES ('312', 'JSCB «VOZROZHDENIYE» Branch, KALININGRAD');
INSERT INTO `bank` VALUES ('313', 'BANQUE POPULAIRE OCCITANE');
INSERT INTO `bank` VALUES ('314', 'ARKEA');
INSERT INTO `bank` VALUES ('315', 'CIC');
INSERT INTO `bank` VALUES ('316', 'BNP PARIBAS ELYSEE HAUSSMANN');
INSERT INTO `bank` VALUES ('317', 'CA PYRENEES-GASCOGNE');
INSERT INTO `bank` VALUES ('318', '江西银行');
INSERT INTO `bank` VALUES ('319', '惠州市惠城区农村信用合作联社');
INSERT INTO `bank` VALUES ('320', '海南银行');
INSERT INTO `bank` VALUES ('321', 'HSBC Hong Kong');
INSERT INTO `bank` VALUES ('322', '江南农村商业银行');
INSERT INTO `bank` VALUES ('323', '鞍山市商业银行');
INSERT INTO `bank` VALUES ('324', '广发银行');
INSERT INTO `bank` VALUES ('325', '龙江县农村信用合作社联合社');
INSERT INTO `bank` VALUES ('326', '长治潞州农村商业银行');
INSERT INTO `bank` VALUES ('327', '山西尧都农村商业银行');
INSERT INTO `bank` VALUES ('328', '浙江民泰商业银行股份有限公司');
INSERT INTO `bank` VALUES ('329', '绵阳市商业银行');
INSERT INTO `bank` VALUES ('330', 'Commerz AG Frankfurt/Main');
INSERT INTO `bank` VALUES ('331', '南充市农村商业银行');
INSERT INTO `bank` VALUES ('332', '新疆维吾尔自治区农村信用社');
INSERT INTO `bank` VALUES ('333', '中國銀行(香港)有限公司');
INSERT INTO `bank` VALUES ('334', 'NANYANG COMMERCIAL , LTD.');
INSERT INTO `bank` VALUES ('335', 'The Hongkong & Shanghai ing  Corporation Ltd');
INSERT INTO `bank` VALUES ('336', ' PEKAO S.A.');
INSERT INTO `bank` VALUES ('337', 'HUA NAN COMMERCIAL  LTD.');
INSERT INTO `bank` VALUES ('338', '德阳银行');
INSERT INTO `bank` VALUES ('339', '江苏沭阳农村商业银行');
INSERT INTO `bank` VALUES ('340', '嘉兴银行');
INSERT INTO `bank` VALUES ('341', '平湖农商银行');
INSERT INTO `bank` VALUES ('342', '陕西秦农农村商业银行');
INSERT INTO `bank` VALUES ('343', '石嘴山银行');
INSERT INTO `bank` VALUES ('344', '烟台农商银行');
INSERT INTO `bank` VALUES ('345', 'BNP PARIBAS FORTIS');
INSERT INTO `bank` VALUES ('346', '北京顺义银座村镇银行');
INSERT INTO `bank` VALUES ('347', '江苏紫金农村商业银行');
INSERT INTO `bank` VALUES ('348', '大同市商业银行');
INSERT INTO `bank` VALUES ('349', '临沂市兰山农村合作银行');
INSERT INTO `bank` VALUES ('350', '本溪市商业银行');
INSERT INTO `bank` VALUES ('351', '渣打銀行(香港)有限公司');
INSERT INTO `bank` VALUES ('352', '上海匯豐銀行（香港）有限公司');
INSERT INTO `bank` VALUES ('353', '佛山市南海农商银行');
INSERT INTO `bank` VALUES ('354', '五常市农村信用合作联社民意信用社');
INSERT INTO `bank` VALUES ('355', '浙江温州瓯海农村商业银行');
INSERT INTO `bank` VALUES ('356', '信阳银行');
INSERT INTO `bank` VALUES ('357', 'Woori  ');
INSERT INTO `bank` VALUES ('358', 'INTER SA');
INSERT INTO `bank` VALUES ('359', '秦皇岛市商业银行');
INSERT INTO `bank` VALUES ('360', '海口联合农村商业银行');
INSERT INTO `bank` VALUES ('361', '哈尔滨城郊农村信用联社');
INSERT INTO `bank` VALUES ('362', '福建龙岩农村商业银行');
INSERT INTO `bank` VALUES ('363', '葫芦岛银行');
INSERT INTO `bank` VALUES ('364', '天水秦州农村合作银行');
INSERT INTO `bank` VALUES ('365', '浙江舟山定海农村合作银行');
INSERT INTO `bank` VALUES ('366', '阜新市商业银行');
INSERT INTO `bank` VALUES ('367', '福建南平农村商业银行');
INSERT INTO `bank` VALUES ('368', '肇庆端州农村商业银行');
INSERT INTO `bank` VALUES ('369', '保定市商业银行');
INSERT INTO `bank` VALUES ('370', '衡水市商业银行');
INSERT INTO `bank` VALUES ('371', 'Wells Fargo , N.A');
INSERT INTO `bank` VALUES ('372', '乌鲁木齐银行');
INSERT INTO `bank` VALUES ('373', 'The Hong Kong and Shanghai ing Corporation');
INSERT INTO `bank` VALUES ('374', '星展银行（香港）有限公司');
INSERT INTO `bank` VALUES ('375', ' OF CHINA PARIS BRANCH');
INSERT INTO `bank` VALUES ('376', '深圳农商银行');
INSERT INTO `bank` VALUES ('377', 'CitiN.A.');
INSERT INTO `bank` VALUES ('378', '珠海农商银行');
INSERT INTO `bank` VALUES ('379', '澳门大丰银行');
INSERT INTO `bank` VALUES ('380', 'SUMITOMO MITSUI ING CORPORATION');
INSERT INTO `bank` VALUES ('381', '吉林农安农村商业银行');
INSERT INTO `bank` VALUES ('382', '贵阳农村商业银行');
INSERT INTO `bank` VALUES ('383', '山东省临清市农村信用合作联社');
INSERT INTO `bank` VALUES ('384', ' of America');
INSERT INTO `bank` VALUES ('385', '中银香港');
INSERT INTO `bank` VALUES ('386', '福建福清汇通农村商业银行');
INSERT INTO `bank` VALUES ('387', '江苏靖江农村商业银行');
INSERT INTO `bank` VALUES ('388', '江苏淮安农村商业银行');
INSERT INTO `bank` VALUES ('389', '恒生银行');
INSERT INTO `bank` VALUES ('390', 'HSBC  PLC');
INSERT INTO `bank` VALUES ('391', 'HSBC');
INSERT INTO `bank` VALUES ('392', 'UniCredit  AG');
INSERT INTO `bank` VALUES ('393', '南洋商業银行');
INSERT INTO `bank` VALUES ('394', '延边农村商业银行');
INSERT INTO `bank` VALUES ('395', '金华成泰农村合作银行');
INSERT INTO `bank` VALUES ('396', '乌鲁木齐市商业银行');
INSERT INTO `bank` VALUES ('397', '菏泽农村商业银行');
INSERT INTO `bank` VALUES ('398', '泰安银行');
INSERT INTO `bank` VALUES ('399', '乐山市商业银行');
INSERT INTO `bank` VALUES ('400', 'Northern Trust ');
INSERT INTO `bank` VALUES ('401', '友利银行(中国)有限公司');
INSERT INTO `bank` VALUES ('402', '陕西省农村信用社联合社');
INSERT INTO `bank` VALUES ('403', '长城华西银行');
INSERT INTO `bank` VALUES ('404', '无锡农村商业银行');
INSERT INTO `bank` VALUES ('405', '长治银行');
INSERT INTO `bank` VALUES ('406', '湖南省农村信用社联合社宁乡农商银行');
INSERT INTO `bank` VALUES ('407', '陕西户县农村商业银行有限责任公司');
INSERT INTO `bank` VALUES ('408', 'xxxx');
INSERT INTO `bank` VALUES ('409', '余杭农村商业银行');
INSERT INTO `bank` VALUES ('410', 'Barclays Corporate ');
INSERT INTO `bank` VALUES ('411', 'China Citic  International Ltd.');
INSERT INTO `bank` VALUES ('412', 'O@9');
INSERT INTO `bank` VALUES ('413', '辽阳银行');
INSERT INTO `bank` VALUES ('414', '摩根大通银行');
INSERT INTO `bank` VALUES ('415', '农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('416', '深圳宝安桂银村镇银行');
INSERT INTO `bank` VALUES ('417', '中国进出口银行');
INSERT INTO `bank` VALUES ('418', '国家开发银行');
INSERT INTO `bank` VALUES ('419', '丹东市商业银行');
INSERT INTO `bank` VALUES ('420', '乐山三江农村商业银行股份有限公司');
INSERT INTO `bank` VALUES ('421', '济宁银行');
INSERT INTO `bank` VALUES ('422', '四川凉山州农村商业银行');
INSERT INTO `bank` VALUES ('423', '四川天府银行');
INSERT INTO `bank` VALUES ('424', '自贡市商业银行');
INSERT INTO `bank` VALUES ('425', '宜宾翠屏农村商业银行');
INSERT INTO `bank` VALUES ('426', '北京农村商业银行');
INSERT INTO `bank` VALUES ('427', '鹤壁市商业银行');
INSERT INTO `bank` VALUES ('428', '吴江农村商业银行');
INSERT INTO `bank` VALUES ('429', '吉安市农村信用社');
INSERT INTO `bank` VALUES ('430', 'JPMORGAN CHASE ');
INSERT INTO `bank` VALUES ('431', '河南省农村信用社');
INSERT INTO `bank` VALUES ('432', 'BANCO NACIONAL DE COSTA RICA');
INSERT INTO `bank` VALUES ('433', 'UMB  n.a');
INSERT INTO `bank` VALUES ('434', ' SINOPAC');
INSERT INTO `bank` VALUES ('435', '顺德农村商业银行');
INSERT INTO `bank` VALUES ('436', '浙江嵊州农村合作银行');
INSERT INTO `bank` VALUES ('437', '上海农村商业银行');
INSERT INTO `bank` VALUES ('438', '泰安市商业银行');
INSERT INTO `bank` VALUES ('439', 'Raiffeisen Straubing eG');
INSERT INTO `bank` VALUES ('440', 'Natwest');
INSERT INTO `bank` VALUES ('441', 'Hang Seng  Limited');
INSERT INTO `bank` VALUES ('442', 'HSBC  POLSKA SA');
INSERT INTO `bank` VALUES ('443', 'TD ');
INSERT INTO `bank` VALUES ('444', '厦门银行');
INSERT INTO `bank` VALUES ('445', '新疆乌鲁木齐市商业银行');
INSERT INTO `bank` VALUES ('446', 'Banco De Oro - PAScor Drive Branch');
INSERT INTO `bank` VALUES ('447', 'WELLS FARGO ');
INSERT INTO `bank` VALUES ('448', 'BNPPARB RUEIL BUZENVAL');
INSERT INTO `bank` VALUES ('449', 'Standard Chartered  (Vietnam) Limited');
INSERT INTO `bank` VALUES ('450', '江苏民丰农村商业银行');
INSERT INTO `bank` VALUES ('451', 'Standard Chartered  (Hong Kong) Limited');
INSERT INTO `bank` VALUES ('452', '北京延庆村镇银行');
INSERT INTO `bank` VALUES ('453', 'Hongkong and Shanghai ing Corporation');
INSERT INTO `bank` VALUES ('454', '广东翁源农村商业银行');
INSERT INTO `bank` VALUES ('455', 'Sparkasse Pforzheim Calw');
INSERT INTO `bank` VALUES ('456', ' Al Habib Limited');
