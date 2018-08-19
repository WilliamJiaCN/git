package com.hivescm.tms.api.dto.db.waybill;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 公司运单配置类
 *
 * @author 李洪春
 * @since 2017/7/18 15:58
 */
@Data
@ToString
public class CompanyWaybillConfigDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    @Required
    private Integer operatorId;
    @ApiModelProperty(hidden = true)
    private String operatorName;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 公司运单基础配置信息
     */
    @ApiModelProperty("运单基础配置信息")
    private WaybillBaseConfigDTO waybillBaseConfig;

    /**
     * 运单属性配置
     */
    @ApiModelProperty("运单属性配置信息")
    private List<WaybillAttrConfigDTO> attrConfigList;

    /**
     * 税费计算方式
     */
    @ApiModelProperty("税费计算项列表")
    private List<WaybillTaxFeeItemDTO> taxFeeItemList;

    /**
     * 总费用计算方式
     */
    @ApiModelProperty("总费用计算项列表")
    private List<WaybillTotalFeeItemDTO> totalFeeItemList;


    /**
     * 新增配置时初始化信息
     *
     * @param createUser 创建者id
     * @param createTime 创建时间
     */
    public void initOnCreate(Integer createUser, Long createTime) {
        if (null != waybillBaseConfig) {
            waybillBaseConfig.setCreateUser(createUser);
            waybillBaseConfig.setCreateTime(createTime);
        }
        if (null != attrConfigList) {
            attrConfigList.forEach(waybillAttrConfig -> {
                waybillAttrConfig.setCreateUser(createUser);
                waybillAttrConfig.setCreateTime(createTime);
            });
        }
        if (null != taxFeeItemList) {
            taxFeeItemList.forEach(taxFeeItem -> {
                taxFeeItem.setCreateUser(createUser);
                taxFeeItem.setCreateTime(createTime);
            });
        }
        if (null != totalFeeItemList) {
            totalFeeItemList.forEach(waybillTotalFeeItem -> {
                waybillTotalFeeItem.setCreateUser(createUser);
                waybillTotalFeeItem.setCreateTime(createTime);
            });
        }
    }

    /**
     * 修改配置保存时初始化信息
     *
     * @param updateUser 修改人Id
     * @param updateTime 修改时间
     */
    public void initOnUpdate(Integer updateUser, Long updateTime) {
        if (null != waybillBaseConfig) {
            waybillBaseConfig.setUpdateUser(updateUser);
            waybillBaseConfig.setUpdateTime(updateTime);
        }
        if (null != attrConfigList) {
            attrConfigList.forEach(waybillAttrConfig -> {
                waybillAttrConfig.setUpdateUser(updateUser);
                waybillAttrConfig.setUpdateTime(updateTime);
            });
        }
        if (null != taxFeeItemList) {
            taxFeeItemList.forEach(taxFeeItem -> {
                taxFeeItem.setUpdateUser(updateUser);
                taxFeeItem.setUpdateTime(updateTime);
            });
        }
        if (null != totalFeeItemList) {
            totalFeeItemList.forEach(waybillTotalFeeItem -> {
                waybillTotalFeeItem.setUpdateUser(updateUser);
                waybillTotalFeeItem.setUpdateTime(updateTime);
            });
        }
    }
}
