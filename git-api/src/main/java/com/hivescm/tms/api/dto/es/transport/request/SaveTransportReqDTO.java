package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Objects;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportGoodsDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportDetailDTO;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherFeeShareTypeEnum;
import com.hivescm.tms.api.enums.biz.transport.TransportLineBranchTypeEnum;
import com.hivescm.tms.api.enums.biz.transport.TransportLineOperateTypeEnum;
import com.hivescm.tms.api.enums.biz.transport.TransportLineStatusEnum;
import com.hivescm.tms.api.enums.biz.transport.TransportStatusEnum;
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
public class SaveTransportReqDTO implements Serializable {
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

    /**
     * 运输批次信息
     */
    @ApiModelProperty("运输批次信息")
    private TmsTransportDTO tmsTransport;
    
    /**
     * 库存网点（单票添加专用字段）
     */
    @ApiModelProperty("库存网点")
    private Integer departBranchId;
    /**
     * 到达网点（单票添加专用字段）
     */
    @ApiModelProperty("到达网点")
    private Integer arrivalBranchId;


    /**
     * 新增运输批次时初始化信息
     */
    public void initOnCreate() {
    	
    	this.initCount();//计算
    	
        Long time = System.currentTimeMillis();
        // 初始化运输批次信息
        tmsTransport.getTransportInfo().setCreateUser(opUserId);//创建人
        tmsTransport.getTransportInfo().setCreateUserName(opUserName);//操作用户名称
        tmsTransport.getTransportInfo().setCreateTime(time);//创建时间
        tmsTransport.getTransportInfo().setCompanyId(companyId);//操作公司网点
        tmsTransport.getTransportInfo().setStatus(TransportStatusEnum.NOT_DEPARTING.getType());//新建默认状态“未发车”
        tmsTransport.getTransportInfo().setArriveBranchId(branchId);//新建批次的默认"到车确认"操作网点
        tmsTransport.getTransportInfo().setDepartBranchId(branchId);//发车网点
        tmsTransport.getTransportInfo().setDepartBranchName(branchName);//发车网点名称

        /**
         * 初始化数据
         */
        // 初始化运输批次费用信息
        if(tmsTransport.getTransportCostDetailList() !=null && tmsTransport.getTransportCostDetailList().size() >0) {
        	tmsTransport.getTransportCostDetailList().forEach(transportCostDetailDTO -> {
        		transportCostDetailDTO.setCreateUser(opUserId);//创建人
        		transportCostDetailDTO.setCreateUserName(opUserName);
        		transportCostDetailDTO.setCreateTime(time);//创建时间
        		transportCostDetailDTO.setCompanyId(companyId);//操作公司网点
        		transportCostDetailDTO.setCreateBranchId(branchId);//录入网点ID
        		transportCostDetailDTO.setCreateBranchName(branchName);//录入网点
        		
        	});
        }
        StringBuffer transportLine= new StringBuffer(branchName);
        // 初始化运输批次线路信息
        if(tmsTransport.getTransportLineEsDTOList() !=null &&  tmsTransport.getTransportLineEsDTOList().size()>0) {
        	tmsTransport.getTransportLineEsDTOList().forEach(transportLineDTO -> {
        		transportLineDTO.setCreateUser(opUserId);//创建人
        		transportLineDTO.setCreateUserName(opUserName);//操作人
        		transportLineDTO.setCreateTime(time);//创建时间
        		transportLineDTO.setCompanyId(companyId);//操作公司网点   		
        		transportLineDTO.setCanBeNext(true);//下一网点
        		transportLineDTO.setStatus(TransportLineStatusEnum.NOT_ARRIVED.getType());//路线状态默认“未到达”
        		transportLine.append("→"+transportLineDTO.getBranchName());
        	});
        	//初始化始发网点
        	TransportLineEsDTO transportLineEsDTO = this.initTransportLine();
        	tmsTransport.getTransportLineEsDTOList().add(transportLineEsDTO);//加入始发网点
        	tmsTransport.getTransportInfo().setLoadFactorWeight(transportLineEsDTO.getLoadFactorWeight());//装载率 重量
        	tmsTransport.getTransportInfo().setLoadFactorVolume(transportLineEsDTO.getLoadFactorVolume());//装载率体积
        }
        tmsTransport.getTransportInfo().setTransportLine(transportLine.toString());//运输线路

        // 初始化运输批次运单、货物信息
        if(tmsTransport.getTmsTransportDetailList() != null &&  tmsTransport.getTmsTransportDetailList().size() >0) {
        	tmsTransport.getTmsTransportDetailList().forEach(tmsTransportDetailDTO -> {
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUser(opUserId);//创建人
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUserName(opUserName);
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateTime(time);//创建时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCompanyId(companyId);//公司ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchId(branchId);//装货网点ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadTime(time);//装货时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchName(branchName);//装货网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setUnload(false);//是否已经卸载
        		tmsTransportDetailDTO.getTransportWaybillDetail().setStatus(TransportWaybillStatusEnum.LOAD.getType());//状态“未到货”
        		
        		// 冗余批次信息
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartType(tmsTransport.getTransportInfo().getDepartType());//发车批次类型
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchId(tmsTransport.getTransportInfo().getDepartBranchId());//发车网点
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchName(tmsTransport.getTransportInfo().getDepartBranchName());//发车网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchId(tmsTransport.getTransportInfo().getArrivalBranchId());//终端网点
        		tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchName(tmsTransport.getTransportInfo().getArrivalBranchName());//终端网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleId(tmsTransport.getTransportInfo().getVehicleId());//车辆ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleNo(tmsTransport.getTransportInfo().getVehicleNo());//车牌号
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverId(tmsTransport.getTransportInfo().getDriverId());//司机ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverName(tmsTransport.getTransportInfo().getDriverName());//司机姓名
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverPhoneNo(tmsTransport.getTransportInfo().getDriverPhoneNo());//司机手机号
        		tmsTransportDetailDTO.getTransportWaybillDetail().setBatchDepartTime(tmsTransport.getTransportInfo().getDepartTime());
        		
        		if(tmsTransportDetailDTO.getTransportGoodsDetailList() !=null && tmsTransportDetailDTO.getTransportGoodsDetailList().size() >0) {
        			tmsTransportDetailDTO.getTransportGoodsDetailList().forEach(transportGoodsDetailDTO -> {
        				transportGoodsDetailDTO.setCreateUser(opUserId);//创建人
        				transportGoodsDetailDTO.setCreateUserName(opUserName);
        				transportGoodsDetailDTO.setCreateTime(time);//创建时间
        				transportGoodsDetailDTO.setCompanyId(companyId);//公司ID
        				transportGoodsDetailDTO.setBranchId(branchId.longValue());//装车网点ID
        			});
        		}
        	});
        }
    }

