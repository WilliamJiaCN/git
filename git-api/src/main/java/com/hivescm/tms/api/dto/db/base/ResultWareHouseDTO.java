package com.hivescm.tms.api.dto.db.base;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ResultWareHouseDTO {

    private List<BaseTmsWarehouseDTO> warehouseDTOs;

    private Integer totalNum;

    private Integer totalPage;
}
