package com.hivescm.tms.api.dto.es.outbill.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class OutBillSignInfoDTO {
	
	@Required
	@Mapping({"OutBillDetailEsDTO.id"})
	@ApiModelProperty("外发单明细id")
	private Long outBillDetailId;
	
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	@Mapping
	@ApiModelProperty("公司名称")
    private String companyName;
	
	@Mapping
	@ApiModelProperty("集团ID")
    private Integer groupId;
	
	@Required
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	
    @Mapping
	@ApiModelProperty("签收单号")
    private String signNum;
    
    @Mapping
	@ApiModelProperty("签收人")
    private String signName;
    
    @Mapping
	@ApiModelProperty("签收人手机")
    private String signMobile;
    
    @Mapping
	@ApiModelProperty("签收人身份证号")
    private String signIdentityCard;
    
    @Mapping
	@ApiModelProperty("签收时间")
    private Long signTime;
    
    @Mapping
	@ApiModelProperty("签收类型")
    private Integer signType;
    
    @Mapping
	@ApiModelProperty("签收类型名称")
    private String signTypeName;
    
    @Mapping
	@ApiModelProperty("签收说明")
    private String signRemark;
    
    @Mapping
	@ApiModelProperty("签收照片")
    private String signPicture;
    
    @Required
    @Mapping
	@ApiModelProperty("经办人（系统操作员）")
    private Integer signOperator;
    
    @Required
    @Mapping
	@ApiModelProperty("经办人（系统操作员）名称")
    private String signOperatorName;
    
    @Mapping
	@ApiModelProperty("签收状态")
    private Integer signStatus;
    
    @Mapping
   	@ApiModelProperty("签收状态名称")
    private String signStatusName;
    
    @Required
    @Mapping
	@ApiModelProperty("签收件数")
    private Integer signCount;
    
    @Required
    @Mapping
	@ApiModelProperty("拒签件数")
    private Integer refuseSignNum;
    
    @Mapping
	@ApiModelProperty("拒签原因")
    private String refuseSignReason;
    
    @Required
    List<OutbillSignGoodsDTO> signGoodsList;
    
    @Required
    @Mapping
	@ApiModelProperty("是否回单确认收回")
	private Boolean isBackConfirm;

    @Required
	@Mapping
	@ApiModelProperty("是否发送短信给收货人")
	private Boolean isSendmsgReceiver;

    @Required
	@Mapping
	@ApiModelProperty("是否发送短信给发货人")
	private Boolean isSendMsgInvoicer;

}
