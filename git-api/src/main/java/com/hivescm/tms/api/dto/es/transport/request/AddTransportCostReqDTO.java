package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 添加运输批次费用请求对象
 * @author 张文龙
 * @since 2017/9/06
 */
@Data
@ToString
public class AddTransportCostReqDTO implements Serializable {
    private static final long serialVersionUID = -532151097077810783L;


    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**当前网点
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;
    
    /**
     * 运输批次
     */
    @Required
    @ApiModelProperty(value = "运输批次*",required = true)
    private String departBatch;
    
    /**
     * 运输批次Id
     */
    @Required
    @ApiModelProperty(value = "运输批次Id*",required = true)
    private Long transportId;
    
    @ApiModelProperty(value = "运输批次费用信息*")
    private List<TransportCostDetailEsDTO> transportCostDetailEsDTOList;

}
