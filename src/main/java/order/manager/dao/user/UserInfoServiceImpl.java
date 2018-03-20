package order.manager.dao.user;

import order.manager.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static order.manager.constant.OrderConstants.USER_STATUS_ENABLE;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Component(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    @Resource
    private UserInfoDAO userInfoDAO;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean insert(UserInfo userInfo, String operator) throws ServiceException {
        Validate.notEmpty(userInfo.getUserName(), "用户名为空");
        userInfo.setPassword(bCryptPasswordEncoder.encode("123456"));
        userInfo.setStatus(USER_STATUS_ENABLE);
        userInfo.setCreator(operator);
        userInfo.setLastOperator(operator);
        userInfo.setLastModifyTime(System.currentTimeMillis());
        userInfo.setInsertTime(System.currentTimeMillis());
        boolean isInsertSuccess = false;

        try {
            isInsertSuccess =  userInfoDAO.insert(userInfo);
        } catch (DuplicateKeyException e) {
            throw new ServiceException("用户名已存在");
        }

        return isInsertSuccess;
    }

    @Override
    public boolean deleteById(long id) {
        return userInfoDAO.deleteById(id);
    }

    @Override
    public boolean updateById(UserInfo userInfo, String operator) {
        if(StringUtils.isNotBlank(userInfo.getPassword())) {
            userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        }

        userInfo.setLastOperator(operator);
        userInfo.setLastModifyTime(System.currentTimeMillis());
        return userInfoDAO.updateById(userInfo);
    }

    @Override
    public List<UserInfo> queryList(UserInfoQuery query) {
        return userInfoDAO.queryList(query);
    }

    @Override
    public int queryCount(UserInfoQuery query) {
        return userInfoDAO.queryCount(query);
    }

    @Override
    public UserInfo getByName(String userName) {
        return userInfoDAO.getByName(userName);
    }
}
