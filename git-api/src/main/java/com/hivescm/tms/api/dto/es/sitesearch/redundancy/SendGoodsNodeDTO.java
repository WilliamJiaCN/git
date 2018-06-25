package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 送货信息
 * 运单根据做派车单时判断是如果从库存提取的运单做送货， 则提取派车单信息
 * @author ke.huang
 * @date 2017年9月20日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SendGoodsNodeDTO implements Serializable{
	private static final long serialVersionUID = -1438797987808660640L;
	@ApiModelProperty("通知时间")
	private Long noticeTime;//通过到货通知管理模块推送短信或者人工录入电话通知的时间后所记录的通知时间
	@ApiModelProperty("派车批次")
    private String batchCode;//派车单批次
	@ApiModelProperty("送货网点名称")
    private String branchName;//派车单的派车网点
	@ApiModelProperty("送货件数")
    private Integer packageNum;//派车单中的装车件数
	@ApiModelProperty("送货时间")
    private Long dispatcherTime;//派车单的派车时间
	@ApiModelProperty("车牌号码")
    private String vehicleNo;//派车单的车牌号码
	@ApiModelProperty("司机姓名")
    private String driverName;//派车单的司机姓名
	@ApiModelProperty("手机号码")
    private String phoneNo;//派车单的手机号码
	@ApiModelProperty("备注")
    private String remark;//派车单的备注
	@ApiModelProperty("经办人")
    private String releaseUserName;//派车单的制单人
	@ApiModelProperty("是否自提改送货")
	private Boolean iChange;//取自提改送货批次
}
