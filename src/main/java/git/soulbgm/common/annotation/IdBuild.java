package git.soulbgm.common.annotation;

import git.soulbgm.common.enumerate.IdType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-21 10:07
 * @description ID生成策略
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface IdBuild {

    /**
     * 生成Id的类型
     *
     * @return
     */
    IdType idType() default IdType.SEQUENCE;

}
