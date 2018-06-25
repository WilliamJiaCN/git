package com.hivescm.tms.api.dto.db.waybill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运单基础属性配置
 *
 * @author dengboqiang
 * @date 2017年7月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillPrintTemplateDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 0L;

    /**
     * id
     */
    private Long id;
    /**
     * 公司ID
     */
    @ApiModelProperty("公司Id")
    private Integer companyId;
    /**
     * 网点ID
     */
    @ApiModelProperty("网点Id")
    private Integer branchId;
    /**
     * 模板类型
     */
    @ApiModelProperty("模板类型")
    private Integer templateType;
    /**
     * 模板ID
     */
    @ApiModelProperty("模板Id")
    private Integer templateCode;
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