package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.transport.TransportArrivalCostDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/31 9:43
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TransportArrivalCostReqDTO implements Serializable{

    private static final long serialVersionUID = -3178937466220562864L;

    @Required
    @ApiModelProperty(value = "到货批次ID",required=true)
    private Long id;

    @Required
    @ApiModelProperty(value = "公司id",required=true)
    private Long companyId;

    @Required
    @ApiModelProperty(value = "网点ID",required=true)
    private Integer branchId;

    @ApiModelProperty("网点名称")
    private String branchName;

    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;


    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;


    @ApiModelProperty("删除的费项ID")
    private List<Long> costId;


    @ApiModelProperty("新增修改的费用")
    private List<TransportArrivalCostDetailEsDTO> list;

    @Required
    @ApiModelProperty("发车批次总费用")
    private BigDecimal cost;
}
