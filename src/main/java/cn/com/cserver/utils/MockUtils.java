package cn.com.cserver.utils;

/**
 * 获取模拟数据工具类
 */
public class MockUtils {
    private MockUtils(){
    }

    /**初始设备编号*/
    private static int INIT_CODE = 10000;
    /**初始指标编号*/
    private static int INIT_INDICATORS_CODE = 100;

    /**
     * 获取唯一设备编号
     * @return 唯一设备号
     */
    public static String getCode(){
        String code = "ZFY" + INIT_CODE++ ;
        return code;
    }
    /**
     * 获取唯一设备编号
     * @return 唯一设备号
     */
    public static String getsSyncCode(){
        String code = "ZFY" + (INIT_CODE - 1);
        return code;
    }

    /**
     * 获取指标编号
     * @return 指标编号
     */
    public static String getIndicatorsCode(){
        String indicatorsCode = "ZFY" + INIT_INDICATORS_CODE++;
        return indicatorsCode;
    }

    /**
     * 获取指标数据
     * @return 指标数据
     */
    public static int getIndicatorsValue(){
        return (int)(Math.random() * 100)+1;
    }

    /**
     * 获取当前时间戳(毫秒级)
     * @return 当前时间戳(毫秒级)
     */
    public static String getCurrentTimeMillis(){
        return String.valueOf(System.currentTimeMillis());
    }
}
