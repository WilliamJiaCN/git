package com.hivescm.tms.api.dto.db.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
 */
@Data
@ToString
public class WarehouseNameAndIdDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8306608508126720920L;

	@ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("仓库类型  (1.发货仓 2.到货仓)")
    private Integer type;

}