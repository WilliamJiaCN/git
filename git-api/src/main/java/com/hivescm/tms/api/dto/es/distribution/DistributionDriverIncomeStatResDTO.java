package com.hivescm.tms.api.dto.es.distribution;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/19
 */
@SuppressWarnings("rawtypes")
@Data
@ToString
public class DistributionDriverIncomeStatResDTO implements Serializable ,Comparable{
    private static final long serialVersionUID = -534007561758963827L;

    @Mapping
    @ApiModelProperty(value = "公司ID", required = true)
    private Long companyId;

    @Mapping
    @ApiModelProperty("司机ID")
    private Integer driverId;
    
    @Mapping
    @ApiModelProperty("司机ID")
    private String driverName;
    
    @Mapping
    @ApiModelProperty("司机头像")
    private String  headUrl;
    
    @Mapping
   	@ApiModelProperty("今日分摊成本(四种分摊成本总合)")
   	private BigDecimal todayCost;
       
    @Mapping
   	@ApiModelProperty("今日完成单数")
   	private Integer todayDetailNum;
    
    @Mapping
	@ApiModelProperty("昨日分摊成本(四种分摊成本总合)")
	private BigDecimal yesterdayCost;
    
    @Mapping
	@ApiModelProperty("昨日完成单数")
	private Integer yesterdayDetailNum;
    
    @ApiModelProperty("昨日荣誉榜")
    List<DistributionDriverIncomeStatResDTO> respList= new ArrayList<DistributionDriverIncomeStatResDTO>();

	@Override
	public int compareTo(Object o) {
		DistributionDriverIncomeStatResDTO respDTO = (DistributionDriverIncomeStatResDTO)o;
		Integer otherDetailNum = respDTO.getYesterdayDetailNum();
		BigDecimal otherCost = respDTO.getYesterdayCost();
		if(this.yesterdayDetailNum < otherDetailNum) {
			return 1;
		}else if(this.yesterdayDetailNum > otherDetailNum) {
			return -1;
		}else if(this.yesterdayDetailNum == otherDetailNum) {
			if(this.yesterdayCost.compareTo(otherCost)==1) {
				return -1;
			}else if(this.yesterdayCost.compareTo(otherCost)==-1) {
				return 1;
			}if(this.yesterdayCost.compareTo(otherCost)==0) {
				return 0;
			}
		}
		return 0;
	}

}
