package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_liteweb_project")
public class Project {
    @TableId("projectCode")
    public String projectCode;// 工单编号
    public String status;// 工单状态 枚举备注: 0 :未开始 10 :执行中 20 :已结束
    public String productCode;// 产品编号
    public String productName;// 产品名称
    public String productSpecification;// 产品规格
    public String planAmount;// 计划数
    public String projectUnit;// 单位
    public String actualStartTime;// 实际开始时间
    public String actualEndTime;// 实际结束时间
    public String numberofGoodProducts;// 良品数e
    public String numberofDefectives;// 不良品数
    public String createTime;// 创建时间
    public String updateTime;// 更新时间
    public String actualAmount;// 实际数
    public String planStartTime;// 计划开始时间
    public String planEndTime;// 计划结束时间
    public String creator;// 创建人
    public String updator;// 修改人
    public String remark;// 备注
    public String qrCode;// 工单二维码对应的文本，例如："projectId=70330"
    public String formCode;// 关联单据编号，不存在时返回""
    public String externalId;// 关联外部id
}
