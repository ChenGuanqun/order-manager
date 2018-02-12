package order.manager.dao.customer;

/**
 * @author chentao01
 * @version 2018/2/11
 */
public class CustomerQuery {
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
