package com.hivescm.tms.api.dto.print.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class WaybillLablePrintDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private @ApiModelProperty("ES主键") Long id;

	private @Mapping  @ApiModelProperty("公司id") Integer companyId;

	private @Mapping @ApiModelProperty("运单号") String code;

	private @Mapping @ApiModelProperty("标签号") String lableCode;

	private @Mapping  @ApiModelProperty("打印人") Integer printUser;

	private @Mapping  @ApiModelProperty("打印人姓名") String printUserName;

	private @Mapping  @ApiModelProperty("打印时间") Long printTime;

	private @Mapping  @ApiModelProperty("标签补打") Boolean isPrintAgain;

	private @Mapping  @ApiModelProperty("标签补打人") Integer printAgainUser;

	private @Mapping  @ApiModelProperty("打印人姓名") String printAgainUserName;

	private @Mapping  @ApiModelProperty("标签补打时间") Long printAgainTime;

	private @Mapping  @ApiModelProperty("标签作废") Boolean isDiscard;

	private @Mapping  @ApiModelProperty("作废类型") Integer discardType;

	private @Mapping  @ApiModelProperty("作废备注") String discardRemark;

	private @Mapping  @ApiModelProperty("作废人") Integer discardUser;

	private @Mapping  @ApiModelProperty("作废人姓名") String discardUserName;

	private @Mapping  @ApiModelProperty("创建人") Integer createUser;

	private @Mapping  @ApiModelProperty("创建人姓名") String createUserName;

	/*-------------------------------扫描修改-------------------------------------------*/
	private @Mapping  @ApiModelProperty("是否已装车") Boolean load;

	private @Mapping  @ApiModelProperty("是否已到货") Boolean unLoad;

	private @Mapping  @ApiModelProperty("运单总件数") Integer totalNum;

	private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;

	private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;

	private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;

	private @Mapping @ApiModelProperty("运单ID") Long waybillId;

	private  @Mapping @ApiModelProperty("网点ID") Integer branchId;

}
