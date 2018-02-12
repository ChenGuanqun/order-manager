package order.manager.user;

import lombok.Getter;
import order.manager.util.Config;
import order.manager.util.HttpClient4Utils;
import org.apache.http.client.HttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chentao01
 * @version 2018/2/12
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application-context-management.xml"})
public abstract class AbstractUnitTest {
    @Getter
    protected HttpClient localHttpClient = HttpClient4Utils
            .createHttpClient(Config.getConfig().getMaxTotal(), Config.getConfig().getMaxPerRoute(),
                    Config.getConfig().getSocketTimeout(), Config.getConfig().getConnectTimeout(),
                    Config.getConfig().getConnectionRequestTimeout());
    protected static final Logger logger = LogManager.getLogger();

}
