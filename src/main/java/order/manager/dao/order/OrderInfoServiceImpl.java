package order.manager.dao.order;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static order.manager.constant.OrderConstants.ORDER_STATUS_INIT;


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

        if(orderInfo.getOrderDate() == null) {
            orderInfo.setOrderDate(-1L);
        }

        /**
         * 生产序号根据订单时间年度来递增，不能为空
         */
        Long orderDate = orderInfo.getOrderDate();
        Validate.isTrue(orderDate != null && orderDate > 0,
                "订单时间不能为空");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(orderDate));
        int year = c.get(Calendar.YEAR);
        orderInfo.setOrderYear(year);

        Integer currentYearMaxId = orderInfoDAO.getCurrentYearMaxId(year);
        if(currentYearMaxId == null) {
            orderInfo.setOrderId(1);
        } else {
            orderInfo.setOrderId(currentYearMaxId + 1);
        }


        if(orderInfo.getPlanDate() == null) {
            orderInfo.setPlanDate(-1L);
        }
        orderInfo.setConfig("[]");
        orderInfo.setStatus(ORDER_STATUS_INIT);
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

    @Override
    public OrderInfo getById(Long id) {
        return orderInfoDAO.getById(id);
    }
}
