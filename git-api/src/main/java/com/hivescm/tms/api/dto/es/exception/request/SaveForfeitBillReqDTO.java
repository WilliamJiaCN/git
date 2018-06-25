package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.tms.api.dto.es.exception.ForfeitBillDetailEsDTO;
import com.hivescm.tms.api.dto.es.exception.component.TmsForfeitBillDTO;
import com.hivescm.tms.api.enums.biz.exception.ForfeitBillStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * 保存罚款单信息
 *
 * @author 李洪春
 * @since 2017/10/10 11:11
 */
@Data
@ToString
public class SaveForfeitBillReqDTO implements Serializable {
    private static final long serialVersionUID = -1607861915115816973L;
    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传", example = "2")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传", example = "2")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传", example = "李洪春")
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传", example = "23")
    private Integer branchId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传", example = "网点23")
    private String branchName;

    /**
     * 罚款单信息
     */
    @ApiModelProperty("罚款单信息")
    private TmsForfeitBillDTO tmsForfeitBill;

    /**
     * 新增时初始化信息
     */
    public void initOnCreate() {
        Long time = System.currentTimeMillis();
        tmsForfeitBill.getForfeitBill().setCompanyId(companyId);
        tmsForfeitBill.getForfeitBill().setCreateUser(opUserId);
        tmsForfeitBill.getForfeitBill().setCreateUserName(opUserName);
        tmsForfeitBill.getForfeitBill().setCreateTime(time);
        tmsForfeitBill.getForfeitBill().setStatus(ForfeitBillStatusEnum.UNCONFIRMED.getType());
        tmsForfeitBill.getForfeitBillDetailList().forEach(forfeitBillDetailEsDTO -> {
            forfeitBillDetailEsDTO.setCompanyId(companyId);
            forfeitBillDetailEsDTO.setCreateTime(time);
            forfeitBillDetailEsDTO.setCreateUser(opUserId);
            forfeitBillDetailEsDTO.setCreateUserName(opUserName);
        });
        String waybillCodes = tmsForfeitBill.getForfeitBillDetailList().stream().map(ForfeitBillDetailEsDTO::getWaybillCode).collect(Collectors.joining("|"));
        tmsForfeitBill.getForfeitBill().setWaybillCode(waybillCodes);
    }

    /**
     * 修改时初始化信息
     */
    public void initOnUpdate() {
        Long time = System.currentTimeMillis();
        tmsForfeitBill.getForfeitBill().setCompanyId(companyId);
        tmsForfeitBill.getForfeitBill().setUpdateUser(opUserId);
        tmsForfeitBill.getForfeitBill().setUpdateUserName(opUserName);
        tmsForfeitBill.getForfeitBill().setUpdateTime(time);
        tmsForfeitBill.getForfeitBillDetailList().forEach(forfeitBillDetailEsDTO -> {
            forfeitBillDetailEsDTO.setCompanyId(companyId);
            if (null == forfeitBillDetailEsDTO.getId()) {
                forfeitBillDetailEsDTO.setForfeitBillId(tmsForfeitBill.getForfeitBill().getId());
                forfeitBillDetailEsDTO.setCreateTime(time);
                forfeitBillDetailEsDTO.setCreateUser(opUserId);
                forfeitBillDetailEsDTO.setCreateUserName(opUserName);
            } else {
                forfeitBillDetailEsDTO.setUpdateTime(time);
                forfeitBillDetailEsDTO.setUpdateUser(opUserId);
                forfeitBillDetailEsDTO.setUpdateUserName(opUserName);
            }
        });
        String waybillCodes = tmsForfeitBill.getForfeitBillDetailList().stream().map(ForfeitBillDetailEsDTO::getWaybillCode).collect(Collectors.joining("|"));
        tmsForfeitBill.getForfeitBill().setWaybillCode(waybillCodes);
    }
}
