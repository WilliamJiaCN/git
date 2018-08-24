package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杨彭伟
 * @date 下午2:00:38
 */
@Data
@ToString
public class FinanceReceiptReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("开始时间")
    private Long startTime;

    @Mapping
    @ApiModelProperty("结束时间")
    private Long endTime;

    @Mapping
    @ApiModelProperty("时间类型（1.收款 2.签收 3.收银）")
    private Integer timeType;

    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;

    @Mapping
    @ApiModelProperty(value = "0,全部；1,未收银；2,已收银", notes = "全部界面列表(ARRIVED, REFUSE_ARRIVED),未收银(REFUSE_ARRIVED)收传此处运单状态")
    private Integer financeEnum;

    @Mapping
    @ApiModelProperty("付款方")
    private String payObject;

    @Mapping
    @ApiModelProperty("来源单号(签收单号)") @Logger
    private String sourceNumber;
    @Mapping 
    @ApiModelProperty("来源单号(运单号)") @Logger
    private String waybillId;

    @Mapping
    @ApiModelProperty("派车批次") @Logger
    private String batchCode;
    @Mapping
    @ApiModelProperty(value = "公司id", required = true)
    private Long companyId;
    @Mapping
    @ApiModelProperty(value = "当前网点id", required = true)
    private Long curDotId;
    @Mapping
    @ApiModelProperty(value = "收款单号")
    private String receiptCode;

    @ApiModelProperty("分页 - 每页显示数")
    private Integer pageSize;

    @ApiModelProperty("分页 - 当前页数")
    private Integer currentPage;
}
