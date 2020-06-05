package cn.com.cserver.service;

public interface IGetService {
    /**
     * 获取已经推送的数据
     * @param code 设备唯一标识符
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    String getDate(String code,String startDate,String endDate) throws Exception;
}
