package com.hivescm.tms.api.dto.db.base;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 司机信息组合Dto
 * @author 
@Talbe(name="base_driver")
 */
@Data
@ToString
public class TmsDriverDTO implements Serializable {
	 /**
     * ID
    @Id
    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
	@Mapping("driverId")
    private Integer id;

    /**
     *
     * 
     * 司机编号
     * @NotEmpty
     * 不可编辑系统自动
     */
    @Mapping
    @ApiModelProperty("司机编号")
    private String driverCode;

    /**
     * 司机账户信息
     */
    @Mapping
    @ApiModelProperty("司机账户信息")
    private List<BaseDriverBankDTO> baseDriverBankDTOs;
    /**
     * 司机姓名
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    
    @Mapping
    @ApiModelProperty("关联用户ID")
    private Long userId;
    
    @Mapping
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * 车牌号
     */
   
    @ApiModelProperty("车牌号")
    private String vehicleNo;

    /**
     * 手机号码
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("手机号码")
    private String phoneNo;

    /**
     * 电话号码
     */
    @Mapping
    @ApiModelProperty("电话号码")
    private String telNo;

    /**
     * 身份证号
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("身份证号")
    private String identityCard;
    //TODO 调用数据字典
    /**
     * 
     * 准驾车型
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("准驾车型")
    private Integer driveType;

    /**
     * 驾驶证号
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("驾驶证号")
    private String driverNo;

    /**
     * 初次领证日期
     *  @NotEmpty
     */
    @Mapping
    @ApiModelProperty("初次领证日期")
    private Date firstLicenseDatetime;

    /**
     * 年检、换证日期
     *  @NotEmpty
     */
    @Mapping
    @ApiModelProperty("年检、换证日期")
    private Date yearCheckDatetime;
    //TODO 调用数据字典
    /**
     * 所属承运商
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("所属承运商")
    private Integer belongCarry;
    
    @Mapping
    @ApiModelProperty("所属承运商")
    private String carrierName;

    /**
     * 国家
     */
    @Mapping
    @ApiModelProperty("国家")
    private String country;

    /**
     * 省
     */
    @Mapping
    @ApiModelProperty("省")
    private String province;
    
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
     * 市
     */
    @Mapping
    @ApiModelProperty("市")
    private String city;
    


    /**
     * 区
     */
    @Mapping
    @ApiModelProperty("区")
    private String county;
    
   
    /**
     * 街道
     */
    @Mapping
    @ApiModelProperty("街道")
    private String street;
    
 

    /**
     * 详细地址
     */
    @Mapping
    @ApiModelProperty("详细地址")
    private String address;

    /**
     * 是否为公司员工
     */
    @Mapping
    @ApiModelProperty("是否为公司员工")
    private Boolean employee;

    /**
     * 状态
    @NotEmpty
     */
    @Mapping
    @ApiModelProperty("状态")
    private Boolean status;

    /**
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 附件
     */
    @Mapping
    @ApiModelProperty("附件")
    private String attachmentUrl;
    
    @Mapping
    @ApiModelProperty("附件")
    private String headUrl;
    
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
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("网点ID")
    private Long branchId;
    @Mapping
    @ApiModelProperty("网点名称")
    private String branchName;
	/**
	 * @NotEmpty
	 */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    
    @Mapping
    @ApiModelProperty(hidden = true)
    private String createUserName;
	/**
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    /**
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Integer updateUser;
    
    @Mapping
    @ApiModelProperty(hidden = true)
    private String updateUserName;
    /**
     * @NotEmpty
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long updateTime;

    private static final long serialVersionUID = 1L;


    
}