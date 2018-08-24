package com.hivescm.tms.api.dto.es.transport;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 到货费用明细
 *
 * @author
 */
@Data
@ToString
public class TransportArrivalCostDetailEsDTO implements Serializable {

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
     * 到货ID
     */
    @Mapping
    @ApiModelProperty("到货ID")
    private Long arrivalId;

    /**
     * 费用类型
     */
    @Mapping
    @ApiModelProperty("费用类型")
    private String feeType;

    /**
     * 费用类型名称
     */
    @Mapping
    @ApiModelProperty("费用类型名称")
    private String feeTypeName;

    /**
     * 油卡号码
     */
    @Mapping
    @ApiModelProperty("油卡号码")
    private String oilCardNo;

    /**
     * 费用金额
     */
    @Mapping
    @ApiModelProperty("费用金额")
    private BigDecimal amount;

    /**
     * 付款网点ID
     */
    @Mapping
    @ApiModelProperty("付款网点ID")
    private Integer payBranchId;

    /**
     * 收款方ID（装卸队、承运商）
     */
    @Mapping
    @ApiModelProperty("收款方ID（装卸队、承运商）")
    private String payeeId;

    /**
     * 费用说明
     */
    @Mapping
    @ApiModelProperty("费用说明")
    private String costDesc;

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

    private static final long serialVersionUID = 1L;

    //  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> 冗余基本信息 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 创建者姓名
     */
    @ApiModelProperty("创建者用户名")
    private String createUserName;
    /**
     * 修改者姓名
     */
    @ApiModelProperty("修改者用户名")
    private String updateUserName;
    /**
     * 付款网点名称
     */
    @ApiModelProperty("付款网点名称")
    private String payBranchName;
    /**
     * 收款方名称
     */
    @ApiModelProperty("收款方名称")
    private String payeeName;

}