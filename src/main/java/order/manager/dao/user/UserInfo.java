package order.manager.dao.user;

import lombok.Data;
import order.manager.dao.abst.BaseBean;

/**
 * @author chentao01
 * @version 2018/2/11
 */
@Data
public class UserInfo extends BaseBean{
    private Long id;
    private String userName;
    private String password;
    private Integer status;
    private Integer securityRole;
}
