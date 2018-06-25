package com.hivescm.tms.api.dto.es.regulation.request;

import com.hivescm.tms.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RegulationInfoReq implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 230607996289213352L;



    /**
     * 调拨批次
     */
    @ApiModelProperty("调拨批次")
    private String regulationBatch;

    /**
     * 公司ID
     */
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 调出网点ID
     */
    @ApiModelProperty("调出网点ID")
    private Integer departBranchId;
    @ApiModelProperty("调出网点名称")
    private String departBranchName;
    /**
     * 调入网点ID
     */
    @ApiModelProperty("调入网点ID")
    private Integer arrivalBranchId;

    @ApiModelProperty("调入网点名称")
    private String arrivalBranchName;

    /**
     * 调入仓库ID
     */
    @ApiModelProperty("调入仓库ID")
    private Integer arrivalWarehouseId;

    @ApiModelProperty("调入仓库名称")
    private String arrivalWarehouseName;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID")
    private Integer opUserId;

    @ApiModelProperty(value = "操作用户名称")
    private String opUserName;

    public Boolean check(){
        if(this.arrivalBranchId != null && StringUtil.isNotBlank(this.arrivalBranchName)){
            if(this.departBranchId != null && StringUtil.isNotBlank(this.departBranchName)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public Boolean checkWarehouse(){
        if(this.arrivalWarehouseId != null &&
                StringUtil.isNotBlank(this.arrivalWarehouseName)){
            return true;
        }else{
            return false;
        }
    }
}
