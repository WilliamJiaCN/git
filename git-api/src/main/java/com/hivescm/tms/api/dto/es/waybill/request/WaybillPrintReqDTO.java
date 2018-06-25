package com.hivescm.tms.api.dto.es.waybill.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
/**
 * 运单打印请求对象
 *
 * @author 李洪春
 * @since 2017/9/26 13:41
 */
@Data
@ToString
public class WaybillPrintReqDTO implements Serializable {

    private static final long serialVersionUID = -2886349666983206778L;

    /**
     * 运单ID
     */
    @Logger
    private Long id;

//    /**
//     * 后期使用
//     */
//    private String key;
}
