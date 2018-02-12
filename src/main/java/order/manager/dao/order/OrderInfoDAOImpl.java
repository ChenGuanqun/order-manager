package order.manager.dao.order;


import order.manager.dao.abst.BaseDaoImpl;
import order.manager.dao.customer.Customer;
import order.manager.dao.customer.CustomerDAO;
import order.manager.dao.customer.CustomerQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
@Component(value = "orderInfoDAO")
public class OrderInfoDAOImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDAO {

    @Override
    public List<OrderInfo> queryList(OrderInfoQuery query) {
        return getSqlSessionTemplate().selectList(getNameSpace() + "queryList", query);

    }

    @Override
    public int queryCount(OrderInfoQuery query) {
        return getSqlSessionTemplate().selectOne(getNameSpace() + "queryCount", query);
    }
}
