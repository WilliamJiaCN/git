package com.hivescm.tms.finance.server.dao.entity.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author
 */
@Data
@ToString
public class SignDetailDO implements Serializable {
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
    /**
     * 签收主表id
     */
    @Mapping
    @ApiModelProperty("签收主表id")
    private Long signId;
    /**
     * 运单id
     */
    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;
    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;
    /**
     * 派车单明细ID
     */
    @Mapping
    @ApiModelProperty("派车单明细ID")
    private Long dispatcherDetailId;
    /**
     * 运单货物明细ID
     */
    @Mapping
    @ApiModelProperty("运单货物明细ID")
    private Long waybillGoodsId;
    /**
     * 运单库存明细ID
     */
    @Mapping
    @ApiModelProperty("运单库存明细ID")
    private Long waybillStockDetailId;
    /**
     * 签收件数
     */
    @Mapping
    @ApiModelProperty("签收件数")
    private Integer signNumber;
    /**
     * 拒签件数
     */
    @Mapping
    @ApiModelProperty("拒签件数")
    private Integer refuseNumber;
    /**
     * 未签件数
     */
    @Mapping
    @ApiModelProperty("未签件数")
    private Integer unsignedNumber;
    /**
     * 开单件数
     */
    @Mapping
    @ApiModelProperty("开单件数")
    private Integer createNumber;
    /**
     * 包装
     */
    @Mapping
    @ApiModelProperty("包装")
    private String packages;
    /**
     * 箱号
     */
    @Mapping
    @ApiModelProperty("箱号")
    private String boxNumber;
    /**
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
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
     * 修改时间
     */
    @Mapping
    @ApiModelProperty(" 修改时间")
    private Long updateTime;
    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    /**
     * 单位
     */
    @Mapping
    @ApiModelProperty("单位")
    private String unit;
    /**
     * 数量
     */
    @Mapping
    @ApiModelProperty("数量")
    private Integer amount;
    
    @Mapping
    @ApiModelProperty("拒签原因")
    private String refuseCause;
}