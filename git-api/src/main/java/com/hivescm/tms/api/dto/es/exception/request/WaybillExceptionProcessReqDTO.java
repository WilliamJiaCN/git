package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionAttachEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionProcessEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class WaybillExceptionProcessReqDTO implements Serializable {

	private static final long serialVersionUID = -6014289560599453193L;
	
	 /**
     * 公司Id
     */
    @ApiModelProperty(value = "集团Id", notes = "前端调用时不传", example = "2",hidden=true)
    private Integer groupId;
    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传", example = "2",hidden=true)
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传", example = "2",hidden=true)
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传", example = "李洪春",hidden=true)
    private String opUserName;
	
	@Mapping
	@ApiModelProperty("异常处理信息")
	private WaybillExceptionProcessEsDTO waybillExceptionProcessEsDTO;

	@Mapping
	@ApiModelProperty("异常附件")
	private List<WaybillExceptionAttachEsDTO> attachList;
	
	 /**
     * 新增时初始化
     */
    public void initOnCreate() {
        Long time = System.currentTimeMillis();
        waybillExceptionProcessEsDTO.setCompanyId(companyId);
        waybillExceptionProcessEsDTO.setCreateTime(time);
        waybillExceptionProcessEsDTO.setCreateUserId(opUserId);
        waybillExceptionProcessEsDTO.setCreateUserName(opUserName);
        if (CollectionUtils.isNotEmpty(attachList)) {
        	attachList.forEach(waybillExceptionAttachEsDTO -> {
                        waybillExceptionAttachEsDTO.setCompanyId(companyId);
                        waybillExceptionAttachEsDTO.setCreateUser(opUserId);
                        waybillExceptionAttachEsDTO.setCreateUserName(opUserName);
                        waybillExceptionAttachEsDTO.setCreateTime(time);
                    }

            );
        }
    }
}
