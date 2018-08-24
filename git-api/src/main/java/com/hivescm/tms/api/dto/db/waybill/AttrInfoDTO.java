package com.hivescm.tms.api.dto.db.waybill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 李洪春
 * @since 2017/8/17 20:09
 */
@Data
@ToString
public class AttrInfoDTO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String attrName;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    private String attrCode;

    /**
     * 配置类型
     */
    @ApiModelProperty("配置类型 格式为 展示_必选 1、默认选中，不可修改 2、默认选中，可修改 3、默认未选中，可修改 4、默认未选中，不可修改")
    private String configType;
    /**
     * 属性所属组ID
     */
    @ApiModelProperty("所属分组ID")
    private Integer attrGroupId;

    /**
     * 排序
     */
    @ApiModelProperty("展示顺序")
    private Integer sort;

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

    private static final long serialVersionUID = 1L;


}
