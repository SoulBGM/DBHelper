package git.soulbgm.service.impl;

import com.github.javafaker.Faker;
import git.soulbgm.Application;
import git.soulbgm.pojo.Student;
import git.soulbgm.service.StudentService;
import git.soulbgm.utils.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    private Student getStudent() {
        Student stu = new Student();
        Faker faker = new Faker();
        stu.setStudentName(faker.name().name());
        stu.setAddress(faker.address().fullAddress());
        stu.setEmail(faker.internet().emailAddress());
        stu.setBornDate(faker.date().birthday());
        stu.setPhone(faker.phoneNumber().cellPhone().replaceAll("-", ""));
        stu.setSex("男");
        stu.setLoginPwd("123");
        stu.setGradeId(1);
        return stu;
    }

    @Test
    public void save() {
        Student stu = getStudent();
        studentService.save(stu);
        System.out.println(stu.getStudentNo());
    }

    @Test
    public void saveBatch() {
        List<Student> list = ListUtil.newArrayList(getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent(), getStudent());
        System.out.println(studentService.saveBatch(list));
    }

    @Test
    public void removeById() {
        System.out.println(studentService.removeById("62935039721213952"));
    }

    @Test
    public void removeByIds() {
        System.out.println(studentService.removeByIds("'0cf4f1cafe104800a32b8d83d38ed3ab','178183cd34214d2193bfba75d17ecb51','26e9a56e5b0b45a8a2e884f383c86597','29d1acab417d427eb71b40649530ee40'"));
    }

    @Test
    public void update() {
        Student student = getStudent();
        student.setStudentNo("2cc322322be844d2aef094a5ec89a983");
        student.setLoginPwd("123456");
        System.out.println(studentService.update(student));
    }

    @Test
    public void findById() {
        System.out.println(studentService.findById("2cc322322be844d2aef094a5ec89a983"));
    }

    @Test
    public void findByIds() {
        System.out.println(studentService.findByIds("'2cc322322be844d2aef094a5ec89a983','30640c68bbf44c25ad77dc3170926aa3','3258d360c9e24c30aa86806f091fc611'").size());
    }

    @Test
    public void findAll() {
        System.out.println(studentService.findAll().size());
    }

    @Test
    public void findByModel() {
        Student stu = new Student();
        stu.setPhone("9675773802");
        System.out.println(studentService.findByModel(stu).size());
    }

    @Test
    public void findByPage() {
        System.out.println(studentService.findByPage(1,20).size());
    }

    @Test
    public void findByPage2(){
        Student stu = new Student();
        stu.setPhone("9675773802");
        System.out.println(studentService.findByPage(1,2,stu).size());
    }

    @Test
    public void findCount(){
        Student stu = new Student();
        stu.setPhone("9675773802");
        System.out.println(studentService.findCount(null));
    }
}