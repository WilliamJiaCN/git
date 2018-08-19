package com.hivescm.tms.api.dto.db.waybill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李洪春
 * @since 2017/7/17 15:12
 */
@Data
@ToString
public class AttrGroupDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 分组编码
     */
    @ApiModelProperty("分组编码")
    private Integer groupId;

    /**
     * 分组名称
     */
    @ApiModelProperty("分组名称")
    private String groupName;

    /**
     * 分组编码
     */
    @ApiModelProperty("分组编码")
    private String groupCode;
    /**
     * 分组属性列表
     */
    @ApiModelProperty("该组属性列表")
    private List<com.hivescm.tms.api.dto.db.waybill.AttrInfoDTO> attrInfoList;

}
