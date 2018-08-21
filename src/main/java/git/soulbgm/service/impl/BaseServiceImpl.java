package git.soulbgm.service.impl;

import com.github.pagehelper.PageHelper;
import git.soulbgm.common.BaseMapper;
import git.soulbgm.common.annotation.IdBuild;
import git.soulbgm.common.enumerate.IdType;
import git.soulbgm.service.BaseService;
import git.soulbgm.utils.ClassUtil;
import git.soulbgm.utils.SequenceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-16 16:05
 * @description 所有xxxServiceImpl类的的基类
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M mapper;

    protected static boolean retBool(Integer result) {
        return null != result && result >= 1;
    }

    @Override
    public boolean save(T t) {
        if (t == null) {
            return false;
        }
        idBuilder(t);

        return retBool(mapper.insertSelective(t));
//        return retBool(mapper.insertUseGeneratedKeys(t));
    }

    @Override
    public boolean saveBatch(List<T> list) {
        return saveBatch(list, 30);
    }

    @Override
    public boolean saveBatch(List<T> list, int batchNum) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (T t : list) {
            idBuilder(t);
        }
        int size = list.size();
        int countNum = size % batchNum == 0 ? size / batchNum : (size / batchNum) + 1;
        int index = 0;
        int count = 0;
        for (int i = 1; i <= countNum; i++) {
            int endIndex = batchNum * i;
            if (endIndex > size) {
                endIndex = size;
            }
            List<T> newList = new ArrayList<>();
            for (int j = index; j < endIndex; j++) {
                newList.add(list.get(j));
            }
            count += mapper.insertList(newList);
            index += batchNum;
        }
        if (count == list.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeById(Object id) {
        if (id == null) {
            return false;
        }
        return retBool(mapper.deleteByPrimaryKey(id));
    }

    @Override
    public boolean removeByIds(String ids) {
        if (ids == null || "".equals(ids)) {
            return false;
        }
        return retBool(mapper.deleteByIds(ids));
    }

    @Override
    public boolean update(T t) {
        if (t == null) {
            return false;
        }
        return retBool(mapper.updateByPrimaryKeySelective(t));
    }

    @Override
    public T findById(Object id) {
        if (id == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findByIds(String ids) {
        if (ids == null || "".equals(ids)) {
            return null;
        }
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> findByModel(T t) {
        return mapper.select(t);
    }

    @Override
    public List<T> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<T> findByPage(int pageNum, int pageSize, T t) {
        PageHelper.startPage(pageNum, pageSize);
        return findByModel(t);
    }

    @Override
    public int findCount(T t) {
        return mapper.selectCount(t);
    }

    /**
     * id 生成器
     * 主要判断条件是 看model中是否有Id的注解
     * 如果有接着检查是否有IdBuild的注解
     * 然后获取IdBuild中IdType的值
     * 如果IdType = UUID 则生成UUID
     * 如果IdType = SEQUENCE 则通过SEQUENCE生成
     *
     * @param t
     */
    private void idBuilder(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (ClassUtil.existFieldAnnotation(field, Id.class)){
                IdBuild idBuild = ClassUtil.getFieldAnnotation(field, IdBuild.class);
                if (idBuild != null) {
                    if (idBuild.idType() == IdType.UUID) {
                        // 如果idType类型等于IdType.UUID
                        ClassUtil.executeSetMethod(t, field, SequenceBuilder.uuid());
                    } else if(idBuild.idType() == IdType.SEQUENCE){
                        // 如果idType类型等于IdType.SEQUENCE
                        Object val = SequenceBuilder.sequenceId();
                        if (field.getType() == String.class) {
                            val = String.valueOf(val);
                        }
                        ClassUtil.executeSetMethod(t, field, val);
                    }
                }
            }
        }
    }
}
