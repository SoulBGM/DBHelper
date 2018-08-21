package git.soulbgm.utils;

import java.util.regex.Pattern;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-06-04 9:38
 * @description int, double, long 等数值类型处理工具类
 */
public class NumericalUtil {

    private static final String REGULAR_INTEGER = "^[-\\+]?[\\d]*$";


    /**
     * 判断1个或多个int值是否为0
     *
     * @param number
     * @return 有为null的返回false
     */
    public static boolean isNotZero(Integer... number) {
        for (int n : number) {
            if (number == null) {
                return false;
            }
			if (n == 0) {
				return false;
			}
        }
        return true;
    }

    /**
     * 判断是否为整数
     *
     * @param str
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile(REGULAR_INTEGER);
        return pattern.matcher(str).matches();
    }

    /**
     * 字符串转整形
     *
     * @param str
     * @return
     */
    public static Integer strToInteger(String str) {
        if (isInteger(str)) {
            return Integer.parseInt(str);
        } else {
            return null;
        }
    }

    /**
     * 字符串转整形
     *
     * @param str
     * @return
     */
    public static Integer strToInteger(String str, int defaultVal) {
        if (isInteger(str)) {
            return Integer.parseInt(str);
        } else {
            return defaultVal;
        }
    }

    /**
     * Integer类型转Long类型
     *
     * @param val
     * @return
     */
    public static Long integerToLong(Integer val) {
        if (val != null) {
            return val.longValue();
        }
        return null;
    }

    /**
     * Long类型转Integer类型
     *
     * @param val
     * @return
     */
    public static Integer longToInteger(Long val) {
        if (val != null) {
            return val.intValue();
        }
        return null;
    }

    /**
     * int类型 负数转正数
     *
     * @return
     */
    public static Integer intNegativeToPositiveNum(Integer num) {
        if (num < 0) {
            return Math.abs(num);
        }
        return num;
    }

    /**
     * int类型 正数转负数
     *
     * @return
     */
    public static Integer intPositiveToNegativeNum(Integer num) {
        if (num > 0) {
            return 0 - num;
        }
        return num;
    }

    /**
     * long类型 负数转正数
     *
     * @return
     */
    public static Long longNegativeToPositiveNum(Long num) {
        if (num < 0) {
            return Math.abs(num);
        }
        return num;
    }

    /**
     * long类型 正数转负数
     *
     * @return
     */
    public static Long longPositiveToNegativeNum(Long num) {
        if (num > 0) {
            return 0 - num;
        }
        return num;
    }
}
