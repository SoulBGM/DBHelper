package git.soulbgm.utils;


import git.soulbgm.Application;
import git.soulbgm.test.BeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 14:56
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBeanTest {

    @Autowired
    private BeanTest test;


    @Test
    public void test() {
        test.test();
    }
}
