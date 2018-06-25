package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单新增请求实体用于外部接口调用
 * TmsDispatcherEsDTO
 *
 * @author lutingting
 * @date 2017年11月10日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcheAddReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */
   
    @ApiModelProperty("公司id")
    private Long companyId;
    
    @ApiModelProperty("公司名称")
    private String companyName;
    
    @ApiModelProperty("集团ID")
    private Long groupId;
    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;
    /**
     * 派车单状态
     */

    @ApiModelProperty("派车单状态(4，未发车， 5、已发车  8、已作废，9,已交接; 10, 已配送)")
    private Integer status;

    /**
     * 派车单发布状态
     */
   
    @ApiModelProperty("派车单发布状态")
    private Boolean releaseStatus;
    /**
     * 司机ID
     */
    @Logger
    @ApiModelProperty("司机ID")
    private Integer driverId;

    /**
     * 车辆ID
     */
    @Logger
    @ApiModelProperty("车辆ID")
    private Integer vehicleId;

    /**
     *运单ID"
     */
    @ApiModelProperty("运单ID")
    private List<Long> waybillId;
    
    
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Logger
	@ApiModelProperty("仓库交接单ID")
	private Integer warehouseReceiptId;
	@Logger
	@ApiModelProperty("仓库交接单号")
    private String warehouseReceiptNo;

	@Logger
    @ApiModelProperty("来源类型  1-tms  2-wms")
    private Integer sourceType ;
	@ApiModelProperty("备注")
   private String remark;

}
