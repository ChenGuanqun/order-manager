package order.manager.dao.role;

import lombok.Data;
import order.manager.dao.abst.BaseBean;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Data
public class RoleInfo extends BaseBean {
    private Long id;
    private String roleName;
    private boolean customerName;
    private boolean productName;
    private boolean productSeries;
    private boolean number;
    private boolean deliveryDate;
    private boolean orderDate;
    private boolean planDate;
    private boolean status;
    private boolean deliveryStatus;
    private boolean config;
    private boolean orderId;
    private boolean operate;
    private boolean userRole;
    private boolean productRequires;
}
