package git.soulbgm.pojo;


import git.soulbgm.common.annotation.IdBuild;
import git.soulbgm.common.enumerate.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Table(name = "grade")
public class Grade {

    /**
     * ID标识
     */
    @Id
    @IdBuild(idType = IdType.JDBC)
    private Long gradeId;

    /**
     * 年级名称
     */
    private String gradeName;

}