    /**
     * 修改运输批次时请求信息
     */
    public void initOnModify() {
    	
    	this.initCount();//计算
    	
        Long time = System.currentTimeMillis();
        // 初始化运输批次信息
        tmsTransport.getTransportInfo().setUpdateUser(opUserId);//修改人
        tmsTransport.getTransportInfo().setUpdateUserName(opUserName);//操作人
        tmsTransport.getTransportInfo().setUpdateTime(time);//修改时间
        tmsTransport.getTransportInfo().setCompanyId(companyId);//操作公司网点


        // 初始化运输批次费用信息
        if(tmsTransport.getTransportCostDetailList() !=null && tmsTransport.getTransportCostDetailList().size()>0) {
        	tmsTransport.getTransportCostDetailList().forEach(transportCostDetailDTO -> {
        		transportCostDetailDTO.setCreateUser(opUserId);//创建人
        		transportCostDetailDTO.setCreateUserName(opUserName);
        		transportCostDetailDTO.setCreateTime(time);//创建时间
        		transportCostDetailDTO.setUpdateUser(opUserId);//修改人
        		transportCostDetailDTO.setUpdateUserName(opUserName);//操作人
        		transportCostDetailDTO.setUpdateTime(time);//修改时间
        		transportCostDetailDTO.setCompanyId(companyId);//操作公司网点
        	});
        }
      
        StringBuffer transportLine= new StringBuffer(branchName);
        // 初始化运输批次线路信息
        if(tmsTransport.getTransportLineEsDTOList() !=null && tmsTransport.getTransportLineEsDTOList().size()>0) {
        	tmsTransport.getTransportLineEsDTOList().forEach(transportLineDTO -> {
        		transportLineDTO.setUpdateUser(opUserId);
        		transportLineDTO.setUpdateUserName(opUserName);
        		transportLineDTO.setUpdateTime(time);
        		transportLineDTO.setCompanyId(companyId);//操作公司网点
        		transportLineDTO.setCanBeNext(true);//始发
        		transportLineDTO.setStatus(TransportLineStatusEnum.NOT_ARRIVED.getType());//路线状态默认“未到达”
        		transportLine.append("→"+transportLineDTO.getBranchName());
        	});
        	//初始化始发网点
        	TransportLineEsDTO transportLineEsDTO = this.initTransportLine();
        	tmsTransport.getTransportLineEsDTOList().add(transportLineEsDTO);//加入始发网点
        	tmsTransport.getTransportInfo().setLoadFactorWeight(transportLineEsDTO.getLoadFactorWeight());//装载率 重量
        	tmsTransport.getTransportInfo().setLoadFactorVolume(transportLineEsDTO.getLoadFactorVolume());//装载率体积
        }
        tmsTransport.getTransportInfo().setTransportLine(transportLine.toString());//运输线路

        // 初始化运输批次运单、货物信息
        if( tmsTransport.getTmsTransportDetailList() !=null &&  tmsTransport.getTmsTransportDetailList().size() > 0) {
        	tmsTransport.getTmsTransportDetailList().forEach(tmsTransportDetailDTO -> {
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUser(opUserId);//创建人
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUserName(opUserName);
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCreateTime(time);//创建时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setUpdateUser(opUserId);
        		tmsTransportDetailDTO.getTransportWaybillDetail().setUpdateUserName(opUserName);
        		tmsTransportDetailDTO.getTransportWaybillDetail().setUpdateTime(time);
        		tmsTransportDetailDTO.getTransportWaybillDetail().setCompanyId(companyId);//公司ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchId(branchId);//装货网点ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchName(branchName);//装货网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setLoadTime(time);//装货时间
        		tmsTransportDetailDTO.getTransportWaybillDetail().setUnload(false);//是否已经卸载
        		tmsTransportDetailDTO.getTransportWaybillDetail().setStatus(TransportWaybillStatusEnum.LOAD.getType());//状态“未到货”
        		
        		// 冗余批次信息
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartType(tmsTransport.getTransportInfo().getDepartType());//发车批次类型
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchId(tmsTransport.getTransportInfo().getDepartBranchId());//发车网点
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchName(tmsTransport.getTransportInfo().getDepartBranchName());//发车网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setBatchDepartTime(tmsTransport.getTransportInfo().getDepartTime());
        		tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchId(tmsTransport.getTransportInfo().getArrivalBranchId());//终端网点
        		tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchName(tmsTransport.getTransportInfo().getArrivalBranchName());//终端网点名称
        		tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleId(tmsTransport.getTransportInfo().getVehicleId());//车辆ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleNo(tmsTransport.getTransportInfo().getVehicleNo());//车牌号
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverId(tmsTransport.getTransportInfo().getDriverId());//司机ID
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverName(tmsTransport.getTransportInfo().getDriverName());//司机姓名
        		tmsTransportDetailDTO.getTransportWaybillDetail().setDriverPhoneNo(tmsTransport.getTransportInfo().getDriverPhoneNo());//司机手机号
        		
        		if(tmsTransportDetailDTO.getTransportGoodsDetailList() !=null && tmsTransportDetailDTO.getTransportGoodsDetailList().size()>0) {
        			tmsTransportDetailDTO.getTransportGoodsDetailList().forEach(transportGoodsDetailDTO -> {
        				transportGoodsDetailDTO.setUpdateUser(opUserId);
        				transportGoodsDetailDTO.setUpdateUserName(opUserName);
        				transportGoodsDetailDTO.setUpdateTime(time);
        				transportGoodsDetailDTO.setCompanyId(companyId);//公司ID
        				transportGoodsDetailDTO.setBranchId(branchId.longValue());//装车网点ID
        			});
        		}
        	});
        }
    }
    
