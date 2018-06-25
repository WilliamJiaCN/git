package com.hivescm.tms.finance.server.dao.entity.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class SignDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;

    @Mapping
    @ApiModelProperty("派车单id")
    private Long dispatcherId;

    @Mapping
    @ApiModelProperty("派车单明细id")
    private Long dispatcherDetailId;

    @Mapping
    @ApiModelProperty("签收批次号")
    private String signBatchNumber;

    @Mapping
    @ApiModelProperty("签收人")
    private String signPeople;

    @Mapping
    @ApiModelProperty("是否同收货人")
    private Boolean isReceiver;

    @Mapping
    @ApiModelProperty("手机号")
    private String phoneNumber;

    @Mapping
    @ApiModelProperty("身份证号")
    private String idCard;

    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;

    @Mapping
    @ApiModelProperty("签收类型")
    private Integer signType;

    @Mapping
    @ApiModelProperty("正常签收状态 NORMAL_SIGN(1,正常签收),ABNORMAL_SIGN(2,异常签收)")
    private Integer normalSignType;

    @Mapping
    @ApiModelProperty("签收说明")
    private String signingInstructions;

    @Mapping
    @ApiModelProperty("是否回单确认收回")
    private Boolean isBackConfirm;

    @Mapping
    @ApiModelProperty("是否发送短信给收货人")
    private Boolean isSendmsgReceiver;

    @Mapping
    @ApiModelProperty("是否发送短信给发货人")
    private Boolean isSendMsgInvoicer;

    @Mapping
    @ApiModelProperty("签收图片")
    private String signPic;

    @Mapping
    @ApiModelProperty("经办人id")
    private Integer handler;

    @Mapping
    @ApiModelProperty("签收件数")
    private Integer signNumber;

    @Mapping
    @ApiModelProperty("拒签件数")
    private Integer refuseNumber;

    @Mapping
    @ApiModelProperty("未签件数")
    private Integer unsignedNumber;

    @Mapping
    @ApiModelProperty("开单件数")
    private Integer createNumber;

    @Mapping
    @ApiModelProperty("应收合计")
    private BigDecimal totalReceivable;

    @Mapping
    @ApiModelProperty("派送失败原因")
    private String deliveryFailureReason;

    @Mapping
    @ApiModelProperty("是否派送失败")
    private Boolean isDeliveryFailure;

    @Mapping
    @ApiModelProperty("收银确认")
    private Boolean isCashierConfirm;

    @Mapping
    @ApiModelProperty("派送类型")
    private Integer deliveryType;

    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;

    @Mapping
    @ApiModelProperty("签收网点id")
    private Integer signOrgId;

    @ApiModelProperty("创建人")
    private Integer createUser;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

}