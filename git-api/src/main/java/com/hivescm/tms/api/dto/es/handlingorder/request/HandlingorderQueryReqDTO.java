package com.hivescm.tms.api.dto.es.handlingorder.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 装卸单请求
 * HandlingorderQueryReqDTO
 *
 * @author lutingting
 * @date 2017年11月21日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class HandlingorderQueryReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 公司id
     */
    @ApiModelProperty("装卸网点id")
    private List<Long> branchId;
    /**
     * 装卸单号
     */
    @ApiModelProperty("装卸单号")
    private String handlingorderCode;

    /**
     * 司机姓名
     */
    @ApiModelProperty("装卸类型")
    private Integer handlingType;

    @ApiModelProperty("装卸对名称")
    private String handlingTeam;

  
 
    @ApiModelProperty("装卸开始时间")
    private Long handlingStartTime;

    @ApiModelProperty("装卸截止时间")
    private Long handlingEndTime;
    
   
    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize) {
            pageSize = 10;
        }
        if (null == currentPage) {
            currentPage = 1;
        }
    }

}