    /**
     * 计算费用
     */
    private void initCount(){
    	Integer waybillAmount = tmsTransport.getTmsTransportDetailList().size();    //  总票数
    	BigDecimal departCost = new BigDecimal(0);  //  发车成本  =  现付车费+现付油卡+… + 装卸费 + 叉车费 + 其他费用
    	BigDecimal totalWeight = new  BigDecimal(0);  //  总重量
    	BigDecimal totalVolume = new  BigDecimal(0);  //  总体积
    	//Integer loadFactorWeight = 0;  //  装载率 重量   =当前装载总重量 / 车辆核载质量
    	//Integer loadFactorVolume = 0;  //  装载率 体积   =当前装载总体积 / 车辆核载体积
    	Integer goodsAmount = 0;  //  总件数
    	BigDecimal outputValue = new  BigDecimal(0);  //  总产值 = 运单明细的产值和 
    	BigDecimal businessFee = new  BigDecimal(0);  //  总业务费 = 运单明细的 总业务费和 
    	BigDecimal totalFreight = new BigDecimal(0);  //  总运费 =  运单明细的 总运费和 
    	BigDecimal collectPayment = new  BigDecimal(0);  //  代收货款合计
    	//Integer loadAmount = 0;  //  途经装货件数
    	//Integer unloadAmount = 0;  //  途经卸货件数
    	
    	
    	/**
         * 计算费用
         */
        tmsTransport.getTransportInfo().setWaybillAmount(waybillAmount); //  总票数
        //发车成本
        if(tmsTransport.getTransportCostDetailList() !=null && tmsTransport.getTransportCostDetailList().size()>0) {
        	for(int i=0; i< tmsTransport.getTransportCostDetailList().size() ;i++) {
        		TransportCostDetailEsDTO cost =  tmsTransport.getTransportCostDetailList().get(i);
        		departCost = departCost.add(cost.getAmount()); //发车成本
        	}
        	tmsTransport.getTransportInfo().setDepartCost(departCost);
        }
        
        if(tmsTransport.getTmsTransportDetailList() !=null && tmsTransport.getTmsTransportDetailList().size() >0) {
        	for (int i = 0; i < tmsTransport.getTmsTransportDetailList().size(); i++) {
        		TransportWaybillDetailEsDTO waybill = tmsTransport.getTmsTransportDetailList().get(i).getTransportWaybillDetail();
        		//总重量
        		totalWeight = totalWeight.add(waybill.getLoadWeight() != null ? waybill.getLoadWeight() : BigDecimal.ZERO);
        		//总体积
        		totalVolume = totalVolume.add(waybill.getLoadVolume() != null ?   waybill.getLoadVolume() : BigDecimal.ZERO );
        		//总产值
        		outputValue = outputValue.add(waybill.getOutputValue() != null ? waybill.getOutputValue() : BigDecimal.ZERO);
        		//总业务费 
        		businessFee = businessFee.add(waybill.getBusinessFee() !=null ? waybill.getBusinessFee() : BigDecimal.ZERO );
        		//总运费
        		totalFreight = totalFreight.add(waybill.getFreight() !=null ? waybill.getFreight() : BigDecimal.ZERO);
        		
        		collectPayment= collectPayment.add(waybill.getGoodsPayment() !=null ? waybill.getGoodsPayment() : BigDecimal.ZERO);
        		
        		if(waybill.getLoadAmount() !=null)
        			goodsAmount =  goodsAmount+waybill.getLoadAmount();//总件数
        	}
        	tmsTransport.getTransportInfo().setTotalWeight(totalWeight);//总重量
        	tmsTransport.getTransportInfo().setTotalVolume(totalVolume);//总体积
        	
        	
//        	BigDecimal tempLoadFactorWeight = tmsTransport.getTransportInfo().getTotalWeight().divide(
//        			new  BigDecimal(tmsTransport.getTransportInfo().getCheckQuality() )
//        			);
//        	loadFactorWeight = tempLoadFactorWeight.setScale(2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();	
//        	tmsTransport.getTransportInfo().setLoadFactorWeight(loadFactorWeight);// 装载率 重量   =当前装载总重量 / 车辆核载质量
//        	BigDecimal tempLoadFactorVolume = tmsTransport.getTransportInfo().getTotalVolume().divide(
//        			new BigDecimal(tmsTransport.getTransportInfo().getCheckVolume())
//        			);
//        	loadFactorVolume = tempLoadFactorVolume.setScale(2,  BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();	
//        	tmsTransport.getTransportInfo().setLoadFactorVolume(loadFactorVolume);// 装载率 体积   =当前装载总体积 / 车辆核载体积
        	
        	tmsTransport.getTransportInfo().setOutputValue(outputValue);  //总产值
        	tmsTransport.getTransportInfo().setBusinessFee(businessFee); //总业务费 
        	tmsTransport.getTransportInfo().setTotalFreight(totalFreight);//总运费 
        	tmsTransport.getTransportInfo().setCollectPayment(collectPayment);//总代收货款
        	tmsTransport.getTransportInfo().setGoodsAmount(goodsAmount);//总件数
        }
    }
    
