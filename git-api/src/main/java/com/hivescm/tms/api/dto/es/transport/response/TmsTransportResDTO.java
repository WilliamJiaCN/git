package com.hivescm.tms.api.dto.es.transport.response;

import com.hivescm.tms.api.dto.es.fare.FareInfoEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TmsTransportResDTO implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 2689648560122525903L;

    @ApiModelProperty(value = "更改备注", required = true)
    private String remarks;

    @ApiModelProperty(value = "操作说明", required = true)
    private String description;

    @ApiModelProperty(value = "申请人姓名", hidden = true)
    private String createUserName;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty(value = "修改人姓名", hidden = true)
    private String updateUserName;

    @ApiModelProperty("修改时间")
    private Long updateTime;

    @ApiModelProperty(value = "确认人姓名", hidden = true)
    private String confirmUserName;

    @ApiModelProperty("确认时间")
    private Long confirmTime;

    @ApiModelProperty("发车批次")
    private TransportInfoResDTO transportInfoResDTO;


    @ApiModelProperty("批次线路信息")
    private List<TransportLineResDTO> transportLineResDTOList;

    @ApiModelProperty("原费用信息")
    private List<FareInfoEsDTO> oldFareInfoEsDTOList;

    @ApiModelProperty("费用信息")
    private List<FareInfoEsDTO> newFareInfoEsDTOList;

}
