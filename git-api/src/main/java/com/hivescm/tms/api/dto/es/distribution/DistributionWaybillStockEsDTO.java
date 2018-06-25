package com.hivescm.tms.api.dto.es.distribution;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class DistributionWaybillStockEsDTO implements Serializable{

     private static final long serialVersionUID = 1L;

     @ApiModelProperty("ID")
        private Long id;

        @ApiModelProperty("运单ID")
        private Long waybillId;

        @ApiModelProperty("公司ID")
        private Long companyId;

        @ApiModelProperty("公司名称")
        private String companyName;

        @ApiModelProperty("库存网点id")
        private Integer orgId;

        @ApiModelProperty("库存网点名称")
        private String orgName;

        @ApiModelProperty("仓库ID")
        private Integer warehouseId;

        @ApiModelProperty("仓库名称")
        private String warehouseName;

        @ApiModelProperty("库区ID")
        private Integer warehouseAreaId;

        @ApiModelProperty("库区名称")
        private String warehouseAreaName;

        @ApiModelProperty("库位ID")
        private Integer warehousePositionId;

        @ApiModelProperty("库位名称")
        private String warehousePositionName;

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
