package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>Title: StockLockSyncDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-11-20:07
 */
@ToString
@Data
public class StockLockSyncDTO implements Serializable {
    private static final long serialVersionUID = -4814129185114656903L;

    @Required  @Logger
    @ApiModelProperty(value = "网点ID",required = true)
    private Integer branchId;
    @Required
    @Logger
    @ApiModelProperty(value = "公司ID", required = true)
    private Long companyId;
    @Required
    @Logger
    @ApiModelProperty(value = "运单ID")
    private Long waybillId;
    @Required
    @Logger
    @ApiModelProperty(value = "库存明细ID", required = true)
    private Long stockDetailId;

    @ApiModelProperty(value = "业务类型",required=true)
    private StockLockTypeEnum stockLockType;

    @ApiModelProperty(value = "运单号")
    private String code;
    @ApiModelProperty
    @Logger(value = "操作件数") //签收操作（包括自提，送货）之外 其他业务需要参数
    private Integer packageNum;
    @ApiModelProperty
    @Logger(value = "签收件数")
    private Integer signNum;    //签收操作所需参数
    @ApiModelProperty
    @Logger(value = "拒签件数")
    private Integer refusalNum; //签收操作所需参数


    //应用场景：调拨入库，网点入库，创建运单，取消发车（派车单，发车配载），送货签收返回入库，到货确认
    @ApiModelProperty(value = "仓库ID")
    private Integer houseId;//调拨入库，网点入库，创建运单（默认的是发货仓），送货签收返回入库，到货确认 必填。   取消发车（派车单，发车配载）不必填
    @ApiModelProperty(value = "仓库名称")
    private String houseName;
    @ApiModelProperty(value = "仓库类型 默认为当前网点仓库 1为发货仓 2为到货仓")
    private Integer houseType;

}
