package com.hivescm.tms.api.dto.es.stock.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 涉及库存查询 (冗余字段）
 * <p>Title: WaybillStockSearchDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 * <p>Company:http://hivescm.com/ </p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-03-24-10:52 AM
 */
@ToString
@Data
public class WaybillStockSearchDTO implements Serializable {

    /**
     * 库存网点集合
     * 默认为当前网点
     */
    @Mapping
    @ApiModelProperty("库存网点")
    private List<Integer> orgId;

    /**
     * 发货仓
     * 到货仓
     */
    @Mapping
    @ApiModelProperty(value="所在仓库")
    private Long warehouseId;

    /**
     * 发货网点
     * 目的网点
     */
    @Mapping
    @ApiModelProperty(value="收发货网点")
    private Integer companyId;

    /**
     * 营业网点集合
     * 默认为空
     */
    @Mapping
    @ApiModelProperty(value = "营业网点")
    private List<Integer> branchType;

    /**
     * 运单信息
     */
    @Mapping({"WaybillEsDTO.id"}) @Logger
    @ApiModelProperty(value="运单ID")
    private String waybillId;


    @ApiModelProperty("入库日期")
    private String storageTime;


    @ApiModelProperty("录单日期")
    private String recordTime;


    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("仓库名称")
    private Long stockName;

}
