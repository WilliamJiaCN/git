package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

/**
 * RF 端操作数据对象
 * @author 曾小明
 *
 */
@Data
@ToString
public class RfTransportOperateReqDTO implements Serializable {
    private static final long serialVersionUID = -532151097077810783L;


    /**
     * 公司Id
     */
    @Mapping
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @Mapping
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @Mapping
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 网点ID
     */
    @Mapping
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @Mapping
    @ApiModelProperty("网点名称")
    private String branchName;
    
    @ApiModelProperty("公司名称")
    private String companyName;
    
    /**
     * 运输批次ID
     */
    private    @ApiModelProperty("发车批次ID，批量发车使用") List<Long> transportIdList;
    
    /**
     * 车辆id
     */
      private   @ApiModelProperty("车辆id") Integer vehicleId;
      /**
       * 车牌号码
       */
      private @ApiModelProperty("车牌号码") String vehicleNo;
      
      
      /**
 	  * 车辆状态
 	  */
 	 private @ApiModelProperty("未发车=途经网点已到达=2，已发车=途经网点已发车=3") Integer vehicleStatus;
}
