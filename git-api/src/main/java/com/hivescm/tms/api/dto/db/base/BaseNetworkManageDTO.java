package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BaseNetworkManageDTO {

    @Mapping
    @ApiModelProperty("网点编码")
    private String networkCode;

    @Mapping
    @ApiModelProperty("网点名称")
    private String networkName;
    
    @Mapping
    @ApiModelProperty("集团ID")
    private Integer groupId;

//    @Mapping
//    @ApiModelProperty("网点覆盖地址编码（省）")
//    private String provCode;
//
//    @Mapping
//    @ApiModelProperty("网点覆盖地址编码（市）")
//    private String cityCode;
//
//    @Mapping
//    @ApiModelProperty("网点覆盖地址编码（区县）")
//    private String disCode;

    @Mapping
    @ApiModelProperty("地址")
    private List<BaseAddressDTO> baseAddressDTO;

    @Mapping
    @ApiModelProperty("地址信息（树形结构）")
    private List<AdressTreeDTO> adressTreeDTOS;
    @Mapping
    @ApiModelProperty("创建人")
    private String createUser;

}
