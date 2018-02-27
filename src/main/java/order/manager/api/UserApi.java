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
import org.apache.commons.lang3.Validate;
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
    public ApiResponse<Boolean> deleteUser(Long id) throws ServiceException {
        Validate.isTrue(id != null && id > 0, "id不合法");
        boolean ret = userInfoService.deleteById(id);
        return new ApiResponse<>(ret);
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

}
