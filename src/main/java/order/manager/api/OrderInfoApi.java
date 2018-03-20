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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> uploadOrder(MultipartFile file) throws ServiceException, IOException {

        List<OrderInfo> list = Lists.newArrayList();
        POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        if (rows <= 1) {
            throw new ServiceException("没有数据");
        }

        for (int r = 1; r < rows; r++) {
            HSSFRow row = sheet.getRow(r);
            try {
                if (!isEmptyRow(row)) {
                    OrderInfo orderInfo = parseData(r, row);
                    list.add(orderInfo);
                }
            } catch (ServiceException e) {
                throw e;
            } catch (Exception e) {
                String msg = String.format("第%s行发生错误，请检查格式再上传!msg:%s", r + 1, e.getMessage());
                throw new ServiceException(msg);
            }

        }
        boolean isInsertSuccess = orderInfoService.insertBatch(list, getOperatorFromContext());
        return new ApiResponse<>(isInsertSuccess);
    }


    private OrderInfo parseData(int r, HSSFRow row) throws ServiceException {
        OrderInfo orderInfo = new OrderInfo();
        if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(0).getCellType() != Cell.CELL_TYPE_STRING) {
                throw new ServiceException(String.format("第%s行第1列错误，客户名称必须为文字格式！", r + 1));
            }
            orderInfo.setCustomerName(row.getCell(0).getStringCellValue());
        }

        if (row.getCell(1) != null && row.getCell(1).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(1).getCellType() != Cell.CELL_TYPE_STRING) {
                throw new ServiceException(String.format("第%s行第2列错误，产品名称必须为文字格式！", r + 1));
            }
            orderInfo.setProductName(row.getCell(1).getStringCellValue());
        }

        if (row.getCell(2) != null && row.getCell(2).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(2).getCellType() != Cell.CELL_TYPE_STRING) {
                throw new ServiceException(String.format("第%s行第3列错误，产品型号必须为文字格式！", r + 1));
            }
            orderInfo.setProductSeries(row.getCell(2).getStringCellValue());
        }

        if (row.getCell(3) != null && row.getCell(3).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(3).getCellType() != Cell.CELL_TYPE_STRING) {
                throw new ServiceException(String.format("第%s行第4列错误，产品要求必须为文字格式！", r + 1));
            }
            orderInfo.setDescription(row.getCell(3).getStringCellValue());
        }

        if (row.getCell(4) != null && row.getCell(4).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(4).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new ServiceException(String.format("第%s行第5列错误，数量必须为数字格式！", r + 1));
            }
            orderInfo.setNumber((long) row.getCell(4).getNumericCellValue());
        }

        if (row.getCell(5) != null && row.getCell(5).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(5).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new ServiceException(String.format("第%s行第6列错误，时间格式错误!正确格式:2018/1/1！", r + 1));
            }
            Date deDate = row.getCell(5).getDateCellValue();
            if (deDate != null) {
                orderInfo.setDeliveryDate(deDate.getTime());
            }
        }

        if (row.getCell(6) == null ||
                row.getCell(6).getCellType() == Cell.CELL_TYPE_BLANK ||
                row.getCell(6).getCellType() != Cell.CELL_TYPE_NUMERIC ||
                row.getCell(6).getDateCellValue() == null) {
            throw new ServiceException(String.format("第%s行错误，上传时必须确定订单日期，由此确定年度！", r + 1));
        }
        orderInfo.setOrderDate(row.getCell(6).getDateCellValue().getTime());

        if (row.getCell(7) != null && row.getCell(7).getCellType() != Cell.CELL_TYPE_BLANK) {
            if (row.getCell(7).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new ServiceException(String.format("第%s行第8列错误，时间格式错误!正确格式:2018/1/1！", r + 1));
            }
            Date planDate = row.getCell(7).getDateCellValue();
            if (planDate != null) {
                orderInfo.setPlanDate(planDate.getTime());
            }
        }
        return orderInfo;
    }

    private boolean isEmptyRow(HSSFRow row) {
        if (row == null) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            HSSFCell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }

            int cellType = cell.getCellType();
            switch (cellType) {
                case Cell.CELL_TYPE_NUMERIC:
                    return false;
                case Cell.CELL_TYPE_STRING:
                    if (StringUtils.isNotBlank(cell.getStringCellValue())) {
                        return false;
                    }
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return true;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Boolean> updateOrder(@RequestBody OrderInfoVO orderInfoVO) throws Exception {
        String[] configArray = orderInfoVO.getConfigArray();
        setConfigDetail(orderInfoVO, configArray);
        if (orderInfoVO.getOrderDate() == null) {
            orderInfoVO.setOrderDate(-1L);
        }

        /**
         * 生产序号根据订单时间年度来递增，不能为空
         */
        Long orderDate = orderInfoVO.getOrderDate();
        Validate.isTrue(orderDate != null && orderDate > 0,
                "订单时间不能为空");
        OrderInfo oldOrderInfo = orderInfoService.getById(orderInfoVO.getId());
        Validate.isTrue(oldOrderInfo != null, "OrderInfo为空");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(orderDate));
        int year = c.get(Calendar.YEAR);
        Validate.isTrue(year == oldOrderInfo.getOrderYear(), "订单时间修改不能跨年度");

        if (orderInfoVO.getPlanDate() == null) {
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
        if (configArray != null) {
            orderInfoVO.resetInitConfig();
            for (String config : configArray) {
                String fieldName = ConfigMapManager.CONFIG_MAP_REVERSE.get(config);
                if (StringUtils.isNotEmpty(fieldName)) {
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
                    for (String filedName : ConfigMapManager.listConfig) {

                        Field filed = orderInfo.getClass().getDeclaredField(filedName);
                        filed.setAccessible(true);
                        Integer status = (Integer) filed.get(orderInfo);
                        if (status != null && status == OrderConstants.ORDER_STATUS_FINISHED) {
                            config.append("\"");
                            config.append(ConfigMapManager.CONFIG_MAP.get(filedName));
                            config.append("\"");
                            config.append(",");
                        }
                    }
                    if (config.length() > 1) {
                        config.deleteCharAt(config.length() - 1);
                    }
                    config.append("]");

                    Type type = new TypeToken<String[]>() {
                    }.getType();
                    OrderInfoVO vo = DOZER_BEAN_MAPPER.map(orderInfo, OrderInfoVO.class);
                    vo.setConfigArray((String[]) gson.fromJson(config.toString(), type));
                    voList.add(vo);
                }
            }
        }
        result.setData(voList);
        return new ApiResponse<>(result);
    }

    @RequestMapping(value = "/getTemplate", method = RequestMethod.GET)
    @ResponseBody
    public void getTemplate(HttpServletResponse response) throws IOException, URISyntaxException {
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();// 清空输出流
        String fileName = "upload.xls";

        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1"));// 设定输出文件头
        response.setContentType("application/vnd.ms-excel");// 定义输出类型

        URL url = this.getClass().getClassLoader().getResource(fileName);

        File xls = new File(url.toURI());
        FileInputStream in = new FileInputStream(xls);
        byte[] buffer = new byte[8192]; // use bigger if you want
        int length = 0;

        while ((length = in.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        in.close();
        os.close();
    }

}
