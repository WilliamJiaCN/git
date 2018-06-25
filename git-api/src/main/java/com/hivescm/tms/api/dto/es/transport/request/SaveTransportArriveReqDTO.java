package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportArriveDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 保存到货批次请求信息
 *
 * @author 李洪春
 * @since 2017/9/11 上午10:20
 */
@Data
@ToString
public class SaveTransportArriveReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8748793261495040325L;


    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;

    /**
     * 集团ID
     */
    @ApiModelProperty(value = "集团ID", notes = "前端调用时不传")
    private Long groupId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;

    /**
     * 到货批次信息
     */
    @Required
    @ApiModelProperty(value = "到货批次信息",required = true)
    TmsTransportArriveDTO tmsTransportArriveDTO;

    /**
     * 运输批次Id
     */
    @Required
    @ApiModelProperty(value = "运输批次Id",required = true)
    Long transportId;

}
