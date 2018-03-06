package order.manager.dao.order;

import lombok.Data;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Data
public class OrderInfoQuery extends OrderInfo{

    private long startTime = -1;

    private long endTime = -1;

    private long startOrderTime = -1;

    private long endOrderTime = -1;

    private int pageNum = 1;

    private int pageSize = 20;

    private int limit;

    private int offset;

    public int getLimit() {
        return pageSize;
    }

    public int getOffset() {
        return pageSize * (pageNum - 1);
    }
}
