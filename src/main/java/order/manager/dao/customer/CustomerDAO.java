package order.manager.dao.customer;


import order.manager.dao.abst.BaseDao;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
public interface CustomerDAO extends BaseDao<Customer> {

    List<Customer> queryList(CustomerQuery query);

    int queryCount(CustomerQuery query);
}
