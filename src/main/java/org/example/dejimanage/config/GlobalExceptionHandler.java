package org.example.dejimanage.config;

import org.example.dejimanage.tools.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//全局异常处理类
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public Result HandleNullPointerException(NullPointerException e) {
        // 返回响应实体
        Map<String,Object> map = new HashMap<>();
        map.put("Null Pointer Exception occurred: ", e.getMessage());
        return Result.error().data(map);
    }
    // 处理数据库操作异常
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(SQLException e) {
        // 返回响应实体
        Map<String,Object> map = new HashMap<>();
        map.put("Database error occurred:", e.getMessage());
        return Result.error().data(map);
    }
    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        // 返回响应实体
        Map<String,Object> map = new HashMap<>();
        map.put("An error occurred: ", e.getMessage());
        return Result.error().data(map);
    }
}
