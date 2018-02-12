package order.manager.dao.abst;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

/**
 * 通用的BaseDao，默认根据泛型类来确定namespace，规则为泛型类全名+Mapper.+方法名
 *
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    protected static final int IN_NUMBER = 1000;// ddb限制in语句里面数量为1024，这里统一限制为1000
    private static final String INSERT_FLAG = "insert";
    private static final String QUERY_BY_ID = "getById";
    private static final String DELETE_BY_ID = "deleteById";
    private static final String UPDATE_BY_ID = "updateById";

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;
    private String namespace;

    public BaseDaoImpl() {
        this.namespace = getNameSpace();
    }

    protected SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @SuppressWarnings("rawtypes")
    public String getNameSpace() {
        Class clazz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz.getName() + "Mapper.";
    }

    @Override
    public boolean insert(T t) {
        int count = getSqlSessionTemplate().insert(namespace + INSERT_FLAG, t);
        return count == 1 ? true : false;
    }

    @Override
    public T getById(long id) {
        return getSqlSessionTemplate().selectOne(namespace + QUERY_BY_ID, id);
    }

    @Override
    public boolean deleteById(long id) {
        int count = getSqlSessionTemplate().delete(namespace + DELETE_BY_ID, id);
        return count == 1 ? true : false;
    }

    @Override
    public boolean updateById(T t) {
        int count = getSqlSessionTemplate().update(namespace + UPDATE_BY_ID, t);
        return count == 1 ? true : false;
    }

}
