package cn.com.cserver.service;

public interface IPushService {
    /**
     * 启动设备
     * @param equiCode 设备编号
     */
    void startDevice (String equiCode) throws Exception;

    /**
     * 推送单条数据
     * @param equiCode 设备编号
     *  @param code1 指标编码1
     */
    void pushData1(String equiCode,String code1) throws Exception;

    /**
     * 推送多条数据
     * @param equiCode 设备编号
     * @param code1 指标编码1
     * @param code2 指标编码2
     */
    void pushData2(String equiCode,String code1,String code2) throws Exception;

    /**
     * 停止设备
     * @param equiCode 设备编号
     */
    void stopDevice(String equiCode) throws Exception;
}
