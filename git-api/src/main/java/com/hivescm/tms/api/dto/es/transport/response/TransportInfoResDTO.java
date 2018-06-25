package com.hivescm.tms.api.dto.es.transport.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运输批次基础信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class TransportInfoResDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 422901402280312046L;

	@ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("发车批次 ， 自动生成")
    private String departBatch;

    @Mapping
    @ApiModelProperty("发车网点名称")
    private String departBranchName;

    @Mapping
    @ApiModelProperty("目标网点名称")
    private String arrivalBranchName;

    @Mapping
    @ApiModelProperty("司机手机号码")
    private String driverPhoneNo;

    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;

    @Mapping
    @ApiModelProperty("备注信息")
    private String remark;
    /**
     * 发车网点id
     */
    @Mapping
    @ApiModelProperty("发车网点id")
    private Integer departBranchId;

    /**
     * 目标网点编码
     */
    @Mapping
    @ApiModelProperty("目标网点编码")
    private Integer arrivalBranchId;
    @Mapping
    @ApiModelProperty("运输线路")
    private String transportLine;
}