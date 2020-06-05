package cn.com.cserver.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GetServiceImpl implements IGetService{

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取推送供暖信息数据远程接口url
     */
    @Value("${url.getCollect}")
    private String GET_COLLECT_URL;

    @Override
    public String getDate(String code, String startDate, String endDate) throws Exception{
        String rmiUrl = GET_COLLECT_URL + "&code="+code + "&startDate=" +startDate
                + "&endDate=" + endDate;
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(rmiUrl, HttpMethod.GET,null,String.class);
        log.info("响应结果(获取推送数据):" + responseEntity.getBody());
        return responseEntity.getBody();
    }
}
