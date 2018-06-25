/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherTaskEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class AppQueryReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("参数")
	@Required
	@Logger
	private String param;
	
	@ApiModelProperty("网点id")
	private Integer orgId;
	
	@ApiModelProperty("公司id")
	private Long companyId;
	
	@ApiModelProperty(value = "签收状态(NO_SIGN= 未签收 SIGNED = 全部签收 PARTIAL_SIGN =部分签收 REFUSE_SIGN= 全部拒签)")
	@Required
	private SignStatusEnum signStatus;
	
	@ApiModelProperty(value = "自提=GET，送货=DELIVERY")
	@Required
	private WaybillDistributionTypeEnum distributionType;
	
	@Required
	@ApiModelProperty("分页 - 每页显示数")
	private Integer pageSize;
	
	@Required
	@ApiModelProperty("分页 - 当前页数")
	private Integer currentPage;


	public List<SearchCondition> toDeliverySearchCondition(){



		List<SearchCondition> list = new ArrayList<>();
//        List<String> clientList = this.getClient();

		//排除 发车之前的状态
		/*
		 *
		 * ASSIGNED(1, "已指派"),
		 * ACCEPTED(2, "已接单"),
		 * LOADED(3, "已装货"),
		 * NOT_DEPARTING(4, "未发车"),
		 * CANCELED(7, "已撤单"),
		 * DISCARD(8, "已作废"),
		 */
		Integer[] dispatcherStatusArray={
				DispatcherStatusEnum.ASSIGNED.getType(),
				DispatcherStatusEnum.ACCEPTED.getType(),
				DispatcherStatusEnum.LOADED.getType(),
				DispatcherStatusEnum.CANCELED.getType(),
				DispatcherStatusEnum.NOT_DEPARTING.getType(),
				DispatcherStatusEnum.DISCARD.getType(),
		};

		SearchCondition dispatcherStatusSc = SearchConditionUtils.newNotInCondition("status", dispatcherStatusArray);
		list.add(dispatcherStatusSc);

		//过滤掉 城配单子,只查询零担单子

		Integer[] waybillTypeArray={
				WaybillTypeEnum.SELFENTRANCE.getType(),
				WaybillTypeEnum.APP.getType()
		};


		SearchCondition waybillTypeSC = SearchConditionUtils.newInCondition("waybillType",waybillTypeArray);

		list.add(waybillTypeSC);

		//过滤掉 提货送货 和  提货送货 的单子  fixme 为了 演示 先只查出库送货
		SearchCondition taskIdSC = SearchConditionUtils.newUnEqualCondition("taskId", DispatcherTaskEnum.PICKINBAND.getType());

		list.add(taskIdSC);


//         当前网点->>运单中的目的网点  fixme 目的网点id运单为空
//        if (null != this.getCurDotId() && this.getCurDotId() > 0) {
////            SearchCondition branchId = SearchConditionUtils.newEqualCondition("invoiceOrgId",
//            SearchCondition branchId = SearchConditionUtils.newEqualCondition("destOrgId",
//                    this.getCurDotId());
//            list.add(branchId);
//        }

		if (null != this.getCompanyId() && this.getCompanyId() > 0) {
			SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
					this.getCompanyId());
			list.add(companyId);
		}

		// 签收状态

		if (this.getSignStatus() != null) {
			if (this.getSignStatus()==SignStatusEnum.NO_SIGN){
				SearchCondition signTypeSc = SearchConditionUtils.newEqualCondition("signType", SignStatusEnum.NO_SIGN.getType());

				SearchCondition statusSc = SearchConditionUtils.newEqualCondition("status", DispatcherDetailStatusEnum.DEPARTED.getType());

				list.add(signTypeSc);
				list.add(statusSc);

			}else {

				SearchCondition signStatus = SearchConditionUtils.newEqualCondition("signType",
						this.getSignStatus().getType());
				list.add(signStatus);
			}
		}
		return list;
	}
}
