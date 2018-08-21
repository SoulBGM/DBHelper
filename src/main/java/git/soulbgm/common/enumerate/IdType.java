package git.soulbgm.common.enumerate;

import java.util.UUID;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-21 10:01
 * @description ID生成几种策略
 */
public enum IdType {

    /**
     * 标识Id由数据库自增生成
     */
    JDBC,

    /**
     * 标识Id由UUID生成
     */
    UUID,

    /**
     * 标识Id使用基于Twitter的Snowflake算法生成分布式高效有序ID
     */
    SEQUENCE

}
