package com.hivescm.tms.api.dto.es.exception;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 异常处理
 */
@Data
@ToString
public class WaybillExceptionProcessEsDTO implements Serializable{
    private static final long serialVersionUID = -6561177552849307358L;

    @Mapping
    @ApiModelProperty(value="主键",hidden=true)
    private Long id;
    @Mapping
    @ApiModelProperty(value="公司ID",hidden=true)
    private Long companyId;
    @Mapping
    @ApiModelProperty("异常ID")
    private Long exceptionId;
    @Mapping
    @ApiModelProperty(value="处理序号",hidden=true)
    private Integer processSort;
    @Mapping
    @ApiModelProperty("处理时间")
    private Long processTime;
    @Mapping
    @ApiModelProperty("处理人ID")
    private Integer processUserId;
    @Mapping
    @ApiModelProperty("处理人姓名")
    private String processUserName;
    @Mapping
    @ApiModelProperty("处理方ID")
    private Integer processBranchId;
    @Mapping
    @ApiModelProperty("处理方名称")
    private String processBranchName;
    @Mapping
    @ApiModelProperty("处理意见")
    private String processAdvise;
    @Mapping
    @ApiModelProperty("处理图片")
    private String processPic;
    @Mapping
    @ApiModelProperty(value="创建人ID",hidden=true)
    private Integer createUserId;
    @Mapping
    @ApiModelProperty(value="创建人姓名",hidden=true)
    private String createUserName;
    @Mapping
    @ApiModelProperty(value="创建时间",hidden=true)
    private Long createTime;
    @Mapping
    @ApiModelProperty(value="修改人ID",hidden=true)
    private Integer updateUserId;
    @Mapping
    @ApiModelProperty(value="修改人姓名",hidden=true)
    private String updateUserName;
    @Mapping
    @ApiModelProperty(value="修改时间",hidden=true)
    private Long updateTime;
}
