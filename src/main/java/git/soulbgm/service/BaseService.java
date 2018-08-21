package git.soulbgm.service;

import java.util.Collection;
import java.util.List;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-16 15:24
 * @description 所有service接口的基类
 */
public interface BaseService<T> {

    // TODO BaseService 新增

    /**
     * 保存对象到数据库中
     *
     * @param t 泛型对象
     * @return true:成功  false:不成功
     */
    boolean save(T t);

    /**
     * 批量保存对象到数据库中
     *
     * @param list 多个泛型对象
     * @return true:成功  false:不成功
     */
    boolean saveBatch(List<T> list);

    /**
     * 批量保存对象到数据库中
     *
     * @param list     多个泛型对象
     * @param batchNum 每次批量的个数
     * @return true:成功  false:不成功
     */
    boolean saveBatch(List<T> list, int batchNum);

    // TODO BaseService 删除

    /**
     * 按主键删除数据
     *
     * @param id 主键
     * @return true:成功  false:不成功
     */
    boolean removeById(Object id);

    /**
     * 按多个主键删除数据
     *
     * @param ids 多个主键拼接
     * @return true:成功  false:不成功
     */
    boolean removeByIds(String ids);

    // TODO BaseService 修改

    /**
     * 按主键修改数据有选择的修改数据
     * 注：如果实体字段中有为null或者empty的数据则不修改
     *
     * @param t 泛型对象
     * @return true:成功  false:不成功
     */
    boolean update(T t);

    // TODO BaseService 查询

    /**
     * 按主键查找数据
     *
     * @param id 主键
     * @return 泛型对象
     */
    T findById(Object id);

    /**
     * 按多个主键查询数据
     *
     * @param ids 多个主键拼接
     * @return 查询出的泛型对象
     */
    List<T> findByIds(String ids);

    /**
     * 查询全部数据
     *
     * @return 查询出的全部泛型对象
     */
    List<T> findAll();

    /**
     * 按泛型对象查询数据
     * 注：如果实体字段所有为null的数据不算条件
     *
     * @param t 泛型对象
     * @return 查询出的泛型对象
     */
    List<T> findByModel(T t);

    /**
     * 按分页查询出数据
     * 注：没有条件
     *
     * @param pageNum  页数
     * @param pageSize 页面显示数量
     * @return 查询出的泛型对象
     */
    List<T> findByPage(int pageNum, int pageSize);

    /**
     * 按分页查询出数据
     *
     * @param pageNum  页数
     * @param pageSize 页面显示数量
     * @param t        泛型对象
     * @return 查询出的泛型对象
     */
    List<T> findByPage(int pageNum, int pageSize, T t);

    int findCount(T t);
}
