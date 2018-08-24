package com.hivescm.tms.api.dto.es.transport.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运输批次途经网点信息
 *
 * @author
 */
@Data
@ToString
public class TransportLineResDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 222890180410129071L;

	/**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty("途经网点ID")
    private Integer branchId;

    @ApiModelProperty("网点名称")
    private String branchName;
   
    @Mapping
    @ApiModelProperty("网点类型， 1、始发网点  2、途经网点  3、目的网点")
    private Integer branchType;

    @Mapping
    @ApiModelProperty("网点装车备注信息")
    private String loadRemark;


}