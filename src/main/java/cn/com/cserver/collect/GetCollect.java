package cn.com.cserver.collect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class GetCollect {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取推送的供暖信息数据远程接口url
     */
    @Value("${url.getCollect}")
    private String GET_COLLECT_URL;

    /**
     * 获取推送的供暖信息数据
     * @param code 唯一设备标识符
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 请求响应结果
     */
    public ResponseEntity<String> getCollect(String code,long startDate,long endDate){
        HttpHeaders headers = new HttpHeaders();
        //headers.set("phone", "1234567");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        //设备唯一编号
        params.add("code", code);
        params.add("startDate",String.valueOf(startDate));
        params.add("endDate",String.valueOf(endDate));
        log.info("参数信息(获取推送的供暖信息数据):" + params.toSingleValueMap());
        //执行远程调用
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(GET_COLLECT_URL, HttpMethod.GET,httpEntity, String.class);
        log.info("响应结果(获取推送的供暖数据):" + responseEntity.toString());
        return responseEntity;
    }
}
