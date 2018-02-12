package order.manager.dao.order;


import order.manager.dao.abst.BaseDao;
import order.manager.dao.customer.Customer;
import order.manager.dao.customer.CustomerQuery;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
public interface OrderInfoDAO extends BaseDao<OrderInfo> {

    List<OrderInfo> queryList(OrderInfoQuery query);

    int queryCount(OrderInfoQuery query);
}
