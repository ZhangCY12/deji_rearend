package org.example.dejimanage.config;

import org.example.dejimanage.tools.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//全局异常处理类
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);//日志对象
    //处理空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        // 返回响应实体
        logger.error("空指针异常: ",e.getMessage());
        Map<String,Object> map = new HashMap<>();
        map.put("空指针异常: ", e.getMessage());
        return Result.error().data(map);
    }
    // 处理数据库操作异常
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(SQLException e) {
        logger.error("SQL异常:",e.getMessage());
        // 返回响应实体
        Map<String,Object> map = new HashMap<>();
        map.put("SQL异常:", e.getMessage());
        return Result.error().data(map);
    }

    //非法参数异常
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e){
        logger.error("非法参数异常:",e.getMessage());
        // 返回响应实体
        Map<String,Object> map = new HashMap<>();
        map.put("非法参数异常:", e.getMessage());
        return Result.error().data(map);
    }

    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error("An error occurred:",e.getMessage());
        // 返回响应实体
        Map<String,Object> map = new HashMap<>();
        map.put("其他异常: ", e.getMessage());
        return Result.error().data(map);
    }

}
