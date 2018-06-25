package com.hivescm.tms.api.dto.es.exception;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单异常附件信息
 */
@Data
@ToString
public class WaybillExceptionAttachEsDTO implements Serializable {

    private static final long serialVersionUID = -3137890378819836176L;
    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司ID（分库分表路由字段）
     */
    @Mapping
    @ApiModelProperty("公司ID（分库分表路由字段）")
    private Long companyId;

    /**
     * 异常单ID
     */
    @Mapping
    @ApiModelProperty("异常单ID")
    private Long exceptionId;

    /**
     * 文件名称
     */
    @Mapping
    @ApiModelProperty("文件名称")
    private String attachName;

    /**
     * 文件大小
     */
    @Mapping
    @ApiModelProperty("文件大小")
    private Integer attachSize;

    /**
     * 附件顺序
     */
    @Mapping
    @ApiModelProperty(value="附件顺序",hidden=true)
    private Integer attachSort;

    /**
     * 附件链接
     */
    @Mapping
    @ApiModelProperty("附件链接")
    private String attachUrl;

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
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

}