package com.hivescm.tms.api.dto.es.fare.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/24
 */
@Data
@ToString
public class FareChangeConfirmReqDTO {
    @ApiModelProperty(value = "确认人",hidden = true)
    private Integer userId;
    @ApiModelProperty(value = "确认人",hidden = true)
    private String userName;
    @Required
    @ApiModelProperty("改费主表id")
    private Long ChangId;

    @ApiModelProperty(value = "当前网点",hidden = true)
    private Long curOrgId;

    @Required
    @ApiModelProperty("操作类型")
    private OperationType operationType;

    @ApiModelProperty("操作说明")
    private String instructions;

    public enum OperationType{

        AGREE(1,"同意"),DISAGREE(2,"不同意");


        OperationType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        private int type;

        private String name;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
