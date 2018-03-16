package order.manager.api;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import order.manager.constant.ApiResponse;
import order.manager.constant.DataListResult;
import order.manager.dao.order.OrderInfo;
import order.manager.dao.order.OrderInfoQuery;
import order.manager.dao.role.RoleInfo;
import order.manager.dao.role.RoleInfoQuery;
import order.manager.dao.role.RoleInfoService;
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
 * @version 2018/3/16
 */
@Controller
@RequestMapping(value = "/api/role")
public class RoleInfoApi extends AbstractApi {

    private Gson gson = new Gson();

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RoleInfoService roleInfoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> createOrder(@RequestBody RoleInfo roleInfo) throws ServiceException {
        boolean ret = roleInfoService.insert(roleInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateOrder(@RequestBody RoleInfo roleInfo) throws ServiceException {
        boolean ret = roleInfoService.updateById(roleInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<DataListResult<RoleInfo>> queryOrder(@RequestBody RoleInfoQuery query) throws Exception {
        DataListResult<RoleInfo> result = new DataListResult<>();
        result.setCount((long) roleInfoService.queryCount(query));

        List<RoleInfo> data = Lists.newArrayList();
        if (result.getCount() > 0) {
            data = roleInfoService.queryList(query);
        }
        result.setData(data);
        return new ApiResponse<>(result);

    }


    @RequestMapping(value = "/getRole", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<RoleInfo> getRole() throws Exception {
        String userName = getOperatorFromContext();
        UserInfo userInfo = userInfoService.getByName(userName);
        RoleInfo result = null;
        if(userInfo != null) {
            RoleInfoQuery query = new RoleInfoQuery();
            query.setRoleName(userInfo.getRoleName());
            List<RoleInfo> roles = roleInfoService.queryList(query);
            if (CollectionUtils.isNotEmpty(roles)) {
                result = roles.get(0);
            } else {
                result = getRoleInfo();
            }
        } else {
            result = getRoleInfo();
        }

        return new ApiResponse<>(result);
    }

    private RoleInfo getRoleInfo() {
        RoleInfo result;
        result = new RoleInfo();
        result.setOrderId(true);
        result.setCustomerName(true);
        result.setProductName(true);
        result.setProductSeries(true);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> deleteUser(@RequestBody RoleInfo roleInfo) throws ServiceException {

        Long id = roleInfo.getId();
        Validate.isTrue(id != null && id > 0, "id不合法");
        boolean ret = roleInfoService.deleteById(id);
        return new ApiResponse<>(ret);
    }
}
