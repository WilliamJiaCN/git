package com.hivescm.tms.api.dto.es.finance;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

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
