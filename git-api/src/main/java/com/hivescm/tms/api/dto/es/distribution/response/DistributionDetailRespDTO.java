package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class DistributionDetailRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 /**
     * 运单号
     */
    @ApiModelProperty("运单id")
    private Long waybillId;
    /**
     * 收货人详细地址
     */
    @ApiModelProperty("收货人详细地址")
    private String receiptAddress;
    
    /**
     * 收货人手机号码
     */
    @ApiModelProperty("收货人手机号码")
    private String receiptConsignorMobleNo;
    
    /**
     * 收货人
     */
    @ApiModelProperty("收货人")
    private String receiptUser;
    /**
     * 发货人详细地址
     */
    @ApiModelProperty("发货人详细地址")
    private String invoiceAddress;
    
    /**
     * 发货人手机号码
     */
    @ApiModelProperty("发货人手机号码")
    private String invoiceMobleNo;
    /**
     * 发货人
     */
    @ApiModelProperty("发货人")
    private String invoiceUser;
    
    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;
    
    /**
     * 件数
     */
    @Mapping
    @ApiModelProperty("件数")
    private Integer packageNum;
    
    /**
     * 重量
     */
    @Mapping
    @ApiModelProperty("重量")
    private BigDecimal weight;

    /**
     * 体积
     */
    @Mapping
    @ApiModelProperty("体积")
    private BigDecimal volume;
    
    public DistributionDetailRespDTO sum(DispatcherGoodsEsDTO t){
    	this.waybillId = t.getWaybillId();
        this.goodsName = t.getGoodsName()+"/"+this.goodsName;
        this.weight.add(t.getWeight());
        this.volume.add(t.getVolume());
        this.packageNum += t.getPackageNum();
        return this;
    }
}
