package com.hivescm.tms.api.dto.es.outbill.resp;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.outbill.OutBillTrackEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class OutBillEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("外发id")
    private Long outbillId;
    @Mapping
    @ApiModelProperty("外发单号")
    private String outbillNum;
    @Mapping
    @ApiModelProperty("外发批次号")
    private String outGoCode;
    @Mapping
    @ApiModelProperty("外发公司")
    private String outCompanyNames;
    @Mapping
    @ApiModelProperty("外发时间")
    private Long outGoTime;
    @Mapping
    @ApiModelProperty("外发网点")
    private Integer outBranchId;
    @Mapping
    @ApiModelProperty("外发网点")
    private String outBranchName;
    @Mapping
    @ApiModelProperty("外发件数")
    private Integer packageNum;
    @Mapping
    @ApiModelProperty("外发体积")
    private BigDecimal volume;
    @Mapping
    @ApiModelProperty("外发重量")
    private BigDecimal weight;

    @ApiModelProperty("跟踪信息")
    private List<OutBillTrackEsDTO> outBillTrackEsDTOS;
}
