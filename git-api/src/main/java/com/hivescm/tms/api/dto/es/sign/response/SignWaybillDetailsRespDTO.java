package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/20
 */
@Data
@ToString
public class SignWaybillDetailsRespDTO {



    private SignEsDTO signEsDTO;

    private List<SignDetailsEsDTO> signDetailsEsDTO;

}
