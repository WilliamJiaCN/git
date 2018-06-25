package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 详情查看
 * <p>Title: HandLingOrderDetailEsDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * version 1.0
 * @create 2018-05-22-15:53
 */
@Data
@ToString
public class HandLingOrderDetailEsDTO implements Serializable {

    private static final long serialVersionUID = -3176064134824207924L;

    @Mapping @ApiModelProperty("运单号") private String waybillCode2;// handlingorderdetails
    @Mapping
    @ApiModelProperty("类型")
    private Integer type;
    @Mapping
    @ApiModelProperty("装货时间")
    private Long shippingTime;
    @Mapping
    @ApiModelProperty("装卸队")
    private String handlingTeam;
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;
    @Mapping @ApiModelProperty("装卸类型（1，装货 2卸货）") private Integer handlingType;  // handlingorder
    @Mapping @ApiModelProperty("装卸类型名称") private String handlingTypeName;   // handlingorder
    @Mapping @ApiModelProperty("装卸批次") private String handlingOrderCode;    // handlingorder
    @Mapping @ApiModelProperty("装卸网点ID") private Integer branchId;      // handlingorder
    @Mapping @ApiModelProperty("装卸网点名称") private String branchName;     // handlingorder
    @Mapping @ApiModelProperty("装卸件数") private Integer packageNum;  // handlingorder
    @Mapping @ApiModelProperty("装卸重量") private BigDecimal weight; // handlingorder
    @Mapping @ApiModelProperty("装卸体积") private BigDecimal volume; // handlingorder
    @Mapping @ApiModelProperty("分摊成本") private BigDecimal cost;     // handlingorder
    @Mapping @ApiModelProperty("实际运费") private BigDecimal freight; // handlingorderdetails
    @Mapping @ApiModelProperty("产值") private BigDecimal outputValue; // handlingorderdetails
    @Mapping @ApiModelProperty("业务费") private BigDecimal busFee;      // handlingorderdetails
    @Mapping @ApiModelProperty("装卸时间") private Long handlingStartTime; // handlingorder
    @Mapping @ApiModelProperty("来源车辆") private String vehicheNo;       // handlingorder
    @Mapping @ApiModelProperty("来源批次") private String batchCode;       // handlingorder
    @Mapping @ApiModelProperty("装卸队名称") private String handlingTeamName;    // handlingorder
    @Mapping @ApiModelProperty("负责人名称") private String handlingTeamLeaderName;  // handlingorder
    @Mapping @ApiModelProperty("负责人电话") private String teamLeaderPhoneNo;   // handlingorder

    // handlingorderdetails
    // handlingorder
    // waybill

    @Mapping @ApiModelProperty("运单状态") private Integer status;  // waybill
    @Mapping @ApiModelProperty("运单状态名称") private  String statusName;// waybill
    @Mapping @ApiModelProperty("货物名称") private String goodsTypeName;// waybill
    @Mapping @ApiModelProperty("包装名称")private  String packingName;// waybill
    @Mapping @ApiModelProperty("总件数") private Integer totalNum;// waybill
    @Mapping @ApiModelProperty("总体积") private BigDecimal totalVolume;// waybill
    @Mapping @ApiModelProperty("总重量") private BigDecimal totalWeight;// waybill
    @Mapping @ApiModelProperty("目的地名称")private String destName;// waybill
    @Mapping @ApiModelProperty("运输线路id")private Long lineId;// waybill
    @Mapping @ApiModelProperty("运输线路名称")private String lineName;// waybill
    @Mapping @ApiModelProperty("发货网点ID") private Integer invoiceOrgId;// waybill
    @Mapping @ApiModelProperty("发货网点名称")private String invoiceOrgName;// waybill
    @Mapping @ApiModelProperty("到达网点id")private Integer destOrgId;// waybill
    @Mapping @ApiModelProperty("到达网点名称")private String destOrgName;// waybill
    @Mapping @ApiModelProperty("回单要求") private String backType;// waybill
    @Mapping @ApiModelProperty("回单份数") private Integer backNum;// waybill
    @Mapping @ApiModelProperty("回单编号") private String backCode;// waybill
    @Mapping @ApiModelProperty("录单时间") private Long createTime;// waybill
    @Mapping @ApiModelProperty("录单员")private String userName;// waybill
    @ApiModelProperty("运单ID")private Long waybillId;
}
