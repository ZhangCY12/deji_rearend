package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.jna.platform.win32.Guid;
import lombok.Data;

//cnc机台实体类
@Data
@TableName("t_cnc_run")
public class CNCs {
    @TableId("ID")
    private Guid ID; // id
    public String cncNum;// 序号
    public String cncNo;// cnc编号
    public String onlineStatus;//是否在线
    public String cncStatus;//状态
    public String cncModel;//模式
    public String cncAlarm;//报警
    public String speedMain;//主轴速度
    public String speedCut;//切削速度
    public String artifactsAll;// 工件总数
    public String artifacts;// 单次加工数
    public String xAbsolute;//X轴绝对位置
    public String yAbsolute;//y轴绝对位置
    public String zAbsolute;// z轴绝对位置
    public String xRelative;// x轴相对位置
    public String yRelative;// y轴相对位置
    public String zRelative;// z轴相对位置
    public String cncType;// cnc类型
    public String mtType;// MT类型
    public String serialnumber;// 系列型号
    public String version;// 版本
    public String axis;// 轴
    public String cutTime;// 切削时间
    public String manpg;// 主程序号
    public String currentpg;// 当前程序号
    public String cycsec;// 运行时间
    public String poweontime;// 开机时间
    public String pgname;// 运行程序信息
    public String maxgrp;// 最大刀具数组
    public String toolChanges;// 换刀次数
    public String usingTool;//正在使用刀具
    public String lastCycle;// 最后一次循环时间
    public String prevCycle;// 上一次循环时间
    public String onceTime;// 单词时间
    public String ip;// IP地址
    public String createTime;// 创建时间
    public String commucation;// 通讯方式
}
