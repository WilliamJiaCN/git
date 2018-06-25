package com.hivescm.tms.api.dto.es.transport;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 到货信息
 */
@Data
@ToString
public class TransportArrivalInfoEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("网点ID")
    private Integer branchId;

    @Mapping
    @ApiModelProperty("运输单ID")
    private Long transportId;

    @Mapping
    @ApiModelProperty("到货批次")
    private String arrivalBatch;

    @Mapping
    @ApiModelProperty("状态")
    private Integer status;

    @Mapping
    @ApiModelProperty("到货时间")
    private Long arrivalTime;

    @Mapping
    @ApiModelProperty("费用分摊方式")
    private Integer shareType;

    @Mapping
    @ApiModelProperty(value = "到货成本", example = "12.12", notes = "数字类型")
    private BigDecimal arrivalCost;

    @Mapping
    @ApiModelProperty("到货备注信息")
    private String remark;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;
    
    @Mapping
    @ApiModelProperty("总票数")
    private Integer waybillnum;
    
    @Mapping
    @ApiModelProperty("总件数")
    private Integer unloadAmount;
    
    @Mapping
    @ApiModelProperty("总重量")
    private BigDecimal unloadWeight;
    
    @Mapping
    @ApiModelProperty("总体积")
    private BigDecimal unloadVolume;
    
    @Mapping
    @ApiModelProperty("总运费")
    private BigDecimal totalFee;
    
    @Mapping
    @ApiModelProperty("总产值")
    private BigDecimal outputValue;
    
    @Mapping
    @ApiModelProperty("总业务费")
    private BigDecimal businessFee;

    @Mapping
    @ApiModelProperty("到货确认人")
    private Integer confirmationUser;




    @Mapping
    @ApiModelProperty("到货确认时间")
    private Long confirmationTime;


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余基本信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @ApiModelProperty("分摊方式名称")
    private String shareTypeName;

    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 修改人姓名
     */
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    @Mapping
    @ApiModelProperty("到货确认姓名")
    private String confirmationUserName;

    /**
     * 到货网点名称
     */
    @ApiModelProperty("到货网点名称")
    private String branchName;

    /**
     * 发车批次
     */
    @Mapping
    @ApiModelProperty("发车批次")
    private String departBatch;

    @Mapping
    @ApiModelProperty("发车网点ID")
    private Integer departBranchId;

    @Mapping
    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    @Mapping
    @ApiModelProperty("到达网点ID")
    private Integer arrivalBranchId;

    @Mapping
    @ApiModelProperty("到达网点名称")
    private String arrivalBranchName;
    
}