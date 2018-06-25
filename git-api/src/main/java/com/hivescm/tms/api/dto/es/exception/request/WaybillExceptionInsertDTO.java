package com.hivescm.tms.api.dto.es.exception.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillExceptionInsertDTO implements Serializable {

	private static final long serialVersionUID = -816616775473145008L;

    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty(value="公司ID",hidden=true)
    private Long companyId;

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
    @ApiModelProperty(value="是否删除 1已删除 0未删除",hidden=true)
    private Boolean isDelete;

    @Mapping
    @ApiModelProperty(value="登记人ID")
    private Integer registerUserId;

    @Mapping
    @ApiModelProperty(value="登记人姓名")
    private String registerUserName;
    
    @Mapping
    @ApiModelProperty(value="登记方ID")
    private Integer registerBranchId;
    
    @Mapping
    @ApiModelProperty(value="登记方名称")
    private String registerBranchName;

    @Mapping
    @ApiModelProperty(value="登记时间")
    private Long registerTime;

    @Mapping
    @ApiModelProperty(value="修改人ID 异常单最后一次的修改人",hidden=true)
    private Integer updateUserId;

    @Mapping
    @ApiModelProperty(value="修改人姓名",hidden=true)
    private String updateUserName;

    @Mapping
    @ApiModelProperty(value="修改人时间 异常单最后一次的修改时间" ,hidden=true)
    private Long updateTime;
}
