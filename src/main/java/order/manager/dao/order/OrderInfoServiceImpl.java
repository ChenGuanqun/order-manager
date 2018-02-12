package order.manager.dao.order;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Component(value = "orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoDAO orderInfoDAO;

    @Override
    public boolean insert(OrderInfo orderInfo, String operator) {

        orderInfo.setStatus(1);
        orderInfo.setCreator(operator);
        orderInfo.setLastOperator(operator);
        orderInfo.setLastModifyTime(System.currentTimeMillis());
        orderInfo.setInsertTime(System.currentTimeMillis());
        return orderInfoDAO.insert(orderInfo);
    }

    @Override
    public boolean deleteById(long id) {
        return orderInfoDAO.deleteById(id);
    }

    @Override
    public boolean updateById(OrderInfo orderInfo, String operator) {
        orderInfo.setLastOperator(operator);
        orderInfo.setLastModifyTime(System.currentTimeMillis());
        return orderInfoDAO.updateById(orderInfo);
    }

    @Override
    public List<OrderInfo> queryList(OrderInfoQuery query) {
        return orderInfoDAO.queryList(query);
    }

    @Override
    public int queryCount(OrderInfoQuery query) {
        return orderInfoDAO.queryCount(query);
    }
}
