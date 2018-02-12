package order.manager.constant;

import lombok.Data;

import java.util.List;

/**
 * @author chentao01
 * @version 2017/11/13
 */
@Data
public class DataListResult<T> {
    private Long count;
    private List<T> data;
}
