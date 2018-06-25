package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 18:02
 * @company 蜂网供应链管理（上海）有限公司
 */
public class SaveFinanceManageCashSerialReqDTO implements Serializable {

    private static final long serialVersionUID = 7212822837268800281L;

    List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList;
}
