package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/2 18:08
 * @company 蜂网供应链管理（上海）有限公司
 */

@Data
@ToString
public class VerifyFinanceReceiptEsDTO implements Serializable {

    private static final long serialVersionUID = 1211514617175968319L;


    @Mapping
    @ApiModelProperty("应收ID")
    private Long id;

    @Mapping
    @ApiModelProperty("付款状态")
    private Integer deliveryStatus;

    /**
     * 审核员ID
     */
    @Mapping
    @ApiModelProperty("审核员ID")
    private  Integer checkUserId;
    
    /**
     * 审核员ID
     */
    @Mapping
    @ApiModelProperty("审核员ID")
    private  String checkUserName;

    /**
     * 审核时间
     */
    @Mapping
    @ApiModelProperty("审核时间")
    private  Long checkTime;

    /**
     * 取消审核员ID
     */
    @Mapping
    @ApiModelProperty("取消审核员ID")
    private  Integer cancelCheckUserId;
    
    /**
     * 取消审核员ID
     */
    @Mapping
    @ApiModelProperty("取消审核员ID")
    private  String cancelCheckUserName;

    /**
     * 取消审核时间
     */
    @Mapping
    @ApiModelProperty("取消审核时间")
    private  Long cancelCheckTime;

}
