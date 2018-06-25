package com.hivescm.tms.api.dto.es.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运单业务单据信息
 *
 * @author 李洪春
 * @since 2017/10/11 16:25
 */
@Data
@ToString
public class WaybillBusinessInfoDTO implements Serializable {

    private static final long serialVersionUID = 2024249157819666859L;

    /**
     * 业务ID
     */
    private Long id;

    /**
     * 业务单号
     */
    private String code;

    /**
     * 业务类型
     */
    private Integer type;
}
