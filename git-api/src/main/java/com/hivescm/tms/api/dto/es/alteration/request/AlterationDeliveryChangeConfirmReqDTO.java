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
public class AlterationDeliveryChangeConfirmReqDTO {

    /**
     * ID
     */
    @Mapping
    private @ApiModelProperty("id") Long id;

    /**
     * 当前网点id
     */
    @Mapping
    private @ApiModelProperty(name = "当前网点id",hidden=true) Long curOrgId;
    /**
     * 公司id
     */
    @Mapping
    private @ApiModelProperty(name = "公司id",hidden=true) Long companyId;
    /**
     * 确认人
     */
    @Mapping
    private @ApiModelProperty(name = "确认人",hidden=true) String confirmUserName;

    /**
     * 确认人ID
     */
    @Mapping
    private @ApiModelProperty(name = "确认人",hidden=true) Integer confirmUser;


}
