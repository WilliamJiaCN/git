package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 派车单发车请求数据对象
 *
 * @author 李洪春
 * @since 2017/8/14 20:51
 */
@Data
@ToString
public class DispatcherToOmsReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -875225972115997910L;

   private DispatcherEsDTO dispatcherEsDTO;
   private List<DispatcherDetailEsDTO> dispatcherDetailEsDTO;
   private Integer type;
   private String changeNo;
}
