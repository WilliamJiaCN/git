package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportDetailDTO;
import com.hivescm.tms.api.enums.biz.transport.DepartTypeEnum;
import com.hivescm.tms.api.enums.biz.transport.TransportWaybillStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:单票添加请求参数
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/18 16:35
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SaveTransportWaybillReqDTO implements Serializable{

    private static final long serialVersionUID = 8656668493712755609L;

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

    @ApiModelProperty("集团ID")
    private Long groupId;


    /**
     * 到达网点
     */
    @ApiModelProperty(value = "到货网点",required = true)
    @Required
    private Integer arriveBranchId;

    /**
     * 到达网点名称
     */
    @ApiModelProperty(value = "到达网点名称",required = true)
    private String arriveBranchName;

    @ApiModelProperty(value = "发车批次ID",required = true)
    @Required
    private Long transportId;

    @ApiModelProperty(value = "运单号",required = true)
    @Required
    private String waybillCode;

    @ApiModelProperty(value = "运单ID",required = true)
    @Required
    private Long waybillId;

    @ApiModelProperty(value = "库存ID",required = true)
    @Required
    private Long stockId;

    /**
     * 发车批次明细信息列表
     */
    @ApiModelProperty("发车批次明细信息列表")
    private List<TmsTransportDetailDTO> tmsTransportDetailList;

    public void initData(TransportInfoEsDTO transportInfoEsDTO){
        // 初始化运输批次运单、货物信息
        if(CollectionUtils.isNotEmpty(this.getTmsTransportDetailList())) {
            this.getTmsTransportDetailList().forEach(tmsTransportDetailDTO -> {
                tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUser(opUserId);//创建人
                tmsTransportDetailDTO.getTransportWaybillDetail().setCreateUserName(opUserName);//操作人姓名
                tmsTransportDetailDTO.getTransportWaybillDetail().setCreateTime(System.currentTimeMillis());//创建时间
                tmsTransportDetailDTO.getTransportWaybillDetail().setCompanyId(companyId);//公司ID
                tmsTransportDetailDTO.getTransportWaybillDetail().setLoadTime(System.currentTimeMillis());//装货时间
                tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchId(branchId);//装货网点ID
                tmsTransportDetailDTO.getTransportWaybillDetail().setLoadBranchName(branchName);//装货网点名称
                tmsTransportDetailDTO.getTransportWaybillDetail().setTransportId(transportId);//批次ID
                tmsTransportDetailDTO.getTransportWaybillDetail().setUnload(false);//是否已经卸载
                tmsTransportDetailDTO.getTransportWaybillDetail().setStatus(TransportWaybillStatusEnum.LOAD.getType());//状态“已装车”
                tmsTransportDetailDTO.getTransportWaybillDetail().setDepartType(DepartTypeEnum.FEEDER.getType());//发车批次类型

                //冗余批次信息
                tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBatch(transportInfoEsDTO.getDepartBatch());//批次号
                tmsTransportDetailDTO.getTransportWaybillDetail().setDepartType(transportInfoEsDTO.getDepartType());//发车批次类型
                tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchId(transportInfoEsDTO.getDepartBranchId());//发车网点
                tmsTransportDetailDTO.getTransportWaybillDetail().setDepartBranchName(transportInfoEsDTO.getDepartBranchName());//发车网点名称
                tmsTransportDetailDTO.getTransportWaybillDetail().setBatchDepartTime(transportInfoEsDTO.getDepartTime());//发车时间
                tmsTransportDetailDTO.getTransportWaybillDetail().setEstimatedArrivalTime(transportInfoEsDTO.getEstimatedArrivalTime());//预计到达时间
                tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchId(transportInfoEsDTO.getArrivalBranchId());//终端网点
                tmsTransportDetailDTO.getTransportWaybillDetail().setArrivalBranchName(transportInfoEsDTO.getArrivalBranchName());//终端网点名称
                tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleId(transportInfoEsDTO.getVehicleId());//车辆ID
                tmsTransportDetailDTO.getTransportWaybillDetail().setVehicleNo(transportInfoEsDTO.getVehicleNo());//车牌号
                tmsTransportDetailDTO.getTransportWaybillDetail().setDriverId(transportInfoEsDTO.getDriverId());//司机ID
                tmsTransportDetailDTO.getTransportWaybillDetail().setDriverName(transportInfoEsDTO.getDriverName());//司机姓名
                tmsTransportDetailDTO.getTransportWaybillDetail().setDriverPhoneNo(transportInfoEsDTO.getDriverPhoneNo());//司机手机号

                tmsTransportDetailDTO.getTransportGoodsDetailList().forEach(transportGoodsDetailDTO -> {
                    transportGoodsDetailDTO.setCreateUser(opUserId);//创建人
                    transportGoodsDetailDTO.setCreateUserName(opUserName);//操作人姓名
                    transportGoodsDetailDTO.setCreateTime(System.currentTimeMillis());//创建时间
                    transportGoodsDetailDTO.setCompanyId(companyId);//公司ID
                    transportGoodsDetailDTO.setBranchId(branchId.longValue());//装车网点ID
                    transportGoodsDetailDTO.setTransportId(transportId);
                });
            });
        }
    }
}
