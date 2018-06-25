package com.hivescm.tms.api.dto.es.global.response;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.global.GlobalDealerCarrierLineInfoEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 经销商设置承运商关联表 
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalDealerCarrierInfoRespDTO implements Serializable{
	private static final long serialVersionUID = 3643802398762531144L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("市ID")
    private String cityId;
	@Mapping
	@ApiModelProperty("经销商ID")
    private Long dealerId;
	@Mapping
	@ApiModelProperty("承运商ID")
    private Long carrierId;
	@Mapping
	@ApiModelProperty("线路类型 1、全部 2、部分")
    private Integer lineType;
	@Mapping
	@ApiModelProperty("承运商顺序")
    private Integer carrierOrder;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/****************************冗余字段**********************/
	@Mapping
	@ApiModelProperty("市名称")
	private String cityName;
	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;
	@Mapping
	@ApiModelProperty("承运商名称")
	private String carrierName;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
	
	//经销商选取承运商下线路
	List<GlobalDealerCarrierLineInfoEsDTO> lines;

}