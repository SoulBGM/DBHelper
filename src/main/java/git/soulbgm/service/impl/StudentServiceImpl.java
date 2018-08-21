package git.soulbgm.service.impl;

import git.soulbgm.mapper.StudentMapper;
import git.soulbgm.pojo.Student;
import git.soulbgm.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-16 17:51
 * @description
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {
}
