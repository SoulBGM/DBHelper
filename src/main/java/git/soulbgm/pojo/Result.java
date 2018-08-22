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

    private boolean success;
    private String msg;
    private Object obj;

}
