package com.hivescm.tms.api.dto.es.exception.request;


import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillStatusUpdateReqDTO {

	@Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty("异常状态 1.未提交 2.未处理 3.处理中 4.已办结")
    private Integer waybillExceptionStatus;
    
    @Mapping
    @ApiModelProperty("异常状态 1.未提交 2.未处理 3.处理中 4.已办结")
    private String waybillExceptionStatusName;

    @Mapping
    @ApiModelProperty(value="修改人ID 异常单最后一次的修改人",hidden=true)
    private Integer updateUserId;

    @Mapping
    @ApiModelProperty(value="修改人姓名",hidden=true)
    private String updateUserName;

    @Mapping
    @ApiModelProperty(value="修改人时间 异常单最后一次的修改时间",hidden=true)
    private Long updateTime;
}
