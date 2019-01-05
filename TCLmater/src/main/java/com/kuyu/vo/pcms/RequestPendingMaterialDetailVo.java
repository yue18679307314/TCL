package com.kuyu.vo.pcms;

import java.util.List;

/**
 * Created by pc on 2019/1/5
 */
public class RequestPendingMaterialDetailVo {

    private List<PendingMaterialDetailVo> list;
    private List<String> stringList;

    public List<PendingMaterialDetailVo> getList() {
        return list;
    }

    public void setList(List<PendingMaterialDetailVo> list) {
        this.list = list;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
