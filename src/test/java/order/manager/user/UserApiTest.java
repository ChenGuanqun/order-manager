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
public class UserApiTest extends AbstractUnitTest{

    private String url =  "http://localhost:8080/api/user";
    @Test
    public void insertUser(){
        Map<String, String> params = new HashMap<>();
        params.put("userName", "chentao");
        String response = HttpClient4Utils.sendPost(localHttpClient,url + "/create", params, null);
        log.info(response);
    }

    @Test
    public void queryTest() {
        Map<String, String> params = new HashMap<>();
        params.put("userName", "chentao");
        String response = HttpClient4Utils.sendPost(localHttpClient,url + "/query", null, null);
        log.info(response);
    }
}
