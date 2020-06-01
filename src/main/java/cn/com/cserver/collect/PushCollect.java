package cn.com.cserver.collect;

import cn.com.cserver.utils.MockUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 推送供暖数据定时任务
 * @author ronin
 */
@Component
@Slf4j
public class PushCollect {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 推送供暖信息数据远程接口url
     */
    @Value("${url.pushCollect}")
    private String PUSH_COLLECT_URL;

    /**
     * 推送供暖信息数据定时任务
     */
    @Scheduled(cron = "0/20 * * * * MON-SAT")
    public void pushCollectScheduleTask() throws InterruptedException {
        //启动机器
        this.startDevice();
        //推送供暖数据
        this.pushCollect1();
        for(int i=0;i<2;i++) {
            this.pushCollect2("A0002");
            this.pushCollect2("A0003");
            Thread.sleep(5000);
        }

        //停止机器
        this.stopDevice();
    }

    /**
     * 启动设备
     * @return 启动设备返回值
     */
    public void startDevice(){
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Content-Type","text/json;charset=utf-8");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        //设备唯一编号
        params.add("code", "10019");
        params.add("runStatus","10");
        params.add("datetime","1579483469697");
        log.info("参数信息(启动设备):" + params.toString());
        //执行远程调用
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(PUSH_COLLECT_URL,HttpMethod.POST, httpEntity, String.class);
        log.info("响应结果(启动设备):" + responseEntity.toString());
    }

    /**
     * 推送供暖数据
     * @return 推送供暖数据返回值
     */
    public void pushCollect1() throws InterruptedException {
        for(int i=0;i<2;i++) {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Content-Type", "text/json;charset=utf-8");
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            //设备唯一编号
            params.add("code", "10019");
            //保存多个设备信息
            JSONArray devices = new JSONArray();
            //模拟设备状态信息
            JSONObject device = new JSONObject();
            //模拟指标编号
            //device.put("indicatorsCode",MockUtils.getIndicatorsCode());
            device.put("indicatorsCode", "A0001");
            device.put("value", MockUtils.getIndicatorsValue());
            device.put("datetime", MockUtils.getCurrentTimeMillis());
            devices.add(device);
            params.add("value", devices.toJSONString());
            log.info("参数信息(推送供暖数据A0001):" + params.toSingleValueMap());
            //执行远程调用
            HttpEntity httpEntity = new HttpEntity(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.
                    exchange(PUSH_COLLECT_URL, HttpMethod.POST, httpEntity, String.class);
            log.info("响应结果(推送供暖数据):" + responseEntity.toString());
            Thread.sleep(5000);
        }
    }

    /**
     * 推送供暖数据
     * @return 推送供暖数据返回值
     */
    public void pushCollect2(String Code) throws InterruptedException {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Content-Type", "text/json;charset=utf-8");
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            //设备唯一编号
            params.add("code",  "10019");
            //保存多个设备信息
            JSONArray devices = new JSONArray();
            //模拟设备状态信息
            JSONObject device = new JSONObject();
            //模拟指标编号
            //device.put("indicatorsCode",MockUtils.getIndicatorsCode());
            device.put("indicatorsCode", Code);
            device.put("value", MockUtils.getIndicatorsValue());
            device.put("datetime", MockUtils.getCurrentTimeMillis());
            devices.add(device);
            params.add("value", devices.toJSONString());
            log.info("参数信息(推送供暖数据"+Code+"):" + params.toSingleValueMap());
            //执行远程调用
            HttpEntity httpEntity = new HttpEntity(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.
                    exchange(PUSH_COLLECT_URL, HttpMethod.POST, httpEntity, String.class);
            log.info("响应结果(推送供暖数据):" + responseEntity.toString());

    }

    /**
     * 关闭设备
     * @return 关闭设备返回值
     */
    public void stopDevice(){
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Content-Type", "text/json;charset=utf-8");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        //设备唯一编号
        params.add("code", "10019");
        params.add("runStatus","20");
        params.add("datetime",MockUtils.getCurrentTimeMillis());
        log.info("参数信息(关闭设备):" + params.toSingleValueMap());
        //执行远程调用
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(PUSH_COLLECT_URL,HttpMethod.POST, httpEntity, String.class);
        log.info("响应结果(关闭设备):" + responseEntity.toString());
    }

}