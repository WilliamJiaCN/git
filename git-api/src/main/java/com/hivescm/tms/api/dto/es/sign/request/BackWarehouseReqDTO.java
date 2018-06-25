package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BackWarehouseReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@Required
	@ApiModelProperty("签收单id")
	private Long signId;
	@Mapping
	@ApiModelProperty("公司id")
    private Long companyId;

	@Mapping @Logger
	@ApiModelProperty("运单id")
    private Long waybillId;

	@Mapping @Logger
	@ApiModelProperty("派车单id")
    private Long dispatcherId;

	@Mapping @Logger
	@ApiModelProperty("派车单明细id")
    private Long dipatcherDetailId;

	@Mapping
	@ApiModelProperty("当前网点")
	private Integer curentOrgId;
	@Mapping
	@ApiModelProperty("当前网点名称")
	private String curentOrgName;
	@Mapping
	@ApiModelProperty("入库网点")
    private Integer storageDot;
	@Mapping
	@ApiModelProperty("入库网点名称")
	private String storageDotName;
	@Mapping
	@ApiModelProperty("入库时间")
    private Long storageTime;

	@Mapping
	@ApiModelProperty("仓库名称(id)")
    private Integer storageNameId;

	@Mapping
	@ApiModelProperty("仓库名称")
	private String storageName;

	@Mapping
	@ApiModelProperty("送货件数")
    private Integer deliveriesNumber;

	@Mapping
	@ApiModelProperty("入库件数")
    private Integer storageNumber;
	
	@Mapping
	@ApiModelProperty("返库类型 SignStockDetailEunm - 1,部分签收 2,拒签, 3,派送失败,4,取消签收")
    private Integer backType;//SignStockDetailEunm - SIGNNEDPARTIALLY(1,"部分签收"),SIGNREFUSED(2,"拒签"),DELIVERYFAILED(3,"派送失败"),CANCELSIGN(4,"取消签收");

	@Mapping
	@ApiModelProperty("返回后配送方式(2自提签收;1送货签收;部分签收和全部拒签 此值为空 null)")
	@Required
    private Integer backDeliveryType;

	@Mapping
	@ApiModelProperty("反库货物名称")
    private String goodsName;
	
	@Mapping
	@ApiModelProperty("反库说明")
    private String backInstructions;

	@Mapping @Logger
    @ApiModelProperty("派车批次")
    private String batchCode;
	
	@Mapping @Logger
    @ApiModelProperty("运单号")
    private String waybillCode;

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;

	/**
	 * 修改时间
	 */
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	@ApiModelProperty("修改人姓名")
	private String updateUserName;

	@ApiModelProperty("集团id")
	@Mapping
	private Integer groupId;

}
