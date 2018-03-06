package order.manager.dao.order;

import lombok.Data;
import order.manager.dao.abst.BaseBean;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Data
public class OrderInfo extends BaseBean{
    private Long id;
    private String customerName;
    private Long productId;
    private String productName;
    private String productSeries;
    private Long number;
    private Long deliveryDate;
    private Long orderDate;
    private Long planDate;
    private Integer status;
    private Integer deliveryStatus;
    private String config;
    private Integer orderId;
    private Integer orderYear;
}
