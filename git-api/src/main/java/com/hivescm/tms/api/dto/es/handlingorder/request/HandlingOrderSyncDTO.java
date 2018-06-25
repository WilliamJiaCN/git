package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: HandlingOrderSyncDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-19-17:20
 */
@Data
@ToString
public class  HandlingOrderSyncDTO {


    @Mapping @Required @Logger
    @ApiModelProperty(value = "主键",required = true)
    private Long handlingOrderId;

    @Mapping @Required @Logger
    @ApiModelProperty(value = "网点id",required = true)
    private Long branchId;

    @Mapping @Logger
    @ApiModelProperty(value = "网点名称")
    private String branchName;

    @Mapping @Required @Logger
    @ApiModelProperty(value = "公司id",required = true)
    private Long companyId;

    @Mapping @Logger
    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("集团ID")
    private Long groupId;



    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    @ApiModelProperty(value = "操作时间", notes = "前端调用时不传")
    private String opUserTime;

}
