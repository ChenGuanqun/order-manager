package order.manager.api;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import order.manager.constant.ApiResponse;
import order.manager.constant.ConfigMapManager;
import order.manager.constant.DataListResult;
import order.manager.constant.OrderConstants;
import order.manager.dao.order.OrderInfo;
import order.manager.dao.order.OrderInfoQuery;
import order.manager.dao.order.OrderInfoService;
import order.manager.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Log4j2
@Controller
@RequestMapping(value = "/api/order")
public class OrderInfoApi extends AbstractApi {

    private Gson gson = new Gson();

    @Resource
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> createOrder(@RequestBody OrderInfo orderInfo) throws ServiceException {
        boolean ret = orderInfoService.insert(orderInfo, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateOrder(@RequestBody OrderInfoVO orderInfoVO) throws Exception {
        String[] configArray = orderInfoVO.getConfigArray();
        setConfigDetail(orderInfoVO, configArray);
        if(orderInfoVO.getOrderDate() == null) {
            orderInfoVO.setOrderDate(-1L);
        }

        /**
         * 生产序号根据订单时间年度来递增，不能为空
         */
        Long orderDate = orderInfoVO.getOrderDate();
        Validate.isTrue(orderDate != null && orderDate > 0,
                "订单时间不能为空");
        OrderInfo oldOrderInfo = orderInfoService.getById(orderInfoVO.getId());
        Validate.isTrue(oldOrderInfo != null,"OrderInfo为空");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(orderDate));
        int year = c.get(Calendar.YEAR);
        Validate.isTrue(year == oldOrderInfo.getOrderYear(), "订单时间修改不能跨年度");

        if(orderInfoVO.getPlanDate() == null) {
            orderInfoVO.setPlanDate(-1L);
        }
        boolean ret = orderInfoService.updateById(orderInfoVO, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateOrderStatus(@RequestBody OrderInfoVO orderInfoVO) throws Exception {
        String[] configArray = orderInfoVO.getConfigArray();
        setConfigDetail(orderInfoVO, configArray);
        boolean ret = orderInfoService.updateById(orderInfoVO, getOperatorFromContext());
        return new ApiResponse<>(ret);
    }

    private void setConfigDetail(OrderInfoVO orderInfoVO, String[] configArray) throws NoSuchFieldException, IllegalAccessException {
        if(configArray != null) {
            orderInfoVO.resetInitConfig();
            for(String config : configArray) {
                String fieldName = ConfigMapManager.CONFIG_MAP_REVERSE.get(config);
                if(StringUtils.isNotEmpty(fieldName)) {
                    Field filed = orderInfoVO.getClass().getSuperclass().getDeclaredField(fieldName);
                    filed.setAccessible(true);
                    filed.set(orderInfoVO, OrderConstants.ORDER_STATUS_FINISHED);
                }
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> deleteOrder(@RequestBody OrderInfo orderInfo) throws ServiceException {
        Long id = orderInfo.getId();
        Validate.isTrue(id != null && id > 0, "id不合法");
        boolean ret = orderInfoService.deleteById(id);
        return new ApiResponse<>(ret);
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<DataListResult<OrderInfoVO>> queryOrder(@RequestBody OrderInfoQuery query) throws Exception {

        DataListResult<OrderInfoVO> result = new DataListResult<>();
        result.setCount((long) orderInfoService.queryCount(query));
        List<OrderInfo> orderInfoList = Lists.newArrayList();
        List<OrderInfoVO> voList = Lists.newArrayList();
        if (result.getCount() > 0) {
            List<OrderInfo> userInfos = orderInfoService.queryList(query);
            if (CollectionUtils.isNotEmpty(userInfos)) {
                orderInfoList = userInfos;
                for (OrderInfo orderInfo : orderInfoList) {

                    StringBuffer config = new StringBuffer();
                    config.append("[");
                    for(String filedName : ConfigMapManager.listConfig) {

                        Field filed = orderInfo.getClass().getDeclaredField(filedName);
                        filed.setAccessible(true);
                        Integer status = (Integer) filed.get(orderInfo);
                        if(status != null && status == OrderConstants.ORDER_STATUS_FINISHED) {
                            config.append("\"");
                            config.append(ConfigMapManager.CONFIG_MAP.get(filedName));
                            config.append("\"");
                            config.append(",");
                        }
                    }
                    if(config.length() > 1) {
                        config.deleteCharAt(config.length()-1);
                    }
                    config.append("]");

                    Type type = new TypeToken<String[]>() {
                    }.getType();
                    OrderInfoVO vo = DOZER_BEAN_MAPPER.map(orderInfo, OrderInfoVO.class);
                    vo.setConfigArray((String[])gson.fromJson(config.toString(), type));
                    voList.add(vo);
                }
            }
        }
        result.setData(voList);
        return new ApiResponse<>(result);
    }

}
