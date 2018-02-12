package order.manager.dao.user;


import order.manager.dao.abst.BaseDao;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
public interface UserInfoDAO extends BaseDao<UserInfo> {

    List<UserInfo> queryList(UserInfoQuery query);

    int queryCount(UserInfoQuery query);
}
