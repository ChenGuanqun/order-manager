package order.manager.api;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import order.manager.constant.ApiResponse;
import order.manager.constant.DataListResult;
import order.manager.dao.user.UserInfo;
import order.manager.dao.user.UserInfoQuery;
import order.manager.dao.user.UserInfoService;
import order.manager.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Log4j2
@Controller
@RequestMapping(value = "/api/user")
public class UserApi extends AbstractApi{

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> createUser(@RequestBody UserInfo userInfo) throws ServiceException {
        boolean ret = userInfoService.insert(userInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateUser(@RequestBody  UserInfo userInfo) throws ServiceException {
        boolean ret = userInfoService.updateById(userInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> deleteUser(@RequestBody UserInfo userInfo) throws ServiceException {
        Long id = userInfo.getId();
        Validate.isTrue(id != null && id > 0, "id不合法");
        boolean ret = userInfoService.deleteById(id);
        return new ApiResponse<>(ret);
    }
    @RequestMapping(value = "/queryCurrentUserName", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> getCurrentUserName() throws ServiceException {
        String username = getOperatorFromContext();
        return new ApiResponse<>(username);
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<DataListResult<UserInfo>> queryUser(@RequestBody UserInfoQuery query) throws ServiceException {

        DataListResult<UserInfo> result = new DataListResult<>();
        result.setCount((long) userInfoService.queryCount(query));
        List<UserInfo> userInfoList = Lists.newArrayList();
        if (result.getCount() > 0) {
            List<UserInfo> userInfos = userInfoService.queryList(query);
            if (CollectionUtils.isNotEmpty(userInfos)) {
                userInfoList = userInfos;
            }
        }
        result.setData(userInfoList);
        return new ApiResponse<>(result);
    }


    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateUser(@RequestBody PwdRequest request) throws ServiceException {
        Validate.isTrue(StringUtils.isNotBlank(request.getNewPwd())
                && StringUtils.isNotBlank(request.getNewAgain()) , "新密码不能为空!");
        Validate.isTrue(StringUtils.equals(request.getNewPwd(), request.getNewAgain()), "两次输入密码不一致!");
        UserInfo oldUser = userInfoService.getByName(getOperatorFromContext());
        Validate.isTrue(oldUser != null, "找不到当前用户信息，联系管理员!");

        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(request.getNewPwd());
        userInfo.setId(oldUser.getId());
        boolean ret = userInfoService.updateById(userInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }
}
