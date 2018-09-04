package git.soulbgm.common.aop;

import git.soulbgm.mapper.GradeMapper;
import git.soulbgm.pojo.Grade;
import git.soulbgm.utils.SpringBeanUtil;
import git.soulbgm.utils.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-28 16:54
 * @description
 */
@Aspect
@Component
public class DbAspect {

    @Pointcut("execution(public * git.soulbgm.mapper.*.insert*(..))")
    public void insert() {
    }

    @Pointcut("execution(public * git.soulbgm.mapper.*.update*(..))")
    public void update() {
    }

    @Pointcut("execution(public * git.soulbgm.mapper.*.delete*(..))")
    public void delete() {
    }

    @Around("insert() || update() || delete()")
    public Object addOperateLog(ProceedingJoinPoint pjp) throws Throwable {
        // 获得目标bean的class
        Class<?> clazz = pjp.getTarget().getClass();


        // 获得目标方法名称
        String methodName = pjp.getSignature().getName();
        // 获得参数
        Object[] params = pjp.getArgs();
        // 获得方法
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();

        Class<?>[] interfaces = clazz.getInterfaces();
        String name = interfaces[0].getName();
        name = name.substring(name.lastIndexOf(".") + 1);
        name = StringUtil.toLowerCase(name, 0, 1);

        Object bean = SpringBeanUtil.getBean(name);
        /*Method[] methods = bean.getClass().getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        Method method1 = bean.getClass().getMethod(methodName, Object.class);
        Object param = params[0];
        Object invoke = method1.invoke(bean, param);
        System.out.println(invoke);*/

        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
