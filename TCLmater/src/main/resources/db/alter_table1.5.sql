#结算单表添加考勤是否生成字段
ALTER TABLE tpm_user_statement ADD COLUMN is_attendence VARCHAR (2) COMMENT '是否生成考勤表，1已生成null未生成';

#将结算单表中已经生成考勤表的is_attendence字段置位1
UPDATE tpm_user_statement SET is_attendence = '1' WHERE uuid IN (SELECT DISTINCT tus_uuid FROM tpm_attendence);