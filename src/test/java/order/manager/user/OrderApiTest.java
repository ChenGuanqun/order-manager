package order.manager.user;

import lombok.extern.log4j.Log4j2;
import order.manager.util.HttpClient4Utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @author chentao01
 * @version 2018/2/12
 */
@Log4j2
public class OrderApiTest extends AbstractUnitTest{

    private String url =  "http://localhost:8080/api/order";
    @Test
    public void insertOrder(){
        Map<String, String> params = new HashMap<>();
        params.put("productId", "12");
        params.put("productName", "qwe");
        params.put("productSeries", "qwe");
        String response = HttpClient4Utils.sendPost(localHttpClient,url + "/create", params, null);
        log.info(response);
    }

    @Test
    public void queryTest() {
        String response = HttpClient4Utils.sendPost(localHttpClient,url + "/query", null, null);
        log.info(response);
    }
}
