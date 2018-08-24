package com.hivescm.tms.api.dto.es.distribution;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class DistributionWaybillFeeEsDTO implements Serializable{

     private static final long serialVersionUID = 1L;

     @ApiModelProperty("ID")
        private Long id;

        @ApiModelProperty("运单ID")
        private Long waybillId;

        @ApiModelProperty("公司ID")
        private Long companyId;

        @ApiModelProperty("公司名称")
        private String companyName;

        @ApiModelProperty("属性ID")
        private Long attrId;

        @ApiModelProperty("属性名称")
        private String attrName;

        @ApiModelProperty("运费")
        private BigDecimal fee;

        /**
         * 创建人
         */
        @ApiModelProperty("创建人")
        private Integer createUser;

        /**
         * 创建人姓名
         */
        @ApiModelProperty("创建人姓名")
        private  String createUserName;
        /**
         * 创建时间
         */
        @ApiModelProperty("创建时间")
        private Long createTime;
        /**
         * 修改人
         */
        @ApiModelProperty("修改人")
        private Integer updateUser;

        /**
         * 修改人姓名
         */
        @ApiModelProperty("修改人姓名")
        private  String updateUserName;
        /**
         * 修改时间
         */
        @ApiModelProperty("修改时间")
        private Long updateTime;
       


}