  /**
   * 途径装货  
   */
 public void initOnWay() {
        Long time = System.currentTimeMillis();
        // 初始化运输批次信息
        tmsTransport.getTransportInfo().setUpdateUser(opUserId);//修改人
        tmsTransport.getTransportInfo().setUpdateTime(time);//修改时间
      
        // 初始化运输批次运单、货物信息
        tmsTransport.getTmsTransportDetailList().forEach(tmsTransportDetailDTO -> {
            tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUser(opUserId);//创建人
            tmsTransportDetailDTO.getTransportWaybillDetail().setCreateTime(time);//创建时间
            tmsTransportDetailDTO.getTransportWaybillDetail().setCompanyId(companyId);//公司ID
            tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchId(branchId);//装货网点ID
            tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchName(branchName);//装货网点名称
        	tmsTransportDetailDTO.getTransportWaybillDetail().setLoadTime(time);//装货时间
            tmsTransportDetailDTO.getTransportWaybillDetail().setStatus(TransportWaybillStatusEnum.LOAD.getType());//状态“已装车”
            
            tmsTransportDetailDTO.getTransportGoodsDetailList().forEach(transportGoodsDetailDTO -> {
                transportGoodsDetailDTO.setCreateUser(opUserId);//创建人
                transportGoodsDetailDTO.setCreateTime(time);//创建时间
                transportGoodsDetailDTO.setCompanyId(companyId);//公司ID
                transportGoodsDetailDTO.setBranchId(branchId.longValue());//装车网点ID
            });
        });
    }
    
    /**
     * 初始化始发网点
     * @return
     */
	private  TransportLineEsDTO initTransportLine() {
		Long time = System.currentTimeMillis();
		TransportLineEsDTO transportLineEsDTO = new TransportLineEsDTO();
		transportLineEsDTO.setCreateUser(opUserId);//创建人
		transportLineEsDTO.setCreateUserName(opUserName);//操作人
		transportLineEsDTO.setCreateTime(time);//创建时间
		transportLineEsDTO.setCompanyId(companyId);//操作公司网点
		transportLineEsDTO.setStatus(TransportLineStatusEnum.VEHICLE_ARRIVED.getType());//路线状态默认“已到达”
		transportLineEsDTO.setCanBeNext(false);//"已到达"状态 不可以为下一网点
		transportLineEsDTO.setVehicleArrivedTime(time);//默认到车确认时间
		transportLineEsDTO.setVehicleArrivedUserId(opUserId);//默认到车确认人
		transportLineEsDTO.setArrivalTime(time);//默认到车时间
		transportLineEsDTO.setBranchType(TransportLineBranchTypeEnum.DEPATR_BRANCH.getType());//网点类型
		
		transportLineEsDTO.setSealNo( tmsTransport.getTransportInfo().getSealNo());//铅封号
		transportLineEsDTO.setLoadRemark( tmsTransport.getTransportInfo().getRemark() ); //装货备注
		transportLineEsDTO.setStowagePlannerId( tmsTransport.getTransportInfo().getStowageId() );//配载员
		transportLineEsDTO.setStowagePlannerName(tmsTransport.getTransportInfo().getStowageName());//配载员姓名
	 
		transportLineEsDTO.setBranchId(branchId);//网点ID
		transportLineEsDTO.setBranchName(branchName);//网点名称
		transportLineEsDTO.setEstimatedArrivalTime(time);//默认当前预计到达时间
		transportLineEsDTO.setOperateType(TransportLineOperateTypeEnum.LOAD.getType());//操作方式默认装
		transportLineEsDTO.setSort(0);//排序
		//计算网点装车
		this.initCountTransportLine(tmsTransport.getTransportInfo().getCheckQuality(), tmsTransport.getTransportInfo().getCheckVolume(), transportLineEsDTO,  tmsTransport.getTmsTransportDetailList());

		return transportLineEsDTO;
		
	}
	/**
	 * 始发网点装载计算
	 * @param checkQuality
	 * @param checkVolume
	 * @param transportLineEsDTO
	 * @param newTmsTransportDetailDTOList
	 */
	private void initCountTransportLine(BigDecimal checkQuality ,BigDecimal checkVolume , TransportLineEsDTO transportLineEsDTO, List<TmsTransportDetailDTO> newTmsTransportDetailDTOList) {
        //始发网点        
        transportLineEsDTO.setLoadGoodsAmount(tmsTransport.getTransportInfo().getGoodsAmount());//途径装货件数
        transportLineEsDTO.setLoadWaybillAmount(tmsTransport.getTransportInfo().getWaybillAmount());//总票数
        transportLineEsDTO.setLoadTotalWeight(tmsTransport.getTransportInfo().getTotalWeight());//总重量
        transportLineEsDTO.setLoadTotalVolume(tmsTransport.getTransportInfo().getTotalVolume());//总体积
        transportLineEsDTO.setLoadGoodsAmount(tmsTransport.getTransportInfo().getGoodsAmount());//总件数
        transportLineEsDTO.setLoadBusinessFee(tmsTransport.getTransportInfo().getBusinessFee());//总业务费
        transportLineEsDTO.setLoadOutputValue(tmsTransport.getTransportInfo().getOutputValue());//总产值
        transportLineEsDTO.setLoadCollectPayment(tmsTransport.getTransportInfo().getCollectPayment());//总代收货款
        transportLineEsDTO.setLoadTotalFreight(tmsTransport.getTransportInfo().getTotalFreight());//途径装货总运费
        //计算始发网点在车装载率
	    if(checkQuality !=null && checkQuality.compareTo(BigDecimal.ZERO) ==1 ) {
	    	Integer loadFactorWeight =transportLineEsDTO.getLoadTotalWeight().divide( checkQuality ,2 , BigDecimal.ROUND_UP)
	    			.multiply(new BigDecimal(100)).intValue();// 网点发车装载率 重量
	    	transportLineEsDTO.setLoadFactorWeight(loadFactorWeight);//网点发车装载率 重量
	    }
	    if(checkVolume !=null && checkVolume.compareTo(BigDecimal.ZERO) ==1) {
	    	Integer loadFactorVolume =transportLineEsDTO.getLoadTotalVolume().divide( checkVolume  ,2 , BigDecimal.ROUND_UP)
	    			.multiply(new BigDecimal(100)).intValue()  ;//网点发车装载率 体积
	    	transportLineEsDTO.setLoadFactorVolume(loadFactorVolume);//网点发车装载率 体积
	    }
	    transportLineEsDTO.setLoad(true);//已装货
	    transportLineEsDTO.setLineStatus(transportLineEsDTO.getBranchName()+"网点途经装货");
	}

	/**
	 * 修改线路初始化
	 */
	public void initLineModify() {
		Long time = System.currentTimeMillis();
		  // 初始化运输批次线路信息
        if(tmsTransport.getTransportLineEsDTOList() !=null && tmsTransport.getTransportLineEsDTOList().size()>0) {
        	tmsTransport.getTransportLineEsDTOList().forEach(transportLineDTO -> {
        		transportLineDTO.setUpdateUser(opUserId);
        		transportLineDTO.setUpdateUserName(opUserName);
        		transportLineDTO.setUpdateTime(time);
        		transportLineDTO.setCompanyId(companyId);//操作公司网点
        		transportLineDTO.setCanBeNext(true);//下一网点
        		transportLineDTO.setStatus(TransportLineStatusEnum.NOT_ARRIVED.getType());//路线状态默认“未到达”
        	});
        }

	}



	/**
	 * 修改 ： 筛选当前网点可操作的运单
	 */
	public void filterTmsTransportDetailList() {
		if(CollectionUtils.isNotEmpty(tmsTransport.getTmsTransportDetailList())) {
			Iterator<TmsTransportDetailDTO> it = tmsTransport.getTmsTransportDetailList().iterator();
			while(it.hasNext()){
				if( ! Objects.equal(branchId, it.next().getTransportWaybillDetail().getLoadBranchId()))//运单的装货网点ID不等于当前ID则移除
					it.remove();
			}
		}
	}

