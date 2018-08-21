package git.soulbgm.utils;

import java.util.UUID;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-17 13:51
 * @description 序列生成器
 */
public class SequenceBuilder {

    private static Sequence seq = new Sequence((int) (Math.random() * 9998) + 1, (int) (Math.random() * 9998) + 1);

    /**
     * 获取UUID
     *
     * @param flag true不带-字符  false带-字符
     * @return 不带-的uuid字符串
     */
    public static String uuid(boolean flag) {
        String uuid = UUID.randomUUID().toString();
        if (flag) {
            uuid = uuid.replaceAll("-", "");
        }
        return uuid;
    }

    /**
     * 获取UUID
     *
     * @return 不带-的uuid字符串
     */
    public static String uuid() {
        return uuid(true);
    }

    /**
     * 基于Twitter的Snowflake算法实现分布式高效有序ID生产黑科技(sequence)
     *
     * @return sequence通过算法生成的ID
     */
    public static long sequenceId() {
        return seq.nextId();
    }

}
