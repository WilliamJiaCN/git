package com.hivescm.tms.api.dto.es.sign.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/23
 */
@Data
@ToString
public class SignWaybillsRespDTO {

    @ApiModelProperty("签收运单列表")
    private List<SignWaybillElementDTO> signWaybillElement;
    @ApiModelProperty("总运单数")
    private int totalWaybillNum;

    @ApiModelProperty("总开单件数")
    private int totalCreateNum;
    @ApiModelProperty("总未签件数")
    private int totalUnSignNum;
    @ApiModelProperty("总重量")
    private int totalWeight;
    @ApiModelProperty("总体积")
    private int totalVolume;
    @ApiModelProperty("总拒签件数")
    private int totalRefuseSignNum;
    @ApiModelProperty("实际运费")
    private BigDecimal totalActualWayFee;


    /**
     * 总数量
     */
    @ApiModelProperty("总数量")
    private int totalCount;
    /**
     * 当前页码
     */

    @ApiModelProperty("当前页码")
    private int currentPage;
    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private int pageSize;


//    public void summary(){
//
//
//
//
//
//    }


}
