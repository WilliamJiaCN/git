package com.hivescm.tms.api.dto.es.dispatcher.response;

import java.io.Serializable;

import com.hivescm.tms.api.dto.es.dispatcher.component.TmsDispatcherEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DispatcherDetailsStatusRespDTO implements Serializable {

   private static final long serialVersionUID = 1L;
   /**
    * 主键
    */
   private WaybillEsDTO waybillEsDTO;
   
   
   
   private Integer dispatcherStatus;
   
   private TmsDispatcherEsDTO tmsDispatcherEsDTO;
   
  
}
