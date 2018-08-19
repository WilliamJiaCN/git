package com.hivescm.tms.api.dto.es.transport;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运输批次费用信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class TransportCostDetailEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 运输单ID
     */
    @Mapping
    @ApiModelProperty("运输单ID")
    private Long transportId;

    /**
     * 费用类型
     */
    @Mapping
    @ApiModelProperty("费用类型")
    private String feeType;

    /**
     * 油卡号码
     */
    @Mapping
    @ApiModelProperty("油卡号码")
    private String oilCardNo;

    /**
     * 金额
     */
    @Mapping
    @ApiModelProperty(value = "金额", example = "111.11")
    private BigDecimal amount;

    /**
     * 付款方ID（网点ID）
     */
    @Mapping
    @ApiModelProperty("付款方ID（网点ID）")
    private Integer payBranchId;

    /**
     * 收款方ID（承运商、装货队）
     */
    @Mapping
    @ApiModelProperty("收款方ID（承运商、装货队）")
    private String payeeId;

    /**
     * 费用说明
     */
    @Mapping
    @ApiModelProperty("费用说明")
    private String costDesc;

    /**
     * 录入网点ID
     */
    @Mapping
    @ApiModelProperty("录入网点ID")
    private Integer createBranchId;

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

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 录入网点名称
     */
    @ApiModelProperty("录入网点名称")
    private String createBranchName;


    /**
     * 付款网点ID
     */
    @ApiModelProperty("付款网点名称")
    private String payBranchName;

    /**
     * 收款方名称
     */
    @ApiModelProperty("收款方名称  装卸队、承运商")
    private String payeeName;

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
    
    /**
     * 费用类型名称
     */
    @Mapping
    @ApiModelProperty("费用类型名称")
    private String feeTypeName;

    
    @ApiModelProperty("发车批次号")
    private String departBatch;

}