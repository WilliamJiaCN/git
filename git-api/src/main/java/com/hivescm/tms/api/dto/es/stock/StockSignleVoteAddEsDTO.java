package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * <p>Title: StockSignleVoteAddEsDTO</p>
 * <p>Description: 单票添加 </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-21-10:49
 */
@Data
@ToString
public class StockSignleVoteAddEsDTO {

    @Required @Logger @Mapping @ApiModelProperty(value = "库存主表ID",required = true)
    private Long stockId;
    @Required @Logger @Mapping @ApiModelProperty(value = "网点ID",required = true)
    private Integer branchId;
    @Required @Logger @Mapping @ApiModelProperty(value = "公司ID",required = true)
    private Long companyId;
    @Logger @Mapping @ApiModelProperty(value = "仓库名称")
    private String houseName;
    @Logger @Mapping @ApiModelProperty(value = "仓库ID")
    private Long houseId;

    @ApiModelProperty(value = "业务类型",required=true)
    private StockLockTypeEnum stockLockType;

    @Mapping @ApiModelProperty("单票添加明细参数")
    private List<WaybillStockDetailEsDTO> waybillStockDetailEsDTOList;
}
