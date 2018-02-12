package order.manager.dao.customer;

import lombok.Data;
import order.manager.dao.abst.BaseBean;

/**
 * @author chentao01
 * @version 2018/2/11
 */
@Data
public class Customer extends BaseBean{
    private Long id;
    private String customerName;
}
