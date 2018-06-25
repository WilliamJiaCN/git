package com.hivescm.tms.api.dto.db.waybill;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillAttrConfigDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Mapping
    private Long id;

    /**
     * 配置id
     */
    @Mapping
    private Long configId;

    /**
     * 属性ID，不持久化
     */
    @Mapping
    @ApiModelProperty("属性ID")
    private Integer attrId;

    /**
     * 属性编码
     */
    @Mapping
    @Required
    @ApiModelProperty("属性编码")
    private String attrCode;

    /**
     * 是否展示
     */
    @Required
    @ApiModelProperty("是否展示")
    @Mapping("isShow")
    private Boolean show;

    /**
     * 是否为必选
     */
    @Required
    @ApiModelProperty("是否必选")
    @Mapping("isRequired")
    private Boolean required;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long updateTime;

    /**
     * 属性，名称
     */
    @Mapping
    @ApiModelProperty("属性名称")
    private String attrName;
    
    @Mapping
    @ApiModelProperty("分组ID")
    private Integer groupId;

}
