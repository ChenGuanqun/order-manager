package order.manager.api;

import lombok.Data;
import order.manager.dao.order.OrderInfo;

/**
 * @author chentao01
 * @version 2018/2/28
 */
@Data
public class OrderInfoVO extends OrderInfo{
    String[] configArray;
}
