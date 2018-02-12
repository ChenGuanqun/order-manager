package order.manager.dao.order;

import order.manager.dao.customer.Customer;
import order.manager.dao.customer.CustomerQuery;

import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/11
 */
public interface OrderInfoService {

    boolean insert(OrderInfo orderInfo, String operator);

    boolean deleteById(long id);

    boolean updateById(OrderInfo orderInfo, String operator);

    List<OrderInfo> queryList(OrderInfoQuery query);

    int queryCount(OrderInfoQuery query);
}
