package com.hivescm.tms.api.dto.es.alteration.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/24
 */
@Data
@ToString
public class AlterationDeliveryChangeDeleteReqDTO {
    /**
     * ID
     */
    @Mapping
    private @ApiModelProperty("id") Long id;

    /**
     * 当前网点id
     */
    @Mapping
    private @ApiModelProperty(value = "当前网点id",hidden = true) Long curOrgId;

    /**
     * 公司id
     */
    @Mapping
    private @ApiModelProperty(value = "公司id",hidden = true) Long companyId;
    /**
     * 修改人ID
     */
    @Mapping
    private @ApiModelProperty(value = "修改人ID",hidden = true) Integer updateUser;
    /**
     * 修改人
     */
    @Mapping
    private @ApiModelProperty(value = "修改人姓名",hidden = true) String updateUserName;
}
