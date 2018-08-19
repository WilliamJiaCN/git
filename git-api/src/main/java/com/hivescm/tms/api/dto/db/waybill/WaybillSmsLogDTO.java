package com.hivescm.tms.api.dto.db.waybill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 * @Talbe(name="waybill_sms_log")
 */
@Data
@ToString
public class WaybillSmsLogDTO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Integer companyId;
    
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
     * 运单id
     */
    @ApiModelProperty("运单id")
    private Long waybillId;

    /**
     * 业务节点
     */
    @ApiModelProperty("业务节点")
    private Integer bussNode;
    /**
     * 业务节点名称
     */
    @ApiModelProperty("业务节点名称")
    private String bussNodeName;

    /**
     * 短信内容
     */
    @ApiModelProperty("短信内容")
    private String content;

    /**
     * 状态
     */
    @ApiModelProperty("状态0成功 1 失败")
    private Integer status;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phoneNo;

    /**
     * 接收人
     */
    @ApiModelProperty("接收人")
    private String user;

    /**
     * 发送网点Id
     */
    @ApiModelProperty("发送网点Id")
    private Integer sendOrgId;

    /**
     * 发送网点Id
     */
    @ApiModelProperty("发送网点名称")
    private String sendOrgName;
    
    /**
     * 发送时间
     */
    @ApiModelProperty("发送时间")
    private Long sendTime;

    /**
     * 运单号
     */
    @ApiModelProperty("运单号")
    private Long waybillCode;

    /**
     * 发送条数
     */
    @ApiModelProperty("发送条数")
    private Integer amount;

    /**
     * 短信费
     */
    @ApiModelProperty("短信费")
    private BigDecimal fee;

    /**
     * 发送人编码
     */
    @ApiModelProperty("发送人编码")
    private Integer sendUserId;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(" 创建时间")
    private Long createTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private Integer updateUser;
    /**
     * 发送类型
     */
    @ApiModelProperty("发送类型")
    private String sendType;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Long updateTime;

    private static final long serialVersionUID = 1L;

}