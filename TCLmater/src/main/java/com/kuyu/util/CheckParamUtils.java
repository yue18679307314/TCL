package com.kuyu.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.BasePageQuery;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.vo.ResultVo;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description 校验参数工具类
 */
public class CheckParamUtils {

    public static boolean checkPageParm(BasePageQuery query){
        if (null == query) {
            return false;
        }
        if (null == query.getCurrent() || query.getCurrent() < 1) {
            return false;
        }
        if (null == query.getSize() || query.getSize() < 1) {
            return false;
        }
        return true;
    }

    public static boolean checkPageParmByNum(Integer current,Integer size){
        if (null == current || null == size) {
            return false;
        }
        if (current < 1 || size < 1) {
            return false;
        }
        return true;
    }


    /**
     * 对字符串进行去空格
     * @param str
     * @return
     */
    public static String trimWithString(String str){
        if (StringUtils.isNotEmpty(str)){
            return str.trim();
        }else {
            return "";
        }
    }

    /**
     * 校验参数非空
     * 1, 对象不为null<br/>
     * 2, 如果为String, 则长度不能为0、不能全是空白、不能是null字串<br/>
     * 3, 如果为数组和集合， 则长度不能为0
     * @param o
     * @param fields
     */
    public static void checkNotEmpty(Object o, String... fields) throws Exception {
        if (o == null) {
            throw new NullPointerException();
        }
        try {
            Class<?> c = o.getClass();
            for (String fieldName : fields) {
                Field field = c.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(o);
                if (isEmpty(value)) {
                    throw new ParamException(ResultVoUtils.paramException(CommonConstants.PARAM_IS_EMPTY,field.getName()));
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }


    /**
     * 把对象中string字段去左右空格并返回对象
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object trimWithObjectField(Object obj) throws Exception {
        if (obj == null) {
            return obj;
        }

        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            if (null != fieldValue && fieldValue instanceof String){
                String str = (String)fieldValue;
                field.set(obj,str.trim());
            }
        }

        return obj;


    }

    /**
     *
     * @param
     * @return
     */
    public static boolean isEmpty(Object value) {
        if (value != null) {
            if (value instanceof String) {
                String str = (String) value;
                if (!(str.length() == 0 || str.trim().length() == 0)) {
                    return false;
                }
            } else if (value.getClass().isArray()) {
                if (Array.getLength(value) > 0) {
                    return false;
                }
            } else if (value instanceof Collection) {
                Collection list = (Collection) value;
                if (list.size() > 0) {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * @param userInfo
     * @return // -1 临促 0分公司管理员 1 admin 2 分公司财务负责人 3活动负责人 4立项与活动负责人 5立项人6既是分公司财务也是分公司管理员
     * @throws ParamException
     */
    public static Integer getPersonType(LoginUserInfo userInfo) throws ParamException{
        Integer personType;
        if (userInfo.getUserType() == CommonConstants.LOGIN_USER_TYPE_LINCHU){
            personType = -1;
        }else if ("1".equals(userInfo.getUserRole())){
            if (null != userInfo.getWeixinUserInfo()){
                throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE));
            }
            personType = 1;
        }else if("2".equals(userInfo.getUserRole())){
            personType = 2;
        }else if("0".equals(userInfo.getUserRole())){
            personType = 0;
        }else if("6".equals(userInfo.getUserRole())){
            personType = 6;
        }else {
            if (null != userInfo.getWeixinUserInfo()){
                personType = 3;
            }else {
                personType = 4;
            }
        }
        return personType;
    }



}
