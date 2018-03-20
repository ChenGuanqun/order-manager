package order.manager.api;

import lombok.Data;

/**
 * @author chentao01
 * @version 2018/3/20
 */
@Data
public class PwdRequest {
    private String newPwd;
    private String newAgain;
}
