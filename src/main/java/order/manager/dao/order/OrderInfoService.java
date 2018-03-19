package order.manager.dao.order;

import order.manager.dao.customer.Customer;
import order.manager.dao.customer.CustomerQuery;
import order.manager.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/11
 */
public interface OrderInfoService {

    boolean insertBatch(List<OrderInfo> list, String operator) throws ServiceException;

    boolean insert(OrderInfo orderInfo, String operator);

    boolean deleteById(long id);

    boolean updateById(OrderInfo orderInfo, String operator);

    List<OrderInfo> queryList(OrderInfoQuery query);

    int queryCount(OrderInfoQuery query);

    OrderInfo getById(Long id);
}
