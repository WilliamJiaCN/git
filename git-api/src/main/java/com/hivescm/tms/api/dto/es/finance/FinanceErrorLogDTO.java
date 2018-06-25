package com.hivescm.tms.api.dto.es.finance;


import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceErrorLogDTO implements Serializable {
	
	private static final long serialVersionUID = -2700413122537197868L;
	private Long id;
	private String queueName;
	private String financeData;
	private String errorReason;
	private String createTime;
}
