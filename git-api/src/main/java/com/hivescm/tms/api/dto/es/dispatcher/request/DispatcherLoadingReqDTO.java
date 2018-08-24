package com.hivescm.tms.api.dto.es.dispatcher.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 派车单请求体
 * TmsDispatcherEsDTO
 *
 * @author lutingting
 * @date 2017年11月11日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcherLoadingReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */

    @ApiModelProperty("公司id")
    private Long companyId;

  
    /**
     * 派车单ID
     */

    @ApiModelProperty("运单Id")
    private List<Long> waybillIds;
    
    @ApiModelProperty("操作人姓名")
    private String operateUserName;

   

  

}
