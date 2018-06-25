package com.hivescm.tms.api.dto.db.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
@Talbe(name="line_contact_startorg")
 */
@Data
@ToString
public class LineContactStartorgDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
    @Id
    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 始发网点
    @NotEmpty
     */
    @ApiModelProperty("始发网点id")
    private Long startOrgId;

    /**
     * 始发网点名称
    @NotEmpty
     */
    @ApiModelProperty("始发网点名称")
    private String startOrgName;
    /**
     * 所属线路
    @NotEmpty
     */
    @ApiModelProperty("线路id")
    private Long lineId;


    /**
     * 部门ID
     */
    @ApiModelProperty("部门Id")
    private Long departmentId;

}