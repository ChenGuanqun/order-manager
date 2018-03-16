package order.manager.dao.role;

import lombok.Data;

/**
 * @author chentao01
 * @version 2018/3/16
 */
@Data
public class RoleInfoQuery extends RoleInfo{

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
