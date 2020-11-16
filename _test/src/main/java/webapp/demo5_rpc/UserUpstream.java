package webapp.demo5_rpc;

import org.noear.solon.annotation.Bean;
import org.noear.solon.core.Upstream;

/**
 * 定义一个负载器（可以对接发现服务）
 * */
@Bean("local")
public class UserUpstream implements Upstream {
    @Override
    public String getServer() {
        return "http://localhost:8080";
    }
}
