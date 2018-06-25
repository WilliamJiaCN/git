package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * 区域类 用于新增与修改
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRegionReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //添加数据时 必填
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    //添加数据时 必填
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;
    //添加数据时 必填
    @Mapping
    @ApiModelProperty("覆盖区域Id")
    private String coverAreaId;
    //添加数据时 必填
    @Mapping
    @ApiModelProperty("覆盖区域")
    private String coverArea;
    //添加数据时 必填
    @Mapping
    @ApiModelProperty("覆盖类型Id")
    private String coverTypeId;
    //添加数据时 必填
    @Mapping
    @ApiModelProperty("覆盖类型")
    private String coverType;
    @Mapping
    @ApiModelProperty("线路Ids")
    private String routeIds;
    @Mapping
    @ApiModelProperty("线路名称s")
    private String routeNames;
    //司机的私有道路 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("道路Ids")
    private String roadId;
    // 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("道路名称s")
    private String roadName;
    // 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("号码类型Ids")
    private String numberTypeId;
    // 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("号码类型s")
    private String numberType;
    // 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("开始号码s")
    private String startNum;
    // 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("结束号码s")
    private String endNum;
    // 拼接的字符串用于回显
    @Mapping
    @ApiModelProperty("站点号s")
    private String siteNum;
    //添加数据时 必填  给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("省Id")
    private String provinceId;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("市Id")
    private String cityId;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("县Id")
    private String countyId;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("镇Id")
    private String townId;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("县名称")
    private String countyName;
    //添加数据时 必填 给司机添加私有道路时需要
    @Mapping
    @ApiModelProperty("镇名称")
    private String townName;

    public boolean equals(Object o) {
        if (this == o) return true;  //先判断o是否为本对象，如果是就肯定是同一对象了，this 指向当前的对象
        if (o == null || getClass() != o.getClass()) return false; //再判断o是否为null，和o.类对象和本类对象是否一致
        TmsScheduleRegionReqDTO reqDTO = (TmsScheduleRegionReqDTO) o;  //再把o对象强制转化为TmsScheduleRegionReqDTO类对象
        return  Objects.equals(coverAreaId, reqDTO.coverAreaId)
                &&Objects.equals(routeIds, reqDTO.routeIds)
                &&Objects.equals(roadId, reqDTO.roadId);  //查看两个对象的属性值是否相等,返回结果
    }
}