	/**
	 *  修改：筛选当前可操作的费用
	 */
	public void filterTransportCostDetailList() {
		if(CollectionUtils.isNotEmpty(tmsTransport.getTransportCostDetailList())) {
			Iterator<TransportCostDetailEsDTO> it = tmsTransport.getTransportCostDetailList().iterator();
			while(it.hasNext()){
				if( ! Objects.equal(branchId, it.next().getCreateBranchId()))//删除非当前网点的费用
					it.remove();
			}
		}
	}

	public void checkTransportLine() {
		 if(CollectionUtils.isNotEmpty(tmsTransport.getTransportLineEsDTOList())) {
			 Set<Integer> s = new HashSet<Integer>();
			 for (TransportLineEsDTO line  : tmsTransport.getTransportLineEsDTOList() ) {
		            boolean b = s.add(line.getBranchId());
		            if(!b){
		    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "运输线路不能重复！");
		            }
		        }
		 }
		 if(CollectionUtils.isNotEmpty(tmsTransport.getTransportCostDetailList())) {
			 Set<String> s = new HashSet<String>();
			 for (TransportCostDetailEsDTO cost  : tmsTransport.getTransportCostDetailList() ) {
				 String temp = cost.getFeeType()+cost.getPayBranchId()+cost.getPayeeId();
		            boolean b = s.add(temp);
		            if(!b){
		    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "费用名称、收款方名称、付款方名称不能出现重复！");
		            }
		        }
		 }
		
		 if(tmsTransport.getTransportInfo().getShareWay() !=null ) {
			 if(Objects.equal(tmsTransport.getTransportInfo().getShareWay(), DispatcherFeeShareTypeEnum.BY_WEIGHT.getType())
					 && (tmsTransport.getTransportInfo().getTotalWeight() !=null && tmsTransport.getTransportInfo().getTotalWeight().compareTo(new BigDecimal(0)) ==0 ) ) {
	    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "装车重量为0，请更改分摊方式！");
			 }
			 if(Objects.equal(tmsTransport.getTransportInfo().getShareWay(), DispatcherFeeShareTypeEnum.BY_VOLUME.getType())
					 && (tmsTransport.getTransportInfo().getTotalVolume() !=null && tmsTransport.getTransportInfo().getTotalVolume().compareTo(new BigDecimal(0)) ==0 ) ) {
	    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "装车体积为0，请更改分摊方式！");
			 }
			 if(Objects.equal(tmsTransport.getTransportInfo().getShareWay(), DispatcherFeeShareTypeEnum.BY_OUTPUT.getType())
					 && (tmsTransport.getTransportInfo().getOutputValue() !=null && tmsTransport.getTransportInfo().getOutputValue().compareTo(new BigDecimal(0)) ==0 ) ) {
	    			throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "产值为0，请更改分摊方式！");
			 }
		 }
	}

	public void checkRequired() {
		if(CollectionUtils.isEmpty(this.getTmsTransport().getTmsTransportDetailList())) {
			throw new SystemException(ExceptionCodeConstants.ERROR_TRANSPORT_CREATE, "未选择运单");
		}
		for(TmsTransportDetailDTO detail : this.getTmsTransport().getTmsTransportDetailList()) {
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
    				throw new SystemException(ExceptionCodeConstants.ERROR_WAY_OF_LOADING, "货物装车件数、装车重量、装车体积不能为空！");
    			}
    		}
		}
	}
    
}
