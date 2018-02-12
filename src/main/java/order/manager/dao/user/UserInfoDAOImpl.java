package order.manager.dao.user;


import order.manager.dao.abst.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/12/12
 */
@Component(value = "userInfoDAO")
public class UserInfoDAOImpl extends BaseDaoImpl<UserInfo> implements UserInfoDAO {

    @Override
    public List<UserInfo> queryList(UserInfoQuery query) {
        return getSqlSessionTemplate().selectList(getNameSpace() + "queryList", query);

    }

    @Override
    public int queryCount(UserInfoQuery query) {
        return getSqlSessionTemplate().selectOne(getNameSpace() + "queryCount", query);
    }
}
