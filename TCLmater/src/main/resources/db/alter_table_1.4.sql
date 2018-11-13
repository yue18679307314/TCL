ALTER TABLE tpm_user_statement ADD COLUMN statement_check_time VARCHAR (20) COMMENT '活动负责人结算审核时间';

CREATE TABLE tpm_branch_admin (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `person_code` varchar(15) NOT NULL COMMENT '员工工号',
  `roleType` varchar(90) NOT NULL COMMENT '0分公司管理员',
  `org_code` varchar(90) DEFAULT NULL COMMENT '表示分公司管理员负责的分公司代码',
  `create_time` varchar(90) NOT NULL COMMENT '设置时间',
  `flag` VARCHAR(2) COMMENT '设置分公司管理员时记录的最上级部门标记：1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_code` (`org_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=606839 DEFAULT CHARSET=utf8 COMMENT='分公司管理员表';

alter table tpm_logs modify column type int comment '日志操作类型：1 修改银行信息；2 pc端考勤审核；3 移动端考勤申请；4 pc端结算审核；5 移动端结算审核；6 修改立项单可使用人;7修改活动负责人;8修改个人信息；9修改财务负责人；10导出考勤表，11下载考勤表,12下载临促银行信息,13设置分公司管理员';   

alter table tpm_user_statement modify column pay_state int(1) NOT NULL COMMENT '付款状态：-1未付款；1 申请付款中；0失败；2 付款成功；3 作废，不再支付';

-- 新建省市的表
CREATE TABLE `province_and_city` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `province_id` varchar(10) NOT NULL COMMENT '省id',
  `province_name` varchar(50) NOT NULL COMMENT '省名称',
  `city_id` varchar(10) NOT NULL COMMENT '地级市id',
  `city_name` varchar(50) NOT NULL COMMENT '地级市名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=363 DEFAULT CHARSET=utf8 COMMENT='省与地级市的表';

-- 为活动表添加停止活动字段
alter table tpm_activity ADD column stop_flag VARCHAR(10) comment'活动是否被停止'
-- 为日志表添加操作人部门
alter table tpm_logs ADD column opt_user_dept VARCHAR(20) comment'操作人的部门'