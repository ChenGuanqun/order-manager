package order.manager.dao.role;


import order.manager.dao.abst.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
@Component(value = "roleInfoDAO")
public class RoleInfoDAOImpl extends BaseDaoImpl<RoleInfo> implements RoleInfoDAO {

    @Override
    public List<RoleInfo> queryList(RoleInfoQuery query) {
        return getSqlSessionTemplate().selectList(getNameSpace() + "queryList", query);

    }

    @Override
    public int queryCount(RoleInfoQuery query) {
        return getSqlSessionTemplate().selectOne(getNameSpace() + "queryCount", query);
    }

}
