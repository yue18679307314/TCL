CREATE PROCEDURE `sp_createChildLst`(IN rootId varchar(20),IN nDepth INT)
BEGIN
DECLARE done INT DEFAULT 0;
DECLARE b VARCHAR(40);
DECLARE cur1 CURSOR FOR SELECT org_code FROM tpm_dept where org_code_parent=rootId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

insert into tmpLst values (null,rootId,nDepth);
SET @@max_sp_recursion_depth = 10;
OPEN cur1;

FETCH cur1 INTO b;
WHILE done=0 DO
CALL sp_createChildLst(b,nDepth+1);
FETCH cur1 INTO b;
END WHILE;

CLOSE cur1;
END