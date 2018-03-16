package order.manager.dao.role;

import order.manager.dao.order.OrderInfo;
import order.manager.exception.ServiceException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chentao01
 * @version 2018/3/16
 */
@Component
public class RoleInfoServiceImpl implements RoleInfoService{

    @Resource
    RoleInfoDAO roleInfoDAO;

    @Override
    public boolean insert(RoleInfo roleInfo, String operator) throws ServiceException {
        roleInfo.setCreator(operator);
        roleInfo.setLastOperator(operator);
        roleInfo.setLastModifyTime(System.currentTimeMillis());
        roleInfo.setInsertTime(System.currentTimeMillis());
        boolean isInsertSuccess = false;
        try{
            isInsertSuccess = roleInfoDAO.insert(roleInfo);
        } catch (DuplicateKeyException e) {
            throw new ServiceException("权限名称已存在");
        }

        return isInsertSuccess;
    }

    @Override
    public boolean deleteById(long id) {
        return roleInfoDAO.deleteById(id);
    }

    @Override
    public boolean updateById(RoleInfo roleInfo, String operator) {
        roleInfo.setLastOperator(operator);
        roleInfo.setLastModifyTime(System.currentTimeMillis());
        return roleInfoDAO.updateById(roleInfo);
    }

    @Override
    public List<RoleInfo> queryList(RoleInfoQuery query) {
        return roleInfoDAO.queryList(query);
    }

    @Override
    public int queryCount(RoleInfoQuery query) {
        return roleInfoDAO.queryCount(query);
    }

    @Override
    public RoleInfo getById(Long id) {
        return roleInfoDAO.getById(id);
    }
}
