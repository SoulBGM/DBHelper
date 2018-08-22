package git.soulbgm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 12:21
 * @description
 */
@Component
public class PropertyUtil {

    private static Environment environment;

    @Autowired
    public void setEnvironment(Environment environment){
        PropertyUtil.environment = environment;
    }

    public static String getProperty(String key){
        return environment.getProperty(key);
    }

}
