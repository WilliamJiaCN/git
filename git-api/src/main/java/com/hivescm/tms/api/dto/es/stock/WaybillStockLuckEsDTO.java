package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@ToString
@Data
public class WaybillStockLuckEsDTO implements Serializable {


    private static final long serialVersionUID = 5595072043484691447L;

    @Required
    @ApiModelProperty(value="运单明细ID")
    private Long stockDetailId; // waybillStockDetail.id

    @Required
    @ApiModelProperty(value="所需操作件数") // 返库件数
    private Integer lockNum;
    
    @ApiModelProperty(value="拒签锁定库存数量")
    private Integer refuseSignlockNum;

    @ApiModelProperty(value = "签收返库类型 1部分签收  2全部拒签 3派送失败    暂时没有4取消签收 ")
    private Integer signStatus;

    @ApiModelProperty(value = "签收状态")
    private Integer signType;

    @ApiModelProperty(value="公司ID")
    private Long companyId;

    @ApiModelProperty(value = "网点ID")
    private Integer branchId;

    @ApiModelProperty(value = "运单号")
    private String code;

    @ApiModelProperty(value = "运单ID")
    private Long waybillId;

    @ApiModelProperty(value = "业务类型")
    private Integer stockStatus;

    //应用场景：调拨入库，网点入库，创建运单，取消发车（派车单，发车配载），送货签收返回入库，到货确认
    @ApiModelProperty(value = "仓库名称")
    private String houseName;

    @ApiModelProperty(value = "仓库ID")
    private Integer houseId;

    @ApiModelProperty(value = "仓库类型 默认为当前网点仓库 1为发货仓 2为到货仓")
    private Integer houseType;


    //冗余参数，仅作为判断条件
    @ApiModelProperty(value = "业务类型", required = true)
    private StockLockTypeEnum stockLockType;
}
