package git.soulbgm.controller;

import git.soulbgm.common.annotation.ApiDoc;
import git.soulbgm.pojo.Grade;
import git.soulbgm.pojo.Result;
import git.soulbgm.service.GradeService;
import git.soulbgm.service.impl.BaseServiceImpl;
import git.soulbgm.utils.PropertyUtil;
import git.soulbgm.utils.SpringBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试接口
 *
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 0:47
 * @description 访问地址：http://IP:端口/swagger-ui.html
 */
@Api("demo")
@RestController
@RequestMapping("/demo")
public class SwaggerControllerDemo {

    /*
    对象属性   	    @ApiModelProperty	用在出入参数对象的字段上
    协议集描述	    @Api	            用于controller类上
    协议描述	        @ApiOperation	    用在controller的方法上
    Response集	    @ApiResponses	    用在controller的方法上
    Response	    @ApiResponse	    用在 @ApiResponses里边
    非对象参数集	    @ApiImplicitParams	用在controller的方法上
    非对象参数描述	    @ApiImplicitParam	用在@ApiImplicitParams的方法里边
    描述返回对象的意义	@ApiModel	        用在返回对象类上

    @ApiImplicitParam
    paramType		查询参数类型
        path	        以地址的形式提交数据
        query	        直接跟参数完成自动映射赋值
        body	        以流的形式提交 仅支持POST
        header	        参数在request headers 里边提交
        form	        以form表单的形式提交 仅支持POST
    dataType		参数的数据类型 只作为标志说明，并没有实际验证
        Long
        String
    name		    接收参数名
    value		    接收参数的意义描述
    required		参数是否必填
        true	        必填
        false	        非必填
    defaultValue	默认值
     */

    @Autowired
    private GradeService gradeService;

    /**
     * 查看所有的年级信息
     *
     * @return
     */
    @ApiOperation(value = "查看所有的年级信息")
    @RequestMapping(value = "/queryAllGrade", method = RequestMethod.GET)
    @ApiDoc(Grade.class)
    public List<Grade> queryAllGrade() {
        return gradeService.findAll();
    }

    /**
     * 查询年级信息
     *
     * @param id ID标识
     * @return
     */
    @ApiOperation(value = "查询年级信息", notes = "根据Id查询年级信息")
    @ApiImplicitParam(name = "id", value = "年级ID", dataType = "Long", required = true, paramType = "path")
    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    @ApiDoc(Grade.class)
    public Grade queryById(@PathVariable(value = "id") Long id) {
        return gradeService.findById(id);
    }

    /**
     * 添加年级信息
     *
     * @param grade 年级信息
     * @return
     */
    @ApiOperation("添加年级信息")
//    @ApiImplicitParam(name = "grade", value = "年级信息", dataType = "Grade", required = true)
    @RequestMapping(value = "/addGrade", method = RequestMethod.POST)
    @ApiDoc(Result.class)
    public Result addGrade(@RequestBody Grade grade) {
        Result result = new Result();
        boolean flag = gradeService.save(grade);
        result.setSuccess(flag);
        if (flag) {
            result.setMsg("添加年级成功id为" + grade.getGradeId());
        }
        return result;
    }

    /**
     * 获取配置文件数据
     *
     * @param key 配置文件中的Key
     * @return
     */
    @ApiOperation("获取配置文件数据")
    @ApiImplicitParam(name = "key", value = "配置Key", paramType = "query", required = true)
    @RequestMapping(value = "/getProperty", method = RequestMethod.GET)
    @ApiDoc(value = String.class)
    public String getProperty(@RequestParam("key") String key) {
        return PropertyUtil.getProperty(key);
    }

}
