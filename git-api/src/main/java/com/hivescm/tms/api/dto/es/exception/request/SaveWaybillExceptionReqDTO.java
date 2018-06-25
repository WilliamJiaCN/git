package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionAttachEsDTO;
import com.hivescm.tms.api.dto.es.exception.WaybillExceptionEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.waybillexception.ExceptionStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 保存异常信息请求对象
 *
 * @author 李洪春
 * @since 2017/9/25 16:26
 */
@Data
@ToString
public class SaveWaybillExceptionReqDTO implements Serializable {

    private static final long serialVersionUID = -6915866265565217511L;


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

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传", example = "23",hidden=true)
    private Integer branchId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传", example = "网点23",hidden=true)
    private String branchName;

    /**
     * 异常信息
     */
    @ApiModelProperty("异常信息")
    private WaybillExceptionInsertDTO waybillExceptionEsDTO;

    /**
     * 运单id
     */
    @ApiModelProperty("运单id")
    private Long waybillId;
    

    @Mapping
    @ApiModelProperty("异常附件")
    private List<WaybillExceptionAttachEsDTO> attachList;

    /**
     * 新增时初始化
     */
    public void initOnCreate() {
        Long time = System.currentTimeMillis();
        waybillExceptionEsDTO.setCompanyId(companyId);
        waybillExceptionEsDTO.setRegisterUserId(opUserId);
        waybillExceptionEsDTO.setRegisterUserName(opUserName);
        waybillExceptionEsDTO.setRegisterTime(time);
        waybillExceptionEsDTO.setIsDelete(false);
        waybillExceptionEsDTO.setWaybillExceptionStatus(ExceptionStatusEnum.UNSUBMIT.getType());
        waybillExceptionEsDTO.setWaybillExceptionStatusName(ExceptionStatusEnum.UNSUBMIT.getName());
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

    /**
     * 修改时初始化信息
     */
    public void initOnUpdate() {
        Long time = System.currentTimeMillis();
        waybillExceptionEsDTO.setCompanyId(companyId);
        waybillExceptionEsDTO.setUpdateUserId(opUserId);
        waybillExceptionEsDTO.setUpdateUserName(opUserName);
        waybillExceptionEsDTO.setUpdateTime(time);
        if (CollectionUtils.isNotEmpty(attachList)) {
        	attachList.forEach(waybillExceptionAttachEsDTO -> {
        		waybillExceptionAttachEsDTO.setExceptionId(waybillExceptionEsDTO.getId());
        		waybillExceptionAttachEsDTO.setCompanyId(companyId);
                waybillExceptionAttachEsDTO.setCreateUser(opUserId);
                waybillExceptionAttachEsDTO.setCreateUserName(opUserName);
                waybillExceptionAttachEsDTO.setCreateTime(time);
            });
        }
    }
}
