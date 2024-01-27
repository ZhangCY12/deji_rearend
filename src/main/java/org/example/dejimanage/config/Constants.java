package org.example.dejimanage.config;

//常量配置类
public class Constants {
    // 私有构造函数，防止实例化
    private Constants() {
        throw new AssertionError("Cannot instantiate constant utility class");
    }
    public static final int PROJECT_TIME = 10*60*1000; //查询2024年以来工单总信息的缓存存在时间
}
