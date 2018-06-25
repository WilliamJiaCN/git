package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.tms.api.dto.es.transport.component.RfTransportArriveDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/4/12 15:24
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class RfTransportArriveReqDTO implements Serializable{

    private static final long serialVersionUID = -1762791764510692620L;

    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

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
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;

    @ApiModelProperty(value = "集团ID", notes = "前端调用时不传")
    private Integer groupId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = " 网点名称", notes = "前端调用时不传")
    private String branchName;

    /**
     * 车牌号码
     */
    private @ApiModelProperty("车牌号码") String vehicleNo;

    /**
     * 车辆ID
     */
    private @ApiModelProperty("车辆ID") Integer vehicleId;


    private   @ApiModelProperty("发车批次ID")  Long transportId;

    private   @ApiModelProperty("到货批次ID")  Long arrivalId;

    /**
     * 运单标签信息
     */
    private @ApiModelProperty("运单标签信息") List<RfTransportArriveDTO> rfTransportArriveDTOList;


}
