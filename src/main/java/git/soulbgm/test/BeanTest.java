package git.soulbgm.test;

import com.alibaba.druid.support.json.JSONUtils;
import git.soulbgm.utils.SpringBeanUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-29 10:21
 * @description
 */
@Component
public class BeanTest {

    public BeanTest test() {
        /*BaseServiceImpl gradeService = (BaseServiceImpl) SpringBeanUtil.getBean("gradeService");
        Grade grade = new Grade();
        grade.setGradeName("kkkkkkkkkk");
        boolean save = gradeService.save(grade);
        System.out.println(save);*/
        try {
            Object obj = SpringBeanUtil.getBean("gradeServiceImpl");
            // getDeclaredMethod  可以看到自身的 print public por
            // getMethod          看到父类包括自身的所有 public
            Method method = obj.getClass().getMethod("save", Object.class);
            Object parse = JSONUtils.parse("{\"gradeName\":\"gradeName2\"}");
            /*Grade grade = new Grade();
            grade.setGradeName("888888888888888888");*/
            Object invoke = method.invoke(obj, parse);
            System.out.println(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return this;
    }

}
