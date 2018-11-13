CREATE PROCEDURE `sp_showChildLst`(IN rootId varchar(40))
BEGIN
DELETE FROM tmpLst;

CALL sp_createChildLst(rootId,0);

select tpm_dept.org_code from tmpLst,tpm_dept where tmpLst.code_value=tpm_dept.org_code order by tmpLst.sno;
END