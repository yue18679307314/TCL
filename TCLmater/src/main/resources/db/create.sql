DROP TABLE IF EXISTS `tmpLst`;
CREATE TABLE `tmpLst` (
  `sno` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code_value` varchar(40) DEFAULT NULL COMMENT 'code值',
  `depth` int COMMENT '层级深度',
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '部门数据零时表';
