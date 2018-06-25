package com.hivescm.tms.api.dto.es.outbill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class OutBillTrackEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Mapping
    @Logger
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 公司id
     */
    @Mapping
    @Required
    @Logger
    @ApiModelProperty("公司id")
    private Long companyId;
    /**
     * 公司名称
     */
    @Mapping
    @ApiModelProperty("公司名称")
    private String companyName;
    /**
     * 外发Id
     */
    @Required
    @Logger
    @Mapping
    @ApiModelProperty("外发Id")
    private Long outbillId;
    /**
     * 运单Id
     */
    @Logger
    @Mapping
    @ApiModelProperty("运单Id")
    private Long waybillId;
    /**
     * 跟踪消息
     */
    @Mapping
    @ApiModelProperty("跟踪消息")
    private String informat;
    /**
     * 经度
     */
    @Mapping
    @ApiModelProperty("经度")
    private String lngs;
    /**
     * 纬度
     */
    @Mapping
    @ApiModelProperty("纬度")
    private String lats;
    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("操作人")
    private String createUserName;
    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    /**
     * 是否可见  true
     */
    @Mapping
    @ApiModelProperty("是否可见")
    private Boolean show;
    /**
     * 是否人工录入 true人工录入  false系统生成
     */
    @Mapping
    @ApiModelProperty("是否人工录入")
    private Boolean artificial;
    /**
     * 状态
     */
    @Mapping
    @ApiModelProperty("外发单状态：1中转确认2：入库确认3签收")
    private Integer status;
    /**
     * 状态
     */
    @Mapping
    @ApiModelProperty("外发单状态：1中转确认2：入库确认3签收")
    private String statusName;
    /**
     * 当前操作网点ID
     */
    @Required
    @Mapping
    @ApiModelProperty("当前操作网点ID")
    private Integer invoiceOrgId;
    /**
     * 当前操作网点名称
     */
    @Mapping
    @ApiModelProperty("当前操作网点名称	")
    private String invoiceOrgName;
    @Mapping
    @ApiModelProperty("当前网点城市名称")
    private String orgCityName;
    @Mapping
    @ApiModelProperty("当前网点address")
    private String orgAddress;
    @Mapping
    @ApiModelProperty("外发公司名称")
    private String outCompanyName;
    @Mapping
    @ApiModelProperty("定位信息，是否定位失败")
    private String locateMessage;
    @Mapping
    @ApiModelProperty("签收人")
    private String signName;

    public void initOutBillTrackEsDto(OutBillTrackEsDTO dto) {
        if (StringUtil.isBlank(dto.getInvoiceOrgName())) {
            dto.setInvoiceOrgName("");
        }
        if (StringUtil.isBlank(dto.getOutCompanyName())) {
            dto.setOutCompanyName("");
        }
        if (StringUtil.isBlank(dto.getCreateUserName())) {
            dto.setCreateUserName("");
        }
        if (StringUtil.isBlank(dto.getSignName())) {
            dto.setSignName("");
        }
    }
}
