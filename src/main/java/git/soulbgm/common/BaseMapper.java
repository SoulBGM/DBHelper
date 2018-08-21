package git.soulbgm.common;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-16 15:21
 * @description 所有Mapper的基类
 * 泛型(实体类)<T>的类型必须符合要求
 * 实体类按照如下规则和数据库表进行转换,注解全部是JPA中的注解:
 * 1. 表名默认使用类名,驼峰转下划线(只对大写字母进行处理),如UserInfo默认对应的表名为user_info。
 * 2. 表名可以使用@Table(name = “tableName”)进行指定,对不符合第一条默认规则的可以通过这种方式指定表名.
 * 3. 字段默认和@Column一样,都会作为表字段,表字段默认为Java对象的Field名字驼峰转下划线形式.
 * 4. 可以使用@Column(name = “fieldName”)指定不符合第3条规则的字段名
 * 5. 使用@Transient注解可以忽略字段,添加该注解的字段不会作为表字段使用.
 * 6. 建议一定是有一个@Id注解作为主键的字段,可以有多个@Id注解的字段作为联合主键.
 *
 * 所有的dao继承此类将具有以下通用方法
 * ===============================查询===============================
 * List<T> selectAll();					// 查询全部数据
 * T selectByPrimaryKey(Object key);	// 通过主键查询
 * T selectOne(T record);			    // 通过实体查询单个实体
 * List<T> select(T record);			// 通过实体查询多个实体
 * int selectCount(T record);			// 通过实体查询实体数量
 * List<T> selectByExample(Object o);	// 通过Example类进行复杂查询多个实体
 * int selectCountByExample(Object o);	// 通过Example类进行复杂查询数量
 * List<T> selectByRowBounds(T record, RowBounds rowBounds);
 * 										// 通过实体查询多个实体并进行分页 rowBounds构造方式有两个值一个是offset(偏移量)一个是limit(限制数)
 * List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);
 * 										// 通过Example类进行复杂查询多个实体并进行分页 ...
 * ===============================添加===============================
 * int insert(T record);				// 全部添加
 * int insertSelective(T record);		// 选择性的添加
 * int insertList(List<T> list);		// 批量插入   注：支持批量插入的数据库可以使用，例如MySQL,H2等
 * int insertUseGeneratedKeys(T record);// 如果主键为自增可使用此方法获取添加成功的主键       注：支持批量插入的数据库可以使用，例如MySQL,H2等
 * ===============================删除===============================
 * int delete(T record);				// 按照实体进行删除
 * int deleteByPrimaryKey(Object o);	// 按照主键进行删除
 * int deleteByExample(Object o);		// 通过Example类进行删除
 * ===============================修改===============================
 * int updateByPrimaryKey(T record);	// 按照实体进行修改
 * int updateByPrimaryKeySelective(T record);
 * 										// 按照实体进行有选择的修改
 * int updateByExample(T record, Object o);
 * 										// 通过Example类进行修改
 * int updateByExampleSelective(T record, Object o);
 * 										// 通过Example类进行有选择的修改
 *
 * Example的使用方法
 * createCriteria() 通过此方法创建查询
 * 	1、andIn(字段名称,List);
 *  2、andLike(字段名称,模糊查询的值必须加%);
 *  ...
 *
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>,IdsMapper<T> {
}
