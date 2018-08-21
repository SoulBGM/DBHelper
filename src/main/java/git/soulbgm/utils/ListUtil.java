package git.soulbgm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-07-13 13:19
 * @description List相关工具类
 */
public class ListUtil {

    /**
     * List是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * List是否为非空
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * 新建一个ArrayList
     *
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<T>();
    }

    /**
     * 新建一个ArrayList
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> newArrayList(T... values) {
        if (values == null) {
            return null;
        }
        ArrayList<T> list = newArrayList();
        list.addAll(Arrays.asList(values));
        return list;
    }

    /**
     * 新建一个不可修改内容的List
     * ps:如果修改的话会报 {@link UnsupportedOperationException} 异常
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> List<T> newUnmodifiableList(T... values) {
        ArrayList<T> list = newArrayList(values);
        return list != null ? Collections.unmodifiableList(list) : null;
    }

    public static void main(String[] args) {
        List<Integer> listInt = newUnmodifiableList(1, 1, 1, 1, 1, 1, 1, 1, 1);
        System.out.println(listInt);
    }

}
