package com.hivescm.tms.api.dto.db.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
@Talbe(name="line_contact_arriveorg")
 */
@Data
@ToString
public class LineContactArriveorgDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
    @Id
    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    private @ApiModelProperty("主键")
    Long id;

    /**
     * 到达网点
    @NotEmpty
     */

    private @ApiModelProperty("始发网点Id")
    Long arriveOrgId;
    /**
     * 到达网点名称
    @NotEmpty
     */
    private  @ApiModelProperty("始发网点名称") String arriveOrgName;

    /**
     * 所属线路
    @NotEmpty
     */
    private @ApiModelProperty("线路id")
    Long lineId;
    /**
     * 部门ID
     */
    private @ApiModelProperty("部门Id")
    Long departmentId;

}