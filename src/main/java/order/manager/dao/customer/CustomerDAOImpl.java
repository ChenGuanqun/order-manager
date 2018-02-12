package order.manager.dao.customer;


import order.manager.dao.abst.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
@Component
public class CustomerDAOImpl extends BaseDaoImpl<Customer> implements CustomerDAO {

    @Override
    public List<Customer> queryList(CustomerQuery query) {
        return getSqlSessionTemplate().selectList(getNameSpace() + "queryList", query);

    }

    @Override
    public int queryCount(CustomerQuery query) {
        return getSqlSessionTemplate().selectOne(getNameSpace() + "queryCount", query);
    }
}
