package com.hivescm.tms.api.dto.db.base;


import java.io.Serializable;
import java.util.Date;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author 
@Talbe(name="base_driver")
 */
@Data
@ToString
public class BaseDriverDTO implements Serializable {
	 /**
     * ID
    @Id
    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    private Integer id;

    /**
     *
     * 
     * 司机编号
     * @NotEmpty
     * 不可编辑系统自动
     */
    @ApiModelProperty("司机编号")
    private String driverCode;

    /**
     * 司机姓名
     * @NotEmpty
    
     */
    @ApiModelProperty("司机姓名")
    private String driverName;
    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String vehicleNo;

    /**
     * 手机号码
     * @NotEmpty
     */
    @ApiModelProperty("手机号码")
    private String phoneNo;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String telNo;

    /**
     * 身份证号
     * @NotEmpty
     */
    @ApiModelProperty("身份证号")
    private String identityCard;
    //TODO 调用数据字典
    /**
     * 
     * 准驾车型
     * @NotEmpty
     */
    @ApiModelProperty("准驾车型")
    private Integer driveType;

    /**
     * 驾驶证号
     * @NotEmpty
     */
    @ApiModelProperty("驾驶证号")
    private String driverNo;

    /**
     * 初次领证日期
     *  @NotEmpty
     */
    @ApiModelProperty("初次领证日期")
    private Date firstLicenseDatetime;

    /**
     * 年检、换证日期
     *  @NotEmpty
     */
    @ApiModelProperty("年检、换证日期")
    private Date yearCheckDatetime;
    
    
 
  //TODO 调用数据字典
    /**
     * 所属承运商
     * @NotEmpty
     */
    
    @ApiModelProperty("所属承运商")
    private Integer belongCarry;
    /**
     * 承运商名称
     * @NotEmpty
     */
    @ApiModelProperty("承运商名称")
    private String carrierName;
    /**
     * 国家
     */
    @ApiModelProperty("国家")
    private String country;
    @Mapping
    @ApiModelProperty("省")
    private String provinceName;
    @Mapping
    @ApiModelProperty("市")
    private String cityName;
    @Mapping
    @ApiModelProperty("区")
    private String countyName;
    @Mapping
    @ApiModelProperty("街道")
    private String streetName;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty("区")
    private String county;
    /**
     * 街道
     */
    @ApiModelProperty("街道")
    private String street;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String address;

    /**
     * 是否为公司员工
     */
    @ApiModelProperty("是否为公司员工")
    private Boolean employee;
    

    /**
     * 关联用户ID
     */
    @ApiModelProperty("关联用户ID")
    private Long userId;

    @Mapping
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * 状态
    @NotEmpty
     */
    @ApiModelProperty("状态")
    private Boolean status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 附件
     */
    @ApiModelProperty("附件")
    private String attachmentUrl;
    
    /**
     * 头像路径
     * @NotEmpty
     */
    @ApiModelProperty("头像路径")
    private String headUrl;
    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("网点ID")
    private Long branchId;
    @Mapping
    @ApiModelProperty("网点名称")
    private String branchName;
    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    
    @Mapping
    @ApiModelProperty("公司名称")
    private String companyName;
	/**
	 * @NotEmpty
	 */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Integer createUser;
    @Mapping
    @ApiModelProperty(hidden = true)
    private String createUserName;
	/**
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long createTime;
    /**
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Integer updateUser;
    /**
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long updateTime;
    @Mapping
    @ApiModelProperty(hidden = true)
    private String updateUserName;

    private static final long serialVersionUID = 1L;


    
}