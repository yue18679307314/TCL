package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/29
 */
@ApiModel("PcmsMaterialImgModel(待验收物料图片模型)")
@TableName("pcms_material_img")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsMaterialImgModel extends Model<PcmsMaterialImgModel> {

    private int id;
    private int pending_material_id;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPending_material_id() {
        return pending_material_id;
    }

    public void setPending_material_id(int pending_material_id) {
        this.pending_material_id = pending_material_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
