package cn.com.cserver.controller;

import cn.com.cserver.service.IGetService;
import cn.com.cserver.service.IPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/collect")
public class DataController {

    @Autowired
    private IPushService pushService;

    @Autowired
    private IGetService getService;

    /**
     * 设备编号
     */
    @Value("${equiCode}")
    private String equiCode;

    /**
     * 推送供暖数据
     * @return {startDate:开始推送供暖数据时间毫秒值,endDate:结束推送供暖数据时间毫秒值}
     * @throws Exception
     */
    @RequestMapping("/push")
    public Map<String,String> push() throws Exception {
        Map<String,String> result = new HashMap<String,String>();
        result.put("code",equiCode);
        result.put("startDate",String.valueOf(System.currentTimeMillis()));
        log.info("-------------------------开始执行推送数据操作-------------------------");
        pushService.startDevice(equiCode);
        pushService.pushData1(equiCode,"A0001");
        pushService.pushData2(equiCode,"A0002","A0003");
        pushService.stopDevice(equiCode);
        log.info("-------------------------结束执行推送数据操作-------------------------");
        result.put("endDate",String.valueOf(System.currentTimeMillis()+500));
        return result;
    }

    @RequestMapping("/get")
    public String get(@RequestParam("code") String code,
                      @RequestParam("startDate") String startDate,
                      @RequestParam("endDate") String endDate) throws Exception {
        log.info("-------------------------开始执行获取数据操作-------------------------");
        String result = getService.getDate(code, startDate, endDate);
        log.info("-------------------------结束执行获取数据操作-------------------------");
        return result;
    }
}
