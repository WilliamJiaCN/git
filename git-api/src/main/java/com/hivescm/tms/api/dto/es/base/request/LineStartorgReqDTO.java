package com.hivescm.tms.api.dto.es.base.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author 
@Talbe(name="line_contact_startorg")
 */
@Data
@ToString
public class LineStartorgReqDTO implements Serializable {
    

    /**
     * 始发网点
    @NotEmpty
     */
    private Integer startOrgId;

    /**
     * 所属线路
    @NotEmpty
     */
    private Integer lineId;
    /**
     * 
    @NotEmpty
     */
    private Integer createUser;
    /**
     * 
    @NotEmpty
     */
    private Long createTime;
 
    private Integer updateUser;
   
    private Long updateTime;

    private static final long serialVersionUID = 1L;

   
}