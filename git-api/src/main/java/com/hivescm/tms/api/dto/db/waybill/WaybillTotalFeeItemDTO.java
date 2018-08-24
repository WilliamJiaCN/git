package com.hivescm.tms.api.dto.db.waybill;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 派车单总费用计算项
 *
 * @author 李洪春
 * @since 2017/8/18 15:59
 */
@Data
@ToString
public class WaybillTotalFeeItemDTO implements Serializable {
    private static final long serialVersionUID = 8644413294632908946L;
    /**
     * id
     */
    private Long id;
    /**
     * 配置Id
     */
    private Long configId;
    /**
     * 字段属性
     */
    @Required
    @ApiModelProperty("属性编码")
    private String attrCode;
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
