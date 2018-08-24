package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 拒绝签收
 *
 * @author 杨彭伟
 * @since 2017/11/9 11:33
 */
@Data
@ToString
public class RefuseForSignReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("userId")
    private Integer userId;
    @ApiModelProperty("运单id") @Logger
    private Long waybillId;
    @ApiModelProperty("派车单id") @Logger
    private Long dispatcherId;
    @ApiModelProperty("公司id")
    private Long companyId;
    @ApiModelProperty("签收状态:2.部分签收 3.拒绝签收")
    private Integer signStatus;
    @ApiModelProperty("收款方式(1=现金支付,2=二维码支付)")
    private Integer settlementMethod;
    @ApiModelProperty("包裹详情")
    private List<RejectPackage> rejectPackages;
    @ApiModelProperty("拒收原因")
    private String refuseCause;
    @ApiModelProperty("是否装车")
    private Boolean loaded;
}
