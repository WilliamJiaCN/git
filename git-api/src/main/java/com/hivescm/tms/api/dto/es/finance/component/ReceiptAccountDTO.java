package com.hivescm.tms.api.dto.es.finance.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 收款账户
 * @author 杨彭伟
 * @date 2017-12-13 15:17
 */
@Data
@ToString
public class ReceiptAccountDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("主体类型:1：组织，2：档案，3：个人")
    private String subjectType;
    @Mapping
    @ApiModelProperty("用户编号:如果主体类型是组织，则本字段为组织编号；如果主体类型是档案，则本字段为档案编号。")
    private String userSn;
    @Mapping
    @ApiModelProperty("数据所有者编号:该条信息所有者的编号，这里的所有者一定是一个组织类型。如果是组织为自己配置银行账户，则用户编号和所有者编号相同")
    private String dataOwnerSn;
    @Mapping
    @ApiModelProperty("账户分类:银行账户、微信账户、支付宝账户等。举例，1：银行；2：微信；3：支付宝；4：京东钱包。")
    private String accountCategory;
    @Mapping
    @ApiModelProperty("账户类型:包括：1：对公；2：对私。无论支付宝、微信、银行都存在对公与对私的区别，故此处必填。")
    private String accountType;
    @Mapping
    @ApiModelProperty("对私账户类型B:1：贷记卡（信用卡）、2：借记卡（储蓄卡）。")
    private String privateAccountType;
    @Mapping
    @ApiModelProperty("对公账户类型B:1：基本账户、2：一般账户、3：临时账户、4：专用账户。")
    private String publicAccountType;
    @Mapping
    @ApiModelProperty("账户号:账号，账户分类为银行账户时：对公为户号（一般20位）、对私为卡号（16、19位），其他情况下（如微信、支付宝）依据账户归属平台制定的规则，加密。")
    private String accountSn;
}
