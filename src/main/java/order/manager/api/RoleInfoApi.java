package order.manager.api;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import order.manager.constant.ApiResponse;
import order.manager.constant.DataListResult;
import order.manager.dao.order.OrderInfo;
import order.manager.dao.order.OrderInfoQuery;
import order.manager.dao.role.RoleInfo;
import order.manager.dao.role.RoleInfoQuery;
import order.manager.dao.role.RoleInfoService;
import order.manager.exception.ServiceException;
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
}
