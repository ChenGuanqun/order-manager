package order.manager.dao.role;

import order.manager.dao.order.OrderInfo;
import order.manager.dao.user.UserInfo;
import order.manager.dao.user.UserInfoQuery;
import order.manager.dao.user.UserInfoService;
import order.manager.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
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

    @Resource
    UserInfoService userInfoService;

    @Override
    public boolean insert(RoleInfo roleInfo, String operator) throws ServiceException {

        Validate.isTrue(StringUtils.isNotBlank(roleInfo.getRoleName()), "角色名称不能为空!");
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
    public boolean deleteById(long id) throws ServiceException {
        RoleInfo roleInfo = getById(id);
        if(roleInfo == null) {
            throw new ServiceException("该权限未找到");
        }
        if("admin".equals(roleInfo.getRoleName())){
            throw new ServiceException("admin权限不能删除");
        }

        UserInfoQuery query = new UserInfoQuery();
        query.setRoleName(roleInfo.getRoleName());
        int count = userInfoService.queryCount(query);
        if(count > 0) {
            throw new ServiceException("该权限有用户正在使用，不能删除！");
        }

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
