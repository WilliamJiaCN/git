package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 反馈转移复制请求
 * @author qsk
 * @date 2017年12月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TmsTransferFeedbackReqDTO implements Serializable {

    private static final long serialVersionUID = 8327757118198890336L;
    @Required
    @ApiModelProperty("反馈标识")
    private List<String> strs;
}
