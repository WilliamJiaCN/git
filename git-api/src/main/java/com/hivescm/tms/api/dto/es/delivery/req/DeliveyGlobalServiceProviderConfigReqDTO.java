package com.hivescm.tms.api.dto.es.delivery.req;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.delivery.resp.DeliveryCompanyRespDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  qsk
 * @company 蜂网供应链(上海)管理有限公司
 * @version 2017年11月18日 下午1:41:49
 * 关联公司请求类
 */
@Data
@ToString
public class DeliveyGlobalServiceProviderConfigReqDTO implements Serializable {
    private static final long serialVersionUID = 8643123283514581979L;
    @Required
    @Mapping
    @ApiModelProperty("仓储服务商ID")
    private Long providerId;
    @Required
    @Mapping
    @ApiModelProperty("仓库ID")
    private Long warehouseId;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建人名称")
    private String createUserName;

    @ApiModelProperty("快递公司信息")
    List<DeliveryCompanyRespDTO> list = new ArrayList<>();
}
