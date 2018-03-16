package order.manager.dao.role;

import order.manager.dao.order.OrderInfo;
import order.manager.dao.order.OrderInfoQuery;
import order.manager.exception.ServiceException;

import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/11
 */
public interface RoleInfoService {

    boolean insert(RoleInfo roleInfo, String operator) throws ServiceException;

    boolean deleteById(long id);

    boolean updateById(RoleInfo roleInfo, String operator);

    List<RoleInfo> queryList(RoleInfoQuery query);

    int queryCount(RoleInfoQuery query);

    RoleInfo getById(Long id);
}
