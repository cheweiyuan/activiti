package com.cwy;

import com.cwy.beans.User;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * map  ->  bean
 */

public class MaoToBean {

    public static void main(String[] args) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("userName","zhangsan");
        map.put("age",22);
//        获取javaBean的描述信息
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
//        获取Javabean的属性描述器对象 返回的是一个描述器数组
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        User user = User.class.newInstance();
//        对描述器对象进行迭代
        for (PropertyDescriptor pd : pds) {
            //获取属性名称和属性的类型
            System.out.println(pd.getName() + "," + pd.getPropertyType());
            Method setter = pd.getWriteMethod();
            setter.invoke(user,map.get(pd.getName()));
        }
        System.out.println(user.toString());
    }
}
