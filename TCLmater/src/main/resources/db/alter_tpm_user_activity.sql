 alter table tpm_user_activity add request_activity_day VARCHAR(30) COMMENT '申请参加哪天活动';
 alter table tpm_user_activity add reject_flag INT(2) COMMENT '是否剔除的标志，1为剔除，0或其他为正常参加活动';