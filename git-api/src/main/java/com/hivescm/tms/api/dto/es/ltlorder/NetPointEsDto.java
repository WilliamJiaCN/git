package com.hivescm.tms.api.dto.es.ltlorder;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NetPointEsDto {

	@Mapping
	@ApiModelProperty("货主ID")
	private Integer goodsOwnerId;
	@Mapping
	@ApiModelProperty("发货城市")
    private String sendCity;
	@Mapping
	@ApiModelProperty("经度")
	private Double curlongitude;
	@Mapping
	@ApiModelProperty("纬度")
	private Double curlatitude;
	@Mapping
	@ApiModelProperty("查询条件")
	private String queryCriteria;
	@ApiModelProperty("分页 - 每页显示数")
	private  Integer pageSize = 30;
	@ApiModelProperty("分页 - 当前页数")
	private  Integer currentPage = 1;
	/**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 30;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }
}
