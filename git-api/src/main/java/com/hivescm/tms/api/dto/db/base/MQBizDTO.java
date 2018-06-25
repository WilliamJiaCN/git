package com.hivescm.tms.api.dto.db.base;

import lombok.Data;

@Data
public class MQBizDTO {

    //业务单元ID
    private Integer buId;
    //业务单元名字
    private String buName;
    //集团ID
    private Integer groupId;
    //操作用户ID
    private Integer userId;
    //操作ID
    private Integer operatorId;
    //所做操作的解释
    private String operatorMeg;
}
