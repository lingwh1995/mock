package cn.com.cserver.controller;

import cn.com.cserver.service.IPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/collect")
public class DataController {

    @Autowired
    private IPushService pushService;

    /**
     * 推送数据
     * @throws Exception
     */
    @RequestMapping("/push")
    public String push(Map<String,String> map) throws Exception {
        log.info("-------------------------开始执行推送数据操作-------------------------");
        pushService.startDevice();
        pushService.pushData1();
        pushService.pushData2("A0002","A0003");
        pushService.stopDevice();
        log.info("-------------------------结束执行推送数据操作-------------------------");
        //return "redirect:/index";
        return "redirect:/https:www.baidu.com";
    }
}
