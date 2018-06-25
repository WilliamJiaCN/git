package com.hivescm.tms.api.dto.db.waybill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 光标路径配置
 *
 * @author 李洪春
 * @since 2017/8/18 16:17
 */
@Data
@ToString
public class WaybillCursorPathDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 属性id
     */
    @ApiModelProperty("属性id")
    private Integer attrId;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 属性名
     */
    @ApiModelProperty("属性名")
    private String attrName;

    /**
     * 字段编码
     */
    @ApiModelProperty("字段编码")
    private String attrCode;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改人
     */
    private Integer updateUser;

    /**
     * 修改时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}
