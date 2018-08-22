package git.soulbgm.controller;

import git.soulbgm.pojo.Grade;
import git.soulbgm.pojo.Result;
import git.soulbgm.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 0:47
 * @description 访问地址：http://IP:端口/swagger-ui.html
 */
@Api("demo")
@RestController
public class SwaggerControllerDemo {

    /*
     @Api：修饰整个类，描述Controller的作用
     @ApiOperation：描述一个类的一个方法，或者说一个接口
     @ApiParam：单个参数描述
     @ApiModel：用对象来接收参数
     @ApiProperty：用对象接收参数时，描述对象的一个字段
     @ApiResponse：HTTP响应其中1个描述
     @ApiResponses：HTTP响应整体描述
     @ApiIgnore：使用该注解忽略这个API
     @ApiError ：发生错误返回的信息
     @ApiImplicitParam：一个请求参数
     @ApiImplicitParams：多个请求参数
     */

    @Autowired
    private GradeService gradeService;

    @ApiOperation(value = "查看所有的年级信息")
    @RequestMapping(value = "queryAllGrade", method = RequestMethod.GET)
    public List<Grade> queryAllGrade() {
        return gradeService.findAll();
    }

    @ApiOperation(value = "查询年级信息", notes = "根据Id查询年级信息")
    @ApiImplicitParam(name = "id", value = "年级ID", dataType = "Long", required = true, paramType = "path")
    @RequestMapping(value = "queryById/{id}", method = RequestMethod.GET)
    public Grade queryById(@PathVariable(value = "id") Long id) {
        return gradeService.findById(id);
    }

    @ApiOperation("添加年级信息")
//    @ApiImplicitParam(name = "grade", value = "年级信息", dataType = "Grade", required = true)
    @RequestMapping(value = "addGrade", method = RequestMethod.POST)
    public Result addGrade(@RequestBody Grade grade) {
        Result result = new Result();
        boolean flag = gradeService.save(grade);
        result.setSuccess(flag);
        if (flag) {
            result.setMsg("添加年级成功id为" + grade.getGradeId());
        }
        return result;
    }

}
