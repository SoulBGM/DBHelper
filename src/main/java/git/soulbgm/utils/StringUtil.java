package git.soulbgm.utils;

import java.util.Random;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018年4月24日 下午6:50:38
 * @Description 字符串处理工具类
 */
public class StringUtil {

    /**
     * 下划线
     */
    private static final char UNDERLINE = '_';

    /**
     * 判断1个或多个字符串是否不为空
     * 判断条件 null 和 长度不为0
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String... str) {
        for (String s : str) {
            if (s == null) {
                return false;
            }
            if (s.trim().length() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断1个或多个字符串是否不为空
     * 判断条件 null 和 长度不为0
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String... str) {
        for (String s : str) {
            if (s == null) {
                return true;
            }
            if (s.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断1个或多个字符串数组是否不为空
     * 判断条件 null 和 长度不为0
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmptyArray(Object[]... objects) {
        for (Object[] obj : objects) {
            if (obj == null) {
                return false;
            }
            if (obj.length == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 源字符串是否包含给定的字符串
     *
     * @param sourceString
     * @param includeString
     * @return
     */
    public static boolean isInclude(String sourceString, String includeString) {
        if (StringUtil.isEmpty(sourceString)) {
            return false;
        }
        if (sourceString.indexOf(includeString) != -1) {
            return true;
        }
        return false;
    }

    /**
     * 将一组字符才以指定的字符链接起来
     *
     * @param linkStr 链接字符
     * @param strs    需要连接的动态参数
     * @return
     */
    public static String join(String linkStr, String... strs) {
        if (isNotEmpty(strs) && isNotEmpty(linkStr)) {
            StringBuffer result = new StringBuffer();
            for (String temp : strs) {
                result.append(temp + linkStr);
            }
            return result.substring(0, result.length() - linkStr.length());
        }
        return null;
    }

    /**
     * Object 转 String
     *
     * @param obj
     * @return
     */
    public static String objToStr(Object obj) {
        String str = String.valueOf(obj);
        return "null".equals(str) ? null : str;
    }

    /**
     * Object 转 String
     * 如果obj为null 则返回 defaultStr 否则 返回转成功后的obj字符串
     *
     * @param obj
     * @return
     */
    public static String objToStr(Object obj, String defaultStr) {
        String str = objToStr(obj);
        return str == null ? defaultStr : str;
    }

    /**
     * 删除起始字符
     *
     * @param str
     * @param trim
     * @return
     */
    public static String trimStart(String str, String trim) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("^(" + trim + ")+", "");
    }

    /**
     * 删除末尾字符
     *
     * @param str
     * @param trim
     * @return
     */
    public static String trimEnd(String str, String trim) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("(" + trim + ")+$", "");
    }

    /**
     * 删除起始和末尾字符
     *
     * @param str
     * @param trim
     * @return
     */
    public static String trimStartEnd(String str, String trim) {
        str = trimStart(str, trim);
        str = trimEnd(str, trim);
        return str;
    }

    /**
     * 随机生成一定长度的字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    /**
     * 随机生成长度为30的字符串
     *
     * @return
     */
    public static String getRandomString() {
        return getRandomString(30);
    }

    /**
     * 根据给定的字符截取给定字符后面的字符串
     * 从后往前搜索给定字符
     * 例子：cutOutEndString("123.txt", ".", false);   结果：txt
     * 例子：cutOutEndString("123.txt", ".", true);   结果：.txt
     *
     * @param source
     * @param cutOutSymbol
     * @param flag         true包含给定字符串   false不包含
     * @return
     */
    public static String cutOutEndString(String source, String cutOutSymbol, boolean flag) {
        if (isEmpty(source, cutOutSymbol)) {
            return null;
        }
        int increment = cutOutSymbol.length();
        if (flag) {
            increment = 0;
        }
        return source.substring(source.lastIndexOf(cutOutSymbol) + increment, source.length());
    }

    /**
     * 根据给定的字符截取给定字符后面的字符串
     * 从后往前搜索给定字符
     * 例子：cutOutEndString("123.txt", ".");   结果：txt
     *
     * @param source
     * @param cutOutSymbol
     * @return
     */
    public static String cutOutEndString(String source, String cutOutSymbol) {
        return cutOutEndString(source, cutOutSymbol, false);
    }

    /**
     * 根据给定的字符截取给定字符前面的字符串
     * 从后往前搜索给定字符
     * 例子：cutOutEndString("123.txt", ".", false);   结果：123
     * 例子：cutOutEndString("123.txt", ".", true);   结果：123.
     *
     * @param source
     * @param cutOutSymbol
     * @param flag         true包含给定字符串 false不包含
     * @return
     */
    public static String cutOutTopString(String source, String cutOutSymbol, boolean flag) {
        if (isEmpty(source, cutOutSymbol)) {
            return null;
        }
        int increment = 0;
        if (flag) {
            increment = cutOutSymbol.length();
        }
        return source.substring(0, source.lastIndexOf(cutOutSymbol) + increment);
    }

    /**
     * 根据给定的字符截取给定字符前面的字符串
     * 从后往前搜索给定字符
     * 例子：cutOutEndString("123.txt", ".");   结果：123
     *
     * @param source
     * @param cutOutSymbol
     * @return
     */
    public static String cutOutTopString(String source, String cutOutSymbol) {
        return cutOutTopString(source, cutOutSymbol, false);
    }

    /**
     * 转换${start}开始${end}结束(不包括${end}位置)的字符串为小写
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String toLowerCase(String str, int start, int end) {
        if (isEmpty(str)) {
            return null;
        }
        String str2 = str.substring(start, end);
        str2 = str2.toLowerCase();
        if (end < str.length()) {
            str = str.substring(0, start) + str2 + str.substring(end);
        } else {
            str = str2;
        }
        return str;
    }

    /**
     * 转换字符串为小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * 转换${start}开始${end}结束(不包括${end}位置)的字符串为大写
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String toUpperCase(String str, int start, int end) {
        if (isEmpty(str)) {
            return null;
        }
        String str2 = str.substring(start, end);
        str2 = str2.toUpperCase();
        if (end < str.length()) {
            str = str.substring(0, start) + str2 + str.substring(end);
        } else {
            str = str2;
        }
        return str;
    }

    /**
     * 转换字符串为小写
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * 驼峰字符转下划线字符
     *
     * @param param 驼峰字符
     * @return 下划线字符
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线字符转驼峰字符
     *
     * @param param 下划线字符
     * @return 驼峰字符
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
