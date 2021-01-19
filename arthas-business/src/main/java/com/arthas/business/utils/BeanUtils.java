package com.arthas.business.utils;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public abstract class BeanUtils {

    private static Logger logger = Logger.getLogger(BeanUtils.class);

    /**
     * Get a property of the object
     *
     * @param object   An object
     * @param propName A property name of the object
     * @return If the property is exists, its value will be returned,or
     * null instead.
     */
    public static final Object getProperty(Object object, String propName) {
        if (object == null) return null;
        Class clazz = object.getClass();
        //try to visit the get method
        String methodName = "get" +
                propName.substring(0, 1).toUpperCase() +
                propName.substring(1);
        try {
            Method getMethod = clazz.getMethod(methodName, new Class[0]);
            return getMethod.invoke(object, new Object[0]);
        } catch (Exception e) {
            //No such get method, the property is not visiable
            return null;
        }
    }

    /**
     * 在对象之间拷贝值,并且不保存旧值
     *
     * @param destination 目标对象
     * @param source      　源对象
     * @throws Exception ex
     */
    /*public static void copyProperties(Object destination, Object source) {
        try {
            copyProperties(destination, source, false);
        } catch (Exception e) {
            LoggerUtils.info("bean utils copyProperties exception");
        }
    }*/


    /**
     * 在对象之间拷贝值.
     *
     * @param dest       目标对象
     * @param src        　源对象
     * @param keepOnNull 为true时，如果source的值为空，则维持dest的旧值
     * @throws Exception
     */
    public static void copyProperty(Object dest, Object src) {
        boolean keepOnNull = true;
        try {
            if (src == null || dest == null) return;
            String destJson = JSON.toJSONString(dest);
            BeanInfo info = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (int i = 0; i < props.length; i++) {
                PropertyDescriptor srcProp = props[i];
                PropertyDescriptor destProp = findProperty(dest, srcProp.getName());
                if (destProp != null && destProp.getWriteMethod() != null && srcProp.getReadMethod() != null) {
                    try {
                        Object srcVal = srcProp.getReadMethod().invoke(src, new Object[0]);
                        Object destVal = destProp.getReadMethod().invoke(dest, new Object[0]);
                        if (keepOnNull) {
                            //If keepOnNull is open ,keep the old value if it exist
                            if (srcVal instanceof String && StringUtil.isEmpty(String.valueOf(srcVal)) && dest != null) {
                                srcVal = destVal;
                            } else if (dest != null && srcVal == null) {
                                srcVal = destVal;
                            }
                        }

                        //set the property only when the input is the same type as the dest type
                        destProp.getWriteMethod().invoke(dest, new Object[]{srcVal});
                    } catch (Exception ex) {
                        //if the property type are not matched, skip this property
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("src=").append(JSON.toJSONString(src));
            sb.append("dest=").append(destJson);
            sb.append("result=").append(JSON.toJSONString(dest));
            LoggerUtils.info(sb.toString(), 3);
        } catch (IntrospectionException e) {
            LoggerUtils.info("解析数据错误。。。");
        }


    }

    /**
     * 在对象之间拷贝值.
     *
     * @param dest       目标对象
     * @param src        源对象
     * @param keepOnNull 为true时，如果源对象的属性值为空，则不维持目标对象的旧值，则用源对象null值覆盖目标对象的值
     * @throws Exception
     */
    public static void copyProperty(Object dest, Object src, boolean keepOnNull) {
        keepOnNull = !keepOnNull;
        try {
            if (src == null || dest == null) return;
            String destJson = JSON.toJSONString(dest);
            BeanInfo info = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (int i = 0; i < props.length; i++) {
                PropertyDescriptor srcProp = props[i];
                PropertyDescriptor destProp = findProperty(dest, srcProp.getName());
                if (destProp != null && destProp.getWriteMethod() != null && srcProp.getReadMethod() != null) {
                    try {
                        Object srcVal = srcProp.getReadMethod().invoke(src, new Object[0]);
                        Object destVal = destProp.getReadMethod().invoke(dest, new Object[0]);
                        if (keepOnNull) {
                            //If keepOnNull is open ,keep the old value if it exist
                            if (srcVal instanceof String && StringUtil.isEmpty(String.valueOf(srcVal)) && dest != null) {
                                srcVal = destVal;
                            } else if (dest != null && srcVal == null) {
                                srcVal = destVal;
                            }
                        }

                        //set the property only when the input is the same type as the dest type
                        destProp.getWriteMethod().invoke(dest, new Object[]{srcVal});
                    } catch (Exception ex) {
                        //if the property type are not matched, skip this property
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("src=").append(JSON.toJSONString(src));
            sb.append("dest=").append(destJson);
            sb.append("result=").append(JSON.toJSONString(dest));
            LoggerUtils.info(sb.toString());
        } catch (IntrospectionException e) {
            LoggerUtils.info("解析数据错误。。。");
        }

    }


    private static void copyProperties(Object dest, Object src, boolean keepOnNull) throws Exception {

    }


    /**
     * 查找对象属性.
     *
     * @param object
     * @param name
     * @return
     * @throws java.beans.IntrospectionException
     */
    private static PropertyDescriptor findProperty(Object object, String name) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[] properties = info.getPropertyDescriptors();
        for (int i = 0; i < properties.length; i++) {
            PropertyDescriptor property = properties[i];
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
    }

    /**
     * 将 bean 中值不为 null 的属性值全部打印出来.
     * <p>例子：</p>
     * <pre>
     * MemberInfo memberInfo = new MemberInfo();
     * memberInfo.setId(650000);
     * memberInfo.setMemberName(&quot;TyroneChan&quot;);
     * String dump = dumpNotNull(memberInfo);
     * </pre>
     * <p>dump 的值为：<br/>
     * com.binguo.model.domain.MemberInfo{id = 650000, memberName = "TyroneChan", }</p>
     *
     * @param bean Object 对象
     * @return
     */
    public static String dumpNotNull(Object bean) {
        return dump(bean, false);
    }

    /**
     * 调试, 打印出给定 Bean 的所有属性的取值.
     *
     * @param bean Bean 对象
     * @return
     */
    private static String dump(Object bean, boolean includeNullValue) {
        PropertyDescriptor[] descriptors = getAvailablePropertyDescriptors(bean);
        final String beanClassName = bean.getClass().getName();
        final StringBuffer values = new StringBuffer();

        for (int i = 0; descriptors != null && i < descriptors.length; i++) {
            Method readMethod = descriptors[i].getReadMethod();
            try {
                Object propValue = readMethod.invoke(bean, null);
                final String propName = descriptors[i].getName();
                final String propType = descriptors[i].getPropertyType().getName();
                if (null == propValue && !includeNullValue) {
                    continue;
                }
                values.append(propName).append(" = ");
                if ("java.lang.String".equals(propType)) {
                    values.append("\"");
                    values.append(propValue);
                    values.append("\"");
                } else {
                    values.append(propValue);
                }
                values.append(", ");
            } catch (Exception e) {
                logger.error("error occurs ", e);
            }
        }

        if (values.length() == 0) {
            values.append(" ");
        } else {
            values.deleteCharAt(values.length() - 2);//删除最后的逗号
        }

        final StringBuffer sb = new StringBuffer();
        sb.append(beanClassName);
        sb.append("{");
        sb.append(values);
        sb.append("}");
        return sb.toString();
    }

    /**
     * 从 bean 中读取有效的属性描述符.
     * <p/>
     * NOTE: 名称为 class 的 PropertyDescriptor 被排除在外.
     *
     * @param bean Object - 需要读取的 Bean
     * @return PropertyDescriptor[] - 属性列表
     */
    private static PropertyDescriptor[] getAvailablePropertyDescriptors(Object bean) {
        try {
            // 从 Bean 中解析属性信息并查找相关的 write 方法
            BeanInfo info = Introspector.getBeanInfo(bean.getClass());
            if (info != null) {
                PropertyDescriptor pd[] = info.getPropertyDescriptors();
                Vector<PropertyDescriptor> columns = new Vector<PropertyDescriptor>();

                for (PropertyDescriptor aPd : pd) {
                    String fieldName = aPd.getName();

                    if (fieldName != null && !fieldName.equals("class")) {
                        columns.add(aPd);
                    }
                }

                PropertyDescriptor[] arrays = new PropertyDescriptor[columns.size()];

                for (int j = 0; j < columns.size(); j++) {
                    arrays[j] = columns.get(j);
                }
                return arrays;
            }
        } catch (Exception ex) {
            logger.error("error occurs ", ex);
            return null;
        }
        return null;
    }


    public static Map<String, String> objMapToStrMap(Map<String, Object> strMap) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<Map.Entry<String, Object>> it = strMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getValue() != null) {
                map.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return map;
    }


    /**
     * 将map转换成Bean，Bean的属性名与map的key值对应时不区分大小写，并对map中key做忽略OMIT_REG正则处理
     *
     * @param <E>
     * @param cla
     * @param map
     * @return
     */
    public static <E> E toBean(Class<E> cla, Map<String, Object> map) {
        // 创建对象
        E obj = null;
        try {
            obj = cla.newInstance();
        } catch (Exception e) {


            return null;
        }
        // 进行值装入
        Method[] ms = cla.getMethods();
        for (Method method : ms) {
            String name = method.getName();
            String mname = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
            if (mname.startsWith("set") || mname.startsWith("Set")) {
                Class[] clas = method.getParameterTypes();
                String a = mname.substring(3, mname.length());
                a = captureName(a);
                Object v = map.get(a);
                if (v != null && clas.length == 1) {
                    try {
                        method.invoke(obj, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }


    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }


    //Discription:[深度复制方法,需要对象及对象所有的对象属性都实现序列化]　
    @SuppressWarnings("unchecked")
    public static <T> T clone(Object src) throws RuntimeException {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(src);
            out.flush();


            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            return (T) in.readObject();
        } catch (Exception e) {
            logger.error("Exception src=" + JSON.toJSONString(src), e);
        } finally {
            //#################################close out #######################################//
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    logger.error("close out IOException src=" + JSON.toJSONString(src), e);
                }
            }
            //#################################close in #######################################//
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    logger.error("close in IOException src=" + JSON.toJSONString(src), e);
                }
            }
        }
        return null;

    }


}
