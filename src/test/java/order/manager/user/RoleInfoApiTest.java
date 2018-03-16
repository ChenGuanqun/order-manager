package order.manager.user;

import lombok.extern.log4j.Log4j2;
import order.manager.util.HttpClient4Utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chentao01
 * @version 2018/3/16
 */
@Log4j2
public class RoleInfoApiTest extends AbstractUnitTest{

    private String url =  "http://localhost:8080/api/role";

    @Test
    public void insertOrder(){
        Map<String, String> params = new HashMap<>();
        params.put("roleName", "developer");
        params.put("orderId", "true");
        params.put("productName", "true");
        params.put("productSeries", "true");
        String response = HttpClient4Utils.sendPost(localHttpClient,url + "/create", params, null);
        log.info(response);
    }

    @Test
    public void queryTest() {
        String response = HttpClient4Utils.sendPost(localHttpClient,url + "/query", null, null);
        log.info(response);
    }
}
