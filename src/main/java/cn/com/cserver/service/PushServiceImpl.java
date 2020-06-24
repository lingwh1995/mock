package cn.com.cserver.service;

import cn.com.cserver.utils.MockUtils;
import com.alibaba.fastjson.JSONArray;
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
public class PushServiceImpl implements IPushService {

    /**
     * 推送供暖信息数据远程接口url
     */
    @Value("${url.pushCollect}")
    private String PUSH_COLLECT_URL;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 启动设备
     * @param equiCode 设备编号
     * @return 启动设备返回值
     */
    @Override
    public void startDevice(String equiCode) throws Exception{
        Map<String, String> params = new HashMap<String,String>();
        //设备唯一编号
        params.put("code", equiCode);
        params.put("runStatus","10");
        params.put("datetime",String.valueOf(System.currentTimeMillis()));
        log.info("参数信息(第一步:启动设备):" + JSONObject.toJSONString(params));
        //执行远程调用
        HttpEntity httpEntity = new HttpEntity(params);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(PUSH_COLLECT_URL, HttpMethod.POST,httpEntity,String.class);
        log.info("响应结果(第一步:启动设备):" + responseEntity.getBody());
    }

    /**
     * 推送供暖数据
     * @param equiCode 设备编号
     * @return 推送供暖数据返回值
     */
    @Override
    public void pushData1(String equiCode) throws Exception {
        for(int i=0;i<2;i++) {
            Map<String, Object> params = new HashMap<String,Object>();
            //设备唯一编号
            params.put("code", equiCode);
            //保存多个设备信息
            JSONArray devices = new JSONArray();
            //模拟设备状态信息
            JSONObject device = new JSONObject();
            device.put("indicatorsCode", "A0001");
            device.put("value", MockUtils.getIndicatorsValue());
            device.put("datetime", MockUtils.getCurrentTimeMillis());
            devices.add(device);
            params.put("value", devices);
            log.info("参数信息(推送供暖数据):" + JSONObject.toJSONString(params));
            //执行远程调用
            HttpEntity httpEntity = new HttpEntity(params);
            ResponseEntity<String> responseEntity = restTemplate.
                    exchange(PUSH_COLLECT_URL, HttpMethod.POST, httpEntity, String.class);
            log.info("响应结果(推送供暖数据):" + responseEntity.getBody());
            Thread.sleep(5000);
        }
    }

    /**
     * 推送供暖数据
     * @param equiCode 设备编号
     * @return 推送供暖数据返回值
     */
    @Override
    public void pushData2(String equiCode,String code1,String code2) throws Exception {
        for(int i=0;i<2;i++) {
            Map<String, Object> params = new HashMap<String, Object>();
            //设备唯一编号
            params.put("code", equiCode);
            //保存多个设备信息
            JSONArray devices = new JSONArray();
            //A0002
            JSONObject device1 = new JSONObject();
            device1.put("indicatorsCode", code1);
            device1.put("value", MockUtils.getIndicatorsValue());
            device1.put("datetime", MockUtils.getCurrentTimeMillis());
            devices.add(device1);
            //A0003
            JSONObject device2 = new JSONObject();
            device2.put("indicatorsCode", code2);
            device2.put("value", MockUtils.getIndicatorsValue());
            device2.put("datetime", MockUtils.getCurrentTimeMillis());
            devices.add(device2);
            params.put("value", devices);
            log.info("参数信息(推送供暖数据):" + JSONObject.toJSONString(params));
            //执行远程调用
            HttpEntity httpEntity = new HttpEntity(params);
            ResponseEntity<String> responseEntity = restTemplate.
                    exchange(PUSH_COLLECT_URL, HttpMethod.POST, httpEntity, String.class);
            log.info("响应结果(推送供暖数据):" + responseEntity.getBody());
        }
    }

    /**
     * 关闭设备
     * @param equiCode 设备编号
     * @return 关闭设备返回值
     */
    @Override
    public void stopDevice(String equiCode) throws Exception{
        Map<String, String> params = new HashMap<String,String>();
        //设备唯一编号
        params.put("code", equiCode);
        params.put("runStatus","20");
        params.put("datetime",MockUtils.getCurrentTimeMillis());
        log.info("参数信息(关闭设备):" + JSONObject.toJSONString(params));
        //执行远程调用
        HttpEntity httpEntity = new HttpEntity(params);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(PUSH_COLLECT_URL,HttpMethod.POST, httpEntity, String.class);
        log.info("响应结果(关闭设备):" + responseEntity.getBody());
    }
}
