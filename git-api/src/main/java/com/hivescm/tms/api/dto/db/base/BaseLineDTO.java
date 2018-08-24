package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author 
@Talbe(name="base_line")
 */
@Data
@ToString
public class BaseLineDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @Logger
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 公司ID
     */
    @Logger
    @Required
    @ApiModelProperty("公司Id")
    private Long companyId;

    /**
     * 线路名称
    @NotEmpty
     */
    @Required
    @ApiModelProperty("线路名称")
    private String lineName;

	/**
     * 类型（干线，支线）
    @NotEmpty
     */
	@ApiModelProperty("类型")
    private Integer lineType;

    /**
     * 是否默认线路
    @NotEmpty
     */
	@ApiModelProperty("是否默认线路 1:默认")
    private Boolean iDefault;

    /**
     * 状态
    @NotEmpty
     */
	@ApiModelProperty("状态")
    private Boolean status;

    /**
     * 始发网点
    @NotEmpty
     */
	@ApiModelProperty("始发网点")
    private List<LineContactStartorgDTO> lineContactStartorgDTOS;

	/**
     * 到达网点
    @NotEmpty
     */
	@ApiModelProperty("到达网点")
    private List<LineContactArriveorgDTO> lineContactArriveorgDTOS;
    /**
     *
    @NotEmpty
	 */
	@ApiModelProperty("运输时效")
	private BigDecimal timeEfficiency;
	/**
	 *总里程数
    @NotEmpty
	 */
	@ApiModelProperty("总里程数")
	private BigDecimal totalMileage;
	/**
     *创建人
     */
    @ApiModelProperty("创建人")
    private Integer createUser;
    /**
     *创建时间
    */
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     *修改人
     */
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     *修改时间
     */
    @ApiModelProperty("修改时间")
    private Long updateTime;

    /**
     *始发网点名称
     */
    @ApiModelProperty("始发网点名称")
    private String startOrgNames;

    /**
     *到达网点名称
     */
    @ApiModelProperty("到达网点名称")
    private String arriveOrgNames;

    /**
     *备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     *创建人名称
     @NotEmpty
     */
    @ApiModelProperty("创建人名称")
    private String createUserName;

    /**
     *修改人名称
     @NotEmpty
     */
    @ApiModelProperty("修改人名称")
    private String updateUserName;

    private List<BaseLineDTO> baseLineDTOS;

    private Integer totalNum;

    private Integer totalPage;

    public boolean checkBaseLine(){
        boolean flag = false;
        if(StringUtils.isBlank(this.startOrgNames) ||StringUtils.isBlank(arriveOrgNames)){
            return flag;
        }else{
            flag = true;
        }
        return flag;
    }

    public boolean checkBaseLineList(){
        boolean result = false;
        List<LineContactStartorgDTO> lineContactStartorgDTOS = this.lineContactStartorgDTOS;
        List<LineContactArriveorgDTO> lineContactArriveorgDTOS = this.lineContactArriveorgDTOS;
        if(StringUtil.isBlankList(lineContactStartorgDTOS) || StringUtil.isBlankList(lineContactArriveorgDTOS)){
            return result;
        }
        LineContactStartorgDTO startorgDTO = lineContactStartorgDTOS.get(0);
        LineContactArriveorgDTO line = lineContactArriveorgDTOS.get(0);
        String startOrgName = startorgDTO.getStartOrgName();
        Long startOrgId = startorgDTO.getStartOrgId();
        String arriveOrgName = line.getArriveOrgName();
        Long arriveOrgId = line.getArriveOrgId();
        if(StringUtils.isNotBlank(startOrgName) && null != startOrgId
                && StringUtils.isNotBlank(arriveOrgName) && null != arriveOrgId){
            result  = true;
        }else{
            result  = false;
        }
        return result;
    }
}