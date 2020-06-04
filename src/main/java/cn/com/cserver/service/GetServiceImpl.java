package cn.com.cserver.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public String getDate(String code, String startDate, String endDate) {
        Map<String, String> params = new HashMap<String,String>();
        //设备唯一编号
        params.put("code", "10019");
        params.put("runStatus","10");
        params.put("datetime",String.valueOf(System.currentTimeMillis()));
        log.info("参数信息(启动设备):" + JSONObject.toJSONString(params));
        //执行远程调用
        HttpEntity httpEntity = new HttpEntity(params);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(PUSH_COLLECT_URL, HttpMethod.POST,httpEntity,String.class);
        log.info("响应结果(启动设备):" + responseEntity.getBody());
        return null;
    }
}
