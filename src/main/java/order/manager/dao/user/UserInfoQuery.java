package order.manager.dao.user;

import lombok.Data;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Data
public class UserInfoQuery extends UserInfo{
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
