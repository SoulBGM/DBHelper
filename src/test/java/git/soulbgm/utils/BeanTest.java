package git.soulbgm.utils;

import com.sun.glass.ui.Application;
import git.soulbgm.pojo.Grade;
import git.soulbgm.service.GradeService;
import git.soulbgm.service.impl.BaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 14:56
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BeanTest {

    @Test
    public void test() {
        /*BaseServiceImpl gradeService = (BaseServiceImpl) SpringBeanUtil.getBean("gradeService");
        Grade grade = new Grade();
        grade.setGradeName("kkkkkkkkkk");
        boolean save = gradeService.save(grade);
        System.out.println(save);*/
        try {
            Object obj = SpringBeanUtil.getBean("gradeService");
            // getDeclaredMethod  可以看到自身的 print public por
            // getMethod          看到父类包括自身的所有 public
            Method method = obj.getClass().getMethod("save", Object.class);
            Grade grade = new Grade();
            grade.setGradeName("888888888888888888");
            Object invoke = method.invoke(obj, grade);
            System.out.println(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
