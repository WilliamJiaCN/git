package com.hivescm.tms.api.dto.es.fare;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 费用详情实体
 *
 * @author qsk
 * @date 2018年5月22日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FareInfoEsDTO {
    @Mapping
    @ApiModelProperty("主键")
    private Long id;
    @Mapping
    @ApiModelProperty("车费更改批次id")
    private @Required
    @Logger
    Long changeId;
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Mapping
    @ApiModelProperty("公司名称")
    private String companyName;
    @Mapping
    @ApiModelProperty("费用类型")
    private @Required
    @Logger
    String feeType;
    @Mapping
    @ApiModelProperty("费用金额（应付金额）")
    private @Required
    @Logger
    BigDecimal payableAmount;
    @Mapping
    @ApiModelProperty("付款状态 1、未审核 2、未付款 3、部分付款 4、已付款")
    private Integer paymentStatus;
    @Mapping
    @ApiModelProperty("付款方（网点ID）")
    private @Required
    @Logger
    Integer paymentNetworkId;
    @Mapping
    @ApiModelProperty(value = "收款方id")
    private @Required
    @Logger
    Integer payeeId;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;
    @Mapping
    @ApiModelProperty("原版本1新版本2")
    private Integer version;
    //------------------------es---------
    /**
     * 费用类型名称
     */
    @Mapping
    @ApiModelProperty(value = "费用类型名称")
    private String feeTypeName;
    /**
     * 付款状态名称
     */
    @Mapping
    @ApiModelProperty(value = "付款状态名称")
    private String paymentStatusName;
    /**
     * 收款方名称
     */
    @Mapping
    @ApiModelProperty("收款方")
    private String payee;
    /**
     * 付款网点名称
     */
    @Mapping
    @ApiModelProperty(value = "付款网点名称")
    private String paymentNetworkName;

    /**
     * 费用说明
     */
    @Mapping
    @ApiModelProperty(value = "费用说明")
    private String feeRemark;
    @Mapping
    @ApiModelProperty(value = "创建人名称")
    private String createUserName;
    @Mapping
    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;
    @Mapping
    @ApiModelProperty("财务费项主键")
    private Long financeId;

    @Mapping
    @ApiModelProperty("单据上的费用主键")
    private Long sheetId;
    /**
     * 为了 改费去重
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FareInfoEsDTO that = (FareInfoEsDTO) o;

        boolean equals = Objects.equals(changeId, that.changeId) &&
                Objects.equals(financeId, that.financeId);
//        System.out.println("equals:"+equals);
        return equals;
    }

    /**
     * 为了 改费去重
     * @param
     * @return
     */
    @Override
    public int hashCode() {
        int hash = Objects.hash(changeId, financeId);
//        System.out.println("hashCode:"+hash);
        return hash;
    }
}