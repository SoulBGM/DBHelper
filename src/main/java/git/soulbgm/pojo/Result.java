package git.soulbgm.pojo;

import lombok.Data;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 1:02
 * @description
 */
@Data
public class Result {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回值
     */
    private Object obj;

}
