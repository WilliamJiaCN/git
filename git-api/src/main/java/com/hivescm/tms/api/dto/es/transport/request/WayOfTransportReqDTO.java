package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Objects;
import com.hivescm.common.exception.SystemException;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportGoodsDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportDetailDTO;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherFeeShareTypeEnum;
import com.hivescm.tms.api.enums.biz.transport.DepartTypeEnum;
import com.hivescm.tms.api.enums.biz.transport.TransportWaybillStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 保存运输批次请求信息，用户新增、修改接口
 *
 * @author 李洪春
 * @since 2017/8/29 17:11
 */
@Data
@ToString
public class WayOfTransportReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7270371287389467420L;

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

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;

    @Required
    @Mapping
    @ApiModelProperty(value = "发车批次ID",required=true)
    private Long transportId;

    /**
     * 发车批次线路信息
     */
    @ApiModelProperty("发车批次线路信息")
    private TransportLineEsDTO transportLineEsDTO;
    
    /**
     * 当前发车批次费用信息
     */
    @ApiModelProperty(value= "当前发车批次费用明细")
    private List<TransportCostDetailEsDTO> transportCostDetailList;
    /**
     * 发车批次明细信息列表
     */
    @Required
    @ApiModelProperty(value = "发车批次明细信息列表",required=true)
    private List<TmsTransportDetailDTO> tmsTransportDetailList;
  
    public  void  checkRequired(){
    	if(null == transportId) {
            throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "前端参数错误！");
    	}
    	if(CollectionUtils.isEmpty(tmsTransportDetailList)) {
            throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "当前网点未选择可操作运单！");
    	}
    	for(TmsTransportDetailDTO detail : tmsTransportDetailList) {
    		TransportWaybillDetailEsDTO way = detail.getTransportWaybillDetail();
    		if(null == way) {
                throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "未选择运单！");
    		}
    		if(way.getWaybillCode() ==null || way.getWaybillId() ==null ||way.getLoadAmount() ==null || way.getLoadWeight() ==null
    			||	way.getLoadVolume() ==null ) {
    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "运单装车件数、装车重量、装车体积不能为空！");
    			
    		}
    		List<TransportGoodsDetailEsDTO>  goods = detail.getTransportGoodsDetailList();
    		if(CollectionUtils.isEmpty(goods)) {
    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "货物明细不能为空！");
    		}
    		for(TransportGoodsDetailEsDTO dto : goods) {
    			if(dto.getWaybillId()==null || dto.getLoadAmount() ==null || dto.getLoadWeight() ==null || dto.getLoadVolume() ==null 
    					|| dto.getGoodsId() ==null || dto.getGoodsName() ==null || dto.getWaybillStockDetailId() ==null) {
    				throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "货物明细装车件数、装车重量、装车体积不能为空！");
    			}
    		}
    	}
    }
    
  /**
   * 途径装货  
   */
    public void initOnWay(TransportInfoEsDTO  transportInfo) {
        Long time = System.currentTimeMillis();
        
        if(CollectionUtils.isNotEmpty(this.getTransportCostDetailList())) {
        	this.getTransportCostDetailList().forEach(cost ->{
        		cost.setCreateUser(opUserId);//创建人
        		cost.setCreateUserName(opUserName);
        		cost.setCreateTime(time);//创建时间
        		cost.setCompanyId(companyId);//操作公司网点
        		cost.setCreateBranchId(branchId);//录入网点ID
        		cost.setCreateBranchName(branchName);//录入网点
        		cost.setDepartBatch(transportInfo.getDepartBatch());//批次号
        	});
        }
        
        // 初始化运输批次运单、货物信息
        if(CollectionUtils.isNotEmpty(this.getTmsTransportDetailList())) {
        	this.getTmsTransportDetailList().forEach(tmsTransportDetailDTO -> {
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUser(opUserId);//创建人
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUserName(opUserName);//操作人姓名
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateTime(time);//创建时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCompanyId(companyId);//公司ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadTime(time);//装货时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchId(branchId);//装货网点ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchName(branchName);//装货网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setTransportId(transportId);//批次ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setUnload(false);//是否已经卸载
        		tmsTransportDetailDTO.getTransportWaybillDetail().setStatus(TransportWaybillStatusEnum.LOAD.getType());//状态“已装车”
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartType(DepartTypeEnum.FEEDER.getType());//发车批次类型
        		
        		//冗余批次信息
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBatch(transportInfo.getDepartBatch());//批次号
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartType(transportInfo.getDepartType());//发车批次类型
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchId(transportInfo.getDepartBranchId());//发车网点
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchName(transportInfo.getDepartBranchName());//发车网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setBatchDepartTime(transportInfo.getDepartTime());//发车时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setEstimatedArrivalTime(transportInfo.getEstimatedArrivalTime());//预计到达时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchId(transportInfo.getArrivalBranchId());//终端网点
        		tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchName(transportInfo.getArrivalBranchName());//终端网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleId(transportInfo.getVehicleId());//车辆ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleNo(transportInfo.getVehicleNo());//车牌号
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverId(transportInfo.getDriverId());//司机ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverName(transportInfo.getDriverName());//司机姓名
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverPhoneNo(transportInfo.getDriverPhoneNo());//司机手机号
        		
        		tmsTransportDetailDTO.getTransportGoodsDetailList().forEach(transportGoodsDetailDTO -> {
        			transportGoodsDetailDTO.setCreateUser(opUserId);//创建人
        			transportGoodsDetailDTO.setCreateUserName(opUserName);//操作人姓名
        			transportGoodsDetailDTO.setCreateTime(time);//创建时间
        			transportGoodsDetailDTO.setCompanyId(companyId);//公司ID
        			transportGoodsDetailDTO.setBranchId(branchId.longValue());//装车网点ID
        			transportGoodsDetailDTO.setTransportId(transportId);
        		});
        	});
        }
     }
    
    public void checkTransportCost() {
		 if(CollectionUtils.isNotEmpty(this.getTransportCostDetailList())) {
			 Set<String> s = new HashSet<String>();
			 for (TransportCostDetailEsDTO cost  : this.getTransportCostDetailList() ) {
				 String temp = cost.getFeeType()+cost.getPayBranchId()+cost.getPayeeId();
		            boolean b = s.add(temp);
		            if(!b){
		    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "费用名称、收款方名称、付款方名称不能出现重复！");
		            }
		        }
		 }
	}
    
}
