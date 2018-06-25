package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/9
 */

@Data
@ToString
public class CancelDeliverySignReqDTO {
    @Required
    @ApiModelProperty("公司ID")
    private  Long companyId;

    @Required
    @ApiModelProperty("当前网点ID")
    private Integer curentOrgId;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    @ApiModelProperty("取消签收单ids")
    private List<Long> signIds;


}
