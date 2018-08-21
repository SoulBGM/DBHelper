package git.soulbgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-17 10:45
 * @description
 * 注： MapperScan 要导入 tk.mybatis.spring.annotation.MapperScan  不能是  org.mybatis.spring.annotation.MapperScan
 */
@Component
@MapperScan("git.soulbgm.mapper")
public class TkMyBatisConfig {

    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("git.soulbgm.mapper");
        Properties propertiesMapper = new Properties();
        //通用mapper位置，不要和其他mapper、dao放在同一个目录
        propertiesMapper.setProperty("mappers", "git.soulbgm.common.BaseMapper");
        propertiesMapper.setProperty("notEmpty", "false");
        //主键UUID回写方法执行顺序,默认AFTER,可选值为(BEFORE|AFTER)
        propertiesMapper.setProperty("ORDER","BEFORE");
        mapperScannerConfigurer.setProperties(propertiesMapper);
        return mapperScannerConfigurer;
    }*/

}
