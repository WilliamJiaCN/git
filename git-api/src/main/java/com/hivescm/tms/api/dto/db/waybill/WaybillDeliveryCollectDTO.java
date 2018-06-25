package com.hivescm.tms.api.dto.db.waybill;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author 
 * @Talbe(name="waybill_delivery_collect")
 */
@Data
@ToString
public class WaybillDeliveryCollectDTO implements Serializable {

	/**
     * id
     */
    private Long id;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 运单id
     */
    private Long waybillId;

    /**
     * 退货款方式
     */
    private Integer refundWay;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankCard;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String identityCard;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改人
     */
    private Integer updateUser;

    /**
     * 修改时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;


}