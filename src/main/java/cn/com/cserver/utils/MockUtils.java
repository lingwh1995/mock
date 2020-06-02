package cn.com.cserver.utils;

import lombok.experimental.UtilityClass;

/**
 * 获取模拟数据工具类
 */
@UtilityClass
public class MockUtils {

    /**
     * 获取指标数据
     * @return 指标数据
     */
    public int getIndicatorsValue(){
        return (int)(Math.random() * 100)+1;
    }

    /**
     * 获取当前时间戳(毫秒级)
     * @return 当前时间戳(毫秒级)
     */
    public String getCurrentTimeMillis(){
        return String.valueOf(System.currentTimeMillis());
    }
}
