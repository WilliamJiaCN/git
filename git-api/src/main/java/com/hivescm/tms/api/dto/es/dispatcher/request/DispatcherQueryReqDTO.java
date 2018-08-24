package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 派车单请求体
 * TmsDispatcherEsDTO
 *
 * @author zhenming.du
 * @date 2017年8月1日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcherQueryReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 派车单状态
     */
    @ApiModelProperty("派车单状态(0、查询全部，4，未发车， 5、已发车)")
    private Integer status;

    /**
     * 派车单发布状态
     */
    @ApiModelProperty("派车单发布状态")
    private Boolean releaseStatus;
    /**
     * 派车单批次
     */
    @Logger
    @ApiModelProperty("派车单批次")
    private String batchCode;

    /**
     * 司机姓名
     */
    @ApiModelProperty("司机姓名")
    private String driverName;

    /**
     * 派车网点ID
     */
    @ApiModelProperty("派车网点ID")
    private List<Long> branchId;

    /**
     * 制单日期 - 开始范围
     */
    @ApiModelProperty("制单日期 - 开始范围")
    private Long startCreateTime;

    /**
     * 制单日期 - 结束范围
     */
    @ApiModelProperty("制单日期 - 结束范围")
    private Long endCreateTime;

    @ApiModelProperty("发车起始时间")
    private Long startDispatcherTime;

    @ApiModelProperty("发车截止时间")
    private Long endDispatcherTime;

    
    @ApiModelProperty("交接起始时间")
    private Long startHandoverTime;

    @ApiModelProperty("交接截止时间")
    private Long endHandoverTime;
    
    @ApiModelProperty("配送开始时间")
    private Long startSendTime;

    @ApiModelProperty("配送截止时间")
    private Long endSendTime;
    
    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;
    
    /**
     * 司机姓名
     */
    @ApiModelProperty("模糊查询(APP)")
    private String filedName;
    /**
     * 司机姓名
     */
    @ApiModelProperty("关键字查询)")
    private String keywords;
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
