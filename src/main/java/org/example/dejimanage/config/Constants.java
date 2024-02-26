package org.example.dejimanage.config;

//全局常量配置类
public class Constants {
    // 私有构造函数，防止实例化
    private Constants() {
        throw new AssertionError("Cannot instantiate constant utility class");
    }
    public static final int PROJECT_TIME = 10*60*1000; //查询2024年以来工单总信息的缓存存在时间
    public static final String CNC_DAILY_OPERATION_TIME = "0 59 23 * * ?";//每日稼动率和运行时间存储时间
    public static final String CNC_DAILY_OPERATION_白 = "0 59 19 * * ?";//白班稼动率统计时间
    public static final String CNC_DAILY_OPERATION_夜 = "0 59 7 * * ?";//夜班稼动率统计时间
}
