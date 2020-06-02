package cn.com.cserver.service;

public interface IPushService {
    /**
     * 启动设备
     */
    void startDevice();

    /**
     * 推送单条数据
     */
    void pushData1() throws Exception;

    /**
     * 推送多条数据
     * @param code1 指标编码1
     * @param code2 指标编码2
     */
    void pushData2(String code1,String code2) throws Exception;

    /**
     * 停止设备
     */
    void stopDevice();
}
