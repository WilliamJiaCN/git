package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 单位进制规则
 * @author 杨彭伟
 * @date 2018-03-06 17:23
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UnitLevelDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private @Mapping @ApiModelProperty("单位名称") String unitName;

    private @Mapping @ApiModelProperty("向上一级单位转换数量") Integer unitRule;

}
