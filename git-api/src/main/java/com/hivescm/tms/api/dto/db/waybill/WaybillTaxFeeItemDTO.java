package com.hivescm.tms.api.dto.db.waybill;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 派车单税费计算项
 *
 * @author 李洪春
 * @since 2017/8/18 16:00
 */
@Data
@ToString
public class WaybillTaxFeeItemDTO implements Serializable {
    private static final long serialVersionUID = 2652075997737135349L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 配置Id
     */
    private Long configId;
    /**
     * 操作
     */
    @Required
    @ApiModelProperty("操作类型， +，- 等")
    private String operation;
    /**
     * 字段属性
     */
    @Required
    @ApiModelProperty("属性编码")
    private String attrCode;
    /**
     * 费率
     */
    @Required
    @ApiModelProperty("费率")
    private BigDecimal rate;
    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private Integer createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Long createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(hidden = true)
    private Integer updateUser;
    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Long updateTime;
}
