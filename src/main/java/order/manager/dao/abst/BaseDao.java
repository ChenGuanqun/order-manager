package order.manager.dao.abst;

/**
 * 基础的DAO接口，所有自定义的DAO接口都可以继承该接口
 *
 */
public interface BaseDao<T> {

    /**
     * 通用的单条记录插入接口
     *
     * @param t 不要设置t的id，由数据库自动分配，并在插入成功后自动赋值
     * @return 插入成功返回true，否则为false
     */
    public boolean insert(T t);

    /**
     * 根据id查询一条记录
     *
     * @param id
     * @return
     */
    public T getById(long id);

    /**
     * 根据id删除一条记录
     *
     * @param id
     * @return
     */
    public boolean deleteById(long id);

    /**
     * 根据id更新一条记录
     *
     * @param t 需要设置t的id属性，否则会更新失败
     * @return
     */
    public boolean updateById(T t);

}
