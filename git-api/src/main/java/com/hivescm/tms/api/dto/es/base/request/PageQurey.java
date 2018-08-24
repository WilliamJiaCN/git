package com.hivescm.tms.api.dto.es.base.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ClassName:PageQurey <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 分页查询参数实体类. <br/>
 * Date: 2017年6月21日 <br/>
 * 
 * @author yuancongcong
 * @version
 * @since JDK 1.8
 * @see
 */

public class PageQurey implements Serializable {

	private static final long serialVersionUID = 2512619704606573667L;
	// 每页条数
	@ApiModelProperty("页大小")
	private Integer pageSize = 10;
	// 起始条数 从0开始
	@ApiModelProperty("起始条数")
	private Integer pageStart;
	// 起始页 从1开始
	@ApiModelProperty("起始页")
	private Integer pageNumber = 1;
	public Integer getPageSize() {
		return pageSize;
	}

	
	public void setPageSize(Integer pageSize) {
		if (pageSize < 0) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}

	public Integer getPageStart() {
		if (pageStart == null) {// 如果没有设值则 自己计算
			pageStart = (pageNumber - 1) * pageSize;
		}
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}
}