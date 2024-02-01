package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_liteweb_project")
public class ProjectOrder implements Serializable {
    @TableId("projectCode")
    public String projectCode; // 工单编号
    public String status; // 工单状态 枚举备注: 0 :未开始 10 :执行中 20 :已结束
    public String productcode; // 产品编号
    public String productname; // 产品名称
    public String productspecification; // 产品规格
    public String planamount; // 计划数
    public String projectunit; // 单位
    public String actualstarttime; // 实际开始时间
    public String actualendtime; // 实际结束时间
    public String numberofgoodproducts; // 良品数e
    public String numberofdefectives; // 不良品数
    public String createtime; // 创建时间
    public String updatetime; // 更新时间
    public String actualamount; // 实际数
    public String planstarttime; // 计划开始时间
    public String planendtime; // 计划结束时间
    public String creator; // 创建人
    public String updator; // 修改人
    public String remark; // 备注
    public String qrcode; // 工单二维码对应的文本，例如："projectId=70330"
    public String formcode; // 关联单据编号，不存在时返回""
    public String externalid; // 关联外部id
}
