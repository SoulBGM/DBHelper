package git.soulbgm.utils;

import git.soulbgm.config.TkMyBatisConfig;
import git.soulbgm.pojo.Grade;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-06-20 8:58
 * @description Class的工具类
 */
public class ClassUtil {

    private static final String GET = "get";
    private static final String IS = "is";
    private static final String SET = "set";

    /**
     * 深度拷贝类
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>
     * @param <M>
     */
    public static <T, M> void copyClass(T source, M target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    /**
     * 通过反射的方式执行getXxx()方法
     *
     * @param obj   实体对象
     * @param field 字段
     * @return 执行了getXxx()方法之后的值
     */
    public static Object executeGetMethod(Object obj, Field field) {
        if (obj == null) {
            return null;
        }
        try {
            return obj.getClass().getMethod(buildGetMethod(field.getName(), field.getType() == Boolean.class)).invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * 通过反射的方法执行setXxx(xx)方法
     *
     * @param obj   实体对象
     * @param field 字段
     * @param val   执行set方法的值
     * @return 成功返回true，失败false
     */
    public static boolean executeSetMethod(Object obj, Field field, Object val) {
        if (obj == null) {
            return false;
        }
        try {
            if (val.getClass() == field.getType()) {
                Method method = obj.getClass().getMethod(buildSetMethod(field.getName()), field.getType());
                method.invoke(obj, val);
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 获取字段${clazz}注解
     *
     * @param field
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> clazz) {
        if (field != null && clazz != null) {
            return field.getAnnotation(clazz);
        } else {
            return null;
        }
    }

    /**
     * 判断字段中是否存在${clazz}注解
     *
     * @param field
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> boolean existFieldAnnotation(Field field, Class<T> clazz) {
        if (field != null && clazz != null) {
            return field.isAnnotationPresent(clazz);
        } else {
            return false;
        }
    }

    /**
     * 获取字段${clazz}注解
     *
     * @param method
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getMethodAnnotation(Method method, Class<T> clazz) {
        if (method != null && clazz != null) {
            return method.getAnnotation(clazz);
        } else {
            return null;
        }
    }

    /**
     * 判断字段中是否存在${clazz}注解
     *
     * @param method
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> boolean existMethodAnnotation(Method method, Class<T> clazz) {
        if (method != null && clazz != null) {
            return method.isAnnotationPresent(clazz);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Grade grade = new Grade();
        TkMyBatisConfig t = new TkMyBatisConfig();
        try {
            Method method = t.getClass().getDeclaredMethod("mapperScannerConfigurer");
            System.out.println(getMethodAnnotation(method, Bean.class));
            System.out.println(existMethodAnnotation(method, Bean.class));
            /*boolean reulst = executeSetMethod(grade, Grade.class.getDeclaredField("gradeName"), "一年级");
            System.out.println(grade);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过给字段名称构建出相应的get方法名称
     *
     * @param fieldName 字段名称
     * @return 组合之后的名称
     */
    private static String buildGetMethod(String fieldName, boolean isBoolean) {
        StringBuilder sb = new StringBuilder();
        if (isBoolean) {
            sb.append(IS);
        } else {
            sb.append(GET);
        }
        sb.append(Character.toUpperCase(fieldName.charAt(0)));
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

    /**
     * 通过给字段名称构建出相应的set方法名称
     *
     * @param fieldName 字段名称
     * @return 组合之后的名称
     */
    private static String buildSetMethod(String fieldName) {
        StringBuilder sb = new StringBuilder(SET);
        sb.append(Character.toUpperCase(fieldName.charAt(0)));
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

}
