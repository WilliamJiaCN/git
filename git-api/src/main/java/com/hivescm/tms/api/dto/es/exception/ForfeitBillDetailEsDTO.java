package com.hivescm.tms.api.dto.es.exception;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 罚款单明细信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class ForfeitBillDetailEsDTO implements Serializable {
    private static final long serialVersionUID = 6360532795264655783L;
    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 罚款单ID
     */
    @Mapping
    @ApiModelProperty("罚款单ID")
    private Long forfeitBillId;

    /**
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;
    @ApiModelProperty("运单编码")
    private String waybillCode;


    /**
     * 业务类型
     */
    @Mapping
    @ApiModelProperty("业务类型")
    private Integer businessType;

    /**
     * 业务单据ID
     */
    @Mapping
    @ApiModelProperty("业务单据ID")
    private Long billId;
    @ApiModelProperty("业务单据编码")
    private String billCode;

    /**
     * 说明信息
     */
    @Mapping
    @ApiModelProperty("说明信息")
    private String remark;

    /**
     * 罚款金额
     */
    @Mapping
    @ApiModelProperty("罚款金额")
    private BigDecimal forfeitAmount;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @ApiModelProperty("创建人姓名")
    private String createUserName;

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
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Long updateTime;

    /*********************************************************冗余***************************************************/

    @ApiModelProperty("录单时间")
    private Long waybillCreateTime;

    /**
     * 发货网点ID
     */
    @ApiModelProperty("发货网点ID")
    private Integer invoiceOrgId;

    /**
     * 发货网点名称
     */
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;

    /**
     * 到达网点id
     */
    @ApiModelProperty("目的网点id")
    private Integer destOrgId;

    /**
     * 到达网点名称
     */
    @ApiModelProperty("目的网点名称")
    private String destOrgName;

    /**
     * 发货人
     */
    @ApiModelProperty("发货人")
    private String invoiceUser;

    /**
     * 收货人
     */
    @ApiModelProperty("收货人")
    private String receiptUser;

    /**
     * 商品名称 ,“/”间隔
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 总体积
     */
    @ApiModelProperty("体积")
    private String volume;

    /**
     * 总重量
     */
    @ApiModelProperty("重量")
    private BigDecimal weight;

    /**
     * 总件数
     */
    @ApiModelProperty("总件数")
    private Integer totalNum;
}