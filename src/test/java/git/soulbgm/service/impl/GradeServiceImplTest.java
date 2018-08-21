package git.soulbgm.service.impl;

import com.github.javafaker.Faker;
import git.soulbgm.Application;
import git.soulbgm.pojo.Grade;
import git.soulbgm.service.GradeService;
import git.soulbgm.utils.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GradeServiceImplTest {

    @Autowired
    private GradeService gradeService;

    private Grade getGrade() {
        Grade grade = new Grade();
        Faker faker = new Faker();
        grade.setGradeName(faker.name().lastName());
        return grade;
    }

    @Test
    public void save() {
        Grade grade = getGrade();
        gradeService.save(grade);
        System.out.println(grade.getGradeId());
    }

    @Test
    public void saveBatch() {
        ArrayList<Grade> grades = ListUtil.newArrayList(getGrade(), getGrade(), getGrade(), getGrade(), getGrade(), getGrade(), getGrade(), getGrade(), getGrade(), getGrade(), getGrade());
        System.out.println(gradeService.saveBatch(grades));
    }

    @Test
    public void saveBatch1() {
    }

    @Test
    public void removeById() {
    }

    @Test
    public void removeByIds() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByIds() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByModel() {
    }

    @Test
    public void findByPage() {
    }
}