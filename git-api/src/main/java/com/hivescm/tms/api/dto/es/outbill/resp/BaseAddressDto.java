package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <b>Description:</b><br>
 * 基础地址实体 <br><br>
 * <p>
 * <b>Note</b><br>
 * <b>ProjectName:</b> base-org-permission
 * <br><b>PackageName:</b> com.hivescm.org.dto
 * <br><b>Date:</b> 2017/11/21 16:46
 *
 * @author DongChunfu
 * @version 1.0
 * @since JDK 1.8
 */
@ApiModel(description = "基础地址实体")
public class BaseAddressDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "国家地区ID")
	private String regionCode = "CHN";

	@ApiModelProperty(value = "国家地区名称")
	private String regionName = "中国";

	@ApiModelProperty(value = "省ID")
	private Long invoiceProvId=0L;

	@ApiModelProperty(value = "省名称")
	private String invoiceProvName="";

	@ApiModelProperty(value = "城市ID")
	private Long invoiceCityId=0L;

	@ApiModelProperty(value = "城市名称")
	private String invoiceCityName="";

	@ApiModelProperty(value = "区ID")
	private Long invoiceDistrictId=0L;

	@ApiModelProperty(value = "区名称")
	private String invoiceDistrictName="";

	@ApiModelProperty(value = "街道ID")
	private Long invoiceStreetId=0L;

	@ApiModelProperty(value = "街道名称")
	private String invoiceStreetName="";

	@ApiModelProperty(value = "地址")
	private String address="";

	public BaseAddressDto() {
	}

	/**
	 * 详细地址
	 * 国家 + 行政区划 + 地址
	 *
	 * @return 详细地址
	 */
	public String getLinkedAddressDetail() {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(getRegionName())) {
			sb.append(getRegionName());
		}
		if (!StringUtils.isEmpty(getInvoiceProvName())) {
			sb.append(getInvoiceProvName());
		}
		if (!StringUtils.isEmpty(getInvoiceCityName())) {
			sb.append(getInvoiceCityName());
		}
		if (!StringUtils.isEmpty(getInvoiceDistrictName())) {
			sb.append(getInvoiceDistrictName());
		}
		if (!StringUtils.isEmpty(getInvoiceStreetName())) {
			sb.append(getInvoiceStreetName());
		}
		if (!StringUtils.isEmpty(getAddress())) {
			sb.append(getAddress());
		}
		return sb.toString();
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getInvoiceProvId() {
		return invoiceProvId;
	}

	public void setInvoiceProvId(Long invoiceProvId) {
		this.invoiceProvId = invoiceProvId;
	}

	public String getInvoiceProvName() {
		return invoiceProvName;
	}

	public void setInvoiceProvName(String invoiceProvName) {
		this.invoiceProvName = invoiceProvName;
	}

	public Long getInvoiceCityId() {
		return invoiceCityId;
	}

	public void setInvoiceCityId(Long invoiceCityId) {
		this.invoiceCityId = invoiceCityId;
	}

	public String getInvoiceCityName() {
		return invoiceCityName;
	}

	public void setInvoiceCityName(String invoiceCityName) {
		this.invoiceCityName = invoiceCityName;
	}

	public Long getInvoiceDistrictId() {
		return invoiceDistrictId;
	}

	public void setInvoiceDistrictId(Long invoiceDistrictId) {
		this.invoiceDistrictId = invoiceDistrictId;
	}

	public String getInvoiceDistrictName() {
		return invoiceDistrictName;
	}

	public void setInvoiceDistrictName(String invoiceDistrictName) {
		this.invoiceDistrictName = invoiceDistrictName;
	}

	public Long getInvoiceStreetId() {
		return invoiceStreetId;
	}

	public void setInvoiceStreetId(Long invoiceStreetId) {
		this.invoiceStreetId = invoiceStreetId;
	}

	public String getInvoiceStreetName() {
		return invoiceStreetName;
	}

	public void setInvoiceStreetName(String invoiceStreetName) {
		this.invoiceStreetName = invoiceStreetName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BaseAddressDto{");
		sb.append(super.toString());
		sb.append("invoiceProvId=").append(invoiceProvId);
		sb.append(", invoiceProvName='").append(invoiceProvName).append('\'');
		sb.append(", invoiceCityId=").append(invoiceCityId);
		sb.append(", invoiceCityName='").append(invoiceCityName).append('\'');
		sb.append(", invoiceDistrictId=").append(invoiceDistrictId);
		sb.append(", invoiceDistrictName='").append(invoiceDistrictName).append('\'');
		sb.append(", invoiceStreetId=").append(invoiceStreetId);
		sb.append(", invoiceStreetName='").append(invoiceStreetName).append('\'');
		sb.append(", address='").append(address).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
