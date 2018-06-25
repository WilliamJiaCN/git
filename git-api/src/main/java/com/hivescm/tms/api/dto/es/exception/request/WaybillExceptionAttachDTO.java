package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 异常办结
 */
@Data
@ToString
public class WaybillExceptionAttachDTO implements Serializable{

    private static final long serialVersionUID = -447636091410921075L;

    /**
     * 文件名称
     */
    @Mapping
    @ApiModelProperty(value = "文件名称")
    private String attachName;

    /**
     * 文件大小
     */
    @Mapping
    @ApiModelProperty(value = "文件大小")
    private Integer attachSize;

    /**
     * 附件顺序
     */
    @Mapping
    @ApiModelProperty(value = "附件顺序")
    private Integer attachSort;

    /**
     * 附件链接
     */
    @Mapping
    @ApiModelProperty(value = "附件链接")
    private String attachUrl;
}
