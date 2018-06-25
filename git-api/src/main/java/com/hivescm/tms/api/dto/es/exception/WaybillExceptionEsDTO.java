package com.hivescm.tms.api.dto.es.exception;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单异常信息表
 */
@Data
@ToString
public class WaybillExceptionEsDTO implements Serializable {
    private static final long serialVersionUID = 5032057672331416732L;

    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    @Mapping
    @ApiModelProperty("运单号")
    private String code ;

    @Mapping
    @ApiModelProperty("异常单号")
    private String exceptionCode;

    @Mapping
    @ApiModelProperty("发生时间  异常单登记的异常发生时间")
    private Long happenTime;

    @Mapping
    @ApiModelProperty("异常状态 1.未提交 2.未处理 3.处理中 4.已办结")
    private Integer waybillExceptionStatus;
    
    @Mapping
    @ApiModelProperty("异常状态 1.未提交 2.未处理 3.处理中 4.已办结")
    private String waybillExceptionStatusName;

    @Mapping
    @ApiModelProperty("反馈网点ID  默认为异常登记网点")
    private Integer feedbackBranchId;

    @Mapping
    @ApiModelProperty("反馈网点名字")
    private String feedbackBranchName;

    @Mapping
    @ApiModelProperty("现场反馈人ID")
    private Integer feedbackUserId;

    @Mapping
    @ApiModelProperty("现场反馈人姓名")
    private String feedbackUserName;

    @Mapping
    @ApiModelProperty("异常类型 数据字典 破损、少货、多货、超重超方等")
    private Integer exceptionType;
    
    @Mapping
    @ApiModelProperty("异常类型 数据字典 破损、少货、多货、超重超方等")
    private String exceptionTypeName;

    @Mapping
    @ApiModelProperty("异常商品名称  包含异常商品名和数量")
    private String exceptionGoods;
    
    @Mapping
    @ApiModelProperty("协商金额")
    private BigDecimal exceptionMoney;

    @Mapping
    @ApiModelProperty("是否产生损失  默认为否，标记后为是")
    private Boolean isCauseLoss;

    @Mapping
    @ApiModelProperty("异常描述信息  200字符以内")
    private String exceptionDesc;

    @Mapping
    @ApiModelProperty("反馈网点处理建议  200字符以内")
    private String feedbackBranchAdvise;

    @Mapping
    @ApiModelProperty("异常图片")
    private String exceptionPic;

    @Mapping
    @ApiModelProperty("异常发生原因 1.内部 2.承运商 3.客户 4.不可抗")
    private Integer reason;
    
    @Mapping
    @ApiModelProperty("异常发生原因 1.内部 2.承运商 3.客户 4.不可抗")
    private String reasonName;

    @Mapping
    @ApiModelProperty("处理方式 1.理赔 2.罚款 3.赔偿货损 4.理赔/罚款 5.无需处理")
    private Integer dealType;
    
    @Mapping
    @ApiModelProperty("处理方式 1.理赔 2.罚款 3.赔偿货损 4.理赔/罚款 5.无需处理")
    private String dealTypeName;

    @Mapping
    @ApiModelProperty("处理人ID")
    private Integer dealUserId;
    
    @Mapping
    @ApiModelProperty("处理人")
    private String dealUserName;

    @Mapping
    @ApiModelProperty("处理方Id")
    private Integer dealBusinessNetworkId;
    
    @Mapping
    @ApiModelProperty("处理方")
    private String dealBusinessNetworkName;

    @Mapping
    @ApiModelProperty("罚款金额   责任方罚款总金额")
    private BigDecimal forfeitAmount;

    @Mapping
    @ApiModelProperty("付款网点ID")
    private Integer payBranchId;

    @Mapping
    @ApiModelProperty("付款网点名称")
    private String payBranchName;

    @Mapping
    @ApiModelProperty("理赔对象是否VIP")
    private Boolean claimTargetIsVip;
    
    @Mapping
    @ApiModelProperty("理赔对象id")
    private Integer claimTargetId;
    
    @Mapping
    @ApiModelProperty("理赔对象")
    private String claimTarget;

