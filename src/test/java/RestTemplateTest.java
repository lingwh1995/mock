import cn.com.cserver.Application;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用RestTemplate完成远程调用
 * @author ronin
 * @version V1.0
 * @since 2019/11/15 13:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 推送供暖信息数据远程接口url
     */
    @Value("${url.pushCollect}")
    private String PUSH_COLLECT_URL;

    @Test
    public void fun1(){
            Map<String, String> params = new HashMap<String,String>();
            //设备唯一编号
            params.put("code", "10019");
            params.put("runStatus", "20");
            params.put("datetime", String.valueOf(System.currentTimeMillis()));
            log.info("参数信息(启动设备):" + params.toString());
            //执行远程调用
            HttpEntity httpEntity = new HttpEntity(params);
            ResponseEntity<String> responseEntity = restTemplate.
                    exchange(PUSH_COLLECT_URL, HttpMethod.POST, httpEntity, String.class);
            log.info("响应结果(启动设备):" + responseEntity.toString());
    }
}