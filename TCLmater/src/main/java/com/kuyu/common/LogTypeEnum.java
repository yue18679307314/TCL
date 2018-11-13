package com.kuyu.common;

/**
 * @Author tang_zhen
 * @Date 2018/1/16
 * @Description
 */
/**
 * 日志操作类型的枚举类
 * 日志操作类型：1 修改银行信息；2 pc端考勤审核；3 移动端考勤申请；4 pc端结算审核；
 * 5 移动端结算审核；6 修改立项单可使用人;7修改活动负责人;8修改个人信息；9修改财务负责人；
 * 10导出考勤表，11下载考勤表,12下载临促银行信息
 */


public enum LogTypeEnum {
    accountInfo("修改银行信息",1),
    attendance("pc端考勤审核",2),
    wapAttendance("移动端考勤申请",3),
    userStatement("pc端结算审核",4),
    wapUserStatement("移动端结算审核",5),
    changeRequestUser("修改立项单可使用人",6),
    changeManager("修改活动负责人",7),
    changeBaseInfo("修改个人信息",8),
    changeFinance("修改财务负责人",9),
    importAttendance("导出考勤表",10),
    downloadAttendence("下载考勤表",11),
    downloadBaseInfo("下载临促银行信息",12);

    private String typeName;
    private int index;
     LogTypeEnum(String name, int index) {
        this.typeName = name;
        this.index = index;
    }
    public static String getTypeName(int index) {
        for (LogTypeEnum c : LogTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.typeName;
            }
        }
        return null;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
