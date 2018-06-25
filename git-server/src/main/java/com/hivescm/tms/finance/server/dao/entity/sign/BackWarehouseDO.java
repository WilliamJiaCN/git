package com.hivescm.tms.finance.server.dao.entity.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BackWarehouseDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	@Mapping
	@ApiModelProperty("公司id")
    private Long companyId;

	@Mapping
	@ApiModelProperty("运单id")
    private Long waybillId;

	@Mapping
	@ApiModelProperty("派车单id")
    private Long dispatcherId;

	@Mapping
	@ApiModelProperty("派车单明细id")
    private Long dipatcherDetailId;

	@Mapping
	@ApiModelProperty("入库批次")
    private String storageBatchCode;

	@Mapping
	@ApiModelProperty("入库网点id")
    private Integer storageDot;
	@Mapping
	@ApiModelProperty("入库网点名称")
    private String storageDotName;

	@Mapping
	@ApiModelProperty("入库时间")
    private Long storageTime;

	@Mapping
	@ApiModelProperty("入库仓库名称")
    private String storageName;
	@Mapping
	@ApiModelProperty("入库仓库id")
    private Integer storageId;

	@Mapping
	@ApiModelProperty("送货件数")
    private Integer deliveriesNumber;

	@Mapping
	@ApiModelProperty("入库件数")
    private Integer storageNumber;
	
	@Mapping
	@ApiModelProperty("反库类型")
    private Integer backType;

	@Mapping
	@ApiModelProperty("返回后配送方式")
    private Integer backDeliveryType;

	@ApiModelProperty("反库货物名称")
    private String goodsName;
	
	@Mapping
	@ApiModelProperty("反库说明")
    private String backInstructions;
	
	@Mapping
	@ApiModelProperty("签收单Id")
    private Long signId;

	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;

	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;

	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;

	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;

	@ApiModelProperty("是否作废")
	@Mapping
	private Boolean idelete;
}