package git.soulbgm.pojo;

import git.soulbgm.common.annotation.IdBuild;
import git.soulbgm.common.enumerate.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Data
@Table(name = "student")
public class Student {

    @Id
    @IdBuild
    private String studentNo;
    private String loginPwd;
    private Integer gradeId;
    private String studentName;
    private String sex;
    private String phone;
    private String address;
    private Date bornDate;
    private String email;

}
