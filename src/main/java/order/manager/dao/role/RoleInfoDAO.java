package order.manager.dao.role;


import order.manager.dao.abst.BaseDao;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
public interface RoleInfoDAO extends BaseDao<RoleInfo> {

    List<RoleInfo> queryList(RoleInfoQuery query);

    int queryCount(RoleInfoQuery query);

}
