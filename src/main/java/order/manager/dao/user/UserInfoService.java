package order.manager.dao.user;

import order.manager.dao.customer.Customer;
import order.manager.dao.customer.CustomerQuery;
import order.manager.exception.ServiceException;

import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/11
 */
public interface UserInfoService {

    boolean insert(UserInfo userInfo, String operator) throws ServiceException;

    boolean deleteById(long id);

    boolean updateById(UserInfo userInfo, String operator);

    List<UserInfo> queryList(UserInfoQuery query);

    int queryCount(UserInfoQuery query);

    UserInfo getByName(String userName);
}