    @Mapping
    @ApiModelProperty("理赔金额")
    private BigDecimal claimAmount;

    @Mapping
    @ApiModelProperty("处理结果判定意见")
    private String handResultJudgeAdvise;

    @Mapping
    @ApiModelProperty(value="是否删除 1已删除 0未删除",hidden=true)
    private Boolean isDelete;

    @Mapping
    @ApiModelProperty(value="办结人ID")
    private Integer closeUserId;

    @Mapping
    @ApiModelProperty(value="办结人姓名")
    private String closeUserName;

    @Mapping
    @ApiModelProperty(value="办结时间")
    private Long closeTime;

    @Mapping
    @ApiModelProperty(value="登记人ID")
    private Integer registerUserId;

    @Mapping
    @ApiModelProperty(value="登记人姓名")
    private String registerUserName;

    @Mapping
    @ApiModelProperty(value="登记时间")
    private Long registerTime;
    
    
    @Mapping
    @ApiModelProperty(value="登记方ID")
    private Integer registerBranchId;
    
    @Mapping
    @ApiModelProperty(value="登记方名称")
    private String registerBranchName;


    @Mapping
    @ApiModelProperty(value="修改人ID 异常单最后一次的修改人",hidden=true)
    private Integer updateUserId;

    @Mapping
    @ApiModelProperty(value="修改人姓名",hidden=true)
    private String updateUserName;

    @Mapping
    @ApiModelProperty(value="修改人时间 异常单最后一次的修改时间" ,hidden=true)
    private Long updateTime;

    /********************************运单冗余字段************************************/

    @Mapping
    @ApiModelProperty("发货人id")
    private Long invoiceUserId;

    @Mapping
    @ApiModelProperty("发货人")
    private String invoiceUser;

    @Mapping
    @ApiModelProperty("发货人电话")
    private String invoiceTelNo;

    @Mapping
    @ApiModelProperty("收货人Id")
    private Long receiptUserId;

    @Mapping
    @ApiModelProperty("收货人")
    private String receiptUser;

    @Mapping
    @ApiModelProperty("收货人电话")
    private String receiptConsignorTelNo;

    @Mapping
    @ApiModelProperty("录单时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("发货网点ID")
    private Integer invoiceOrgId;

    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;

    @Mapping
    @ApiModelProperty("始发地ID  运单发货地的名称")
    private String invoiceCityId;

    @Mapping
    @ApiModelProperty("始发地")
    private String invoiceCityName;

    @Mapping
    @ApiModelProperty("目的网点Id 运单发货网点名称")
    private Integer destOrgId;

    @Mapping
    @ApiModelProperty("目的网点名称")
    private String destOrgName;

    @Mapping
    @ApiModelProperty("目的地id  运单目的网点名称")
    private String destId;

    @Mapping
    @ApiModelProperty("目的地名称")
    private String destName;

    @Mapping
    @ApiModelProperty("运单状态")
    private Integer status;
    
	@Mapping
	@ApiModelProperty("运单状态名称") 
	private String statusName;

    @Mapping
    @ApiModelProperty("配送方式  1.自提  2.送货")
    private Integer distributionType;

    @Mapping
    @ApiModelProperty("配送方式  1.自提  2.送货")
    private String distributionTypeName;

    @Mapping
    @ApiModelProperty("货物类型名称   冗余商品表类型，以‘/’间隔")
    private String goodsTypeName;

    @Mapping
    @ApiModelProperty("包装名称  运单中货物的包装单位")
    private String packingName;

    @Mapping
    @ApiModelProperty("总件数")
    private Integer totalNum;

    @Mapping
    @ApiModelProperty("总重量")
    private BigDecimal weight;

    @Mapping
    @ApiModelProperty("总体积")
    private BigDecimal volume;

    @Mapping
    @ApiModelProperty("回单要求")
    private String backType;
    @Mapping
    @ApiModelProperty("回单要求")
    private Integer backTypeValue;

    @Mapping
    @ApiModelProperty("回单份数")
    private Integer backNum;
}