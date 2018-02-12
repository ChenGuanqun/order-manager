package order.manager.api;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import order.manager.constant.ApiResponse;
import order.manager.constant.DataListResult;
import order.manager.dao.order.OrderInfo;
import order.manager.dao.order.OrderInfoQuery;
import order.manager.dao.order.OrderInfoService;
import order.manager.dao.user.UserInfo;
import order.manager.dao.user.UserInfoQuery;
import order.manager.dao.user.UserInfoService;
import order.manager.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/api/order")
public class OrderInfoApi extends AbstractApi{

    @Resource
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> createUser(OrderInfo orderInfo) throws ServiceException {
        boolean ret = orderInfoService.insert(orderInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateUser(OrderInfo orderInfo) throws ServiceException {
        boolean ret = orderInfoService.updateById(orderInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> deleteUser(Long id) throws ServiceException {
        Validate.isTrue(id != null && id > 0, "id不合法");
        boolean ret = orderInfoService.deleteById(id);
        return new ApiResponse<>(ret);
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<DataListResult<OrderInfo>> queryUser(OrderInfoQuery query) throws ServiceException {

        DataListResult<OrderInfo> result = new DataListResult<>();
        result.setCount((long) orderInfoService.queryCount(query));
        List<OrderInfo> orderInfoList = Lists.newArrayList();
        if (result.getCount() > 0) {
            List<OrderInfo> userInfos = orderInfoService.queryList(query);
            if (CollectionUtils.isNotEmpty(userInfos)) {
                orderInfoList = userInfos;
            }
        }
        result.setData(orderInfoList);
        return new ApiResponse<>(result);
    }

}
