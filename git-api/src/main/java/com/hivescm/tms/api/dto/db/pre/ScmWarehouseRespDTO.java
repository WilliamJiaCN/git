package com.hivescm.tms.api.dto.db.pre;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年11月20日 上午10:11:12
* 
*/
@Data
@ToString
public class ScmWarehouseRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
     * 站点id
     */
	@ApiModelProperty("站点id")
    private Long siteId;
    /**
     * 站点id
     */
    private String siteName;
    /**
     * 仓库id
     */
    private Long warehouseId;
    /**
     * 仓库id
     */
    @ApiModelProperty("")
    private String warehouseName;
    /**
     * 覆盖区域
     */
    private List<ScmWarehouseAreaDTO> coverCity;
    
    /**
     * 根据仓库id分组
     * @return
     */
    public Long groupByWarehouseId() {
    	
		return this.warehouseId;
    	
    }
}
