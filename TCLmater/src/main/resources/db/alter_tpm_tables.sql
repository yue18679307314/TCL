
ALTER TABLE tpm_dept ADD update_time VARCHAR(20) COMMENT '更新时间';


ALTER TABLE tpm_employee ADD update_time VARCHAR(20) COMMENT '更新时间';

CREATE TABLE `tpm_attendence` (
  `uuid` varchar(32) NOT NULL COMMENT '主键',
  `person_code` varchar(24) NOT NULL COMMENT '生成人员编号',
  `person_name` varchar(24) NOT NULL COMMENT '生成人员姓名',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `attendence_uuid` varchar(32) NOT NULL COMMENT '考勤表单号',
  `tus_uuid` varchar(32) NOT NULL COMMENT '结算单单号',
  `url` varchar(255) DEFAULT NULL COMMENT '生成的文件路径',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `tus_uuid` (`tus_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤导出表';


ALTER TABLE tpm_other_fee_original MODIFY COLUMN amount DOUBLE;


alter table tpm_logs modify column type int comment '日志操作类型：1 修改银行信息；2 pc端考勤审核；3 移动端考勤申请；4 pc端结算审核；5 移动端结算审核；6 修改立项单可使用人;7修改活动负责人;8修改个人信息；9修改财务负责人；10导出考勤表，11下载考勤表,12下载临促银行信息';   

ALTER TABLE tpm_user_statement ADD financial_check_time VARCHAR(20) COMMENT '财务审核时间';

ALTER TABLE tpm_other_fee_original ADD activity_vendor VARCHAR(32) COMMENT '供应商';

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




