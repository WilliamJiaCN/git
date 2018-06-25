package com.hivescm.tms.api.dto.db.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("基础地址")
public class AdressTreeDTO implements Serializable {
    private static final long serialVersionUID = 5059593733632743199L;
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父id")
    private String parentId;

    private List<AdressTreeDTO> children;

    public AdressTreeDTO(){}
    public AdressTreeDTO(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
