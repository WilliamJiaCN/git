/**
 * Project Name:wms-base-server
 * File Name:ResponseMsg.java
 * Package Name:com.hivescm.wms.server.base.domain.dto
 * Date:2017年6月20日下午6:21:19
 *
*/

package com.hivescm.tms.api.dto.common;

import com.hivescm.tms.enums.ReturnCodeEnum;
import com.hivescm.tms.utils.StrFormatterUtils;

/**
 * ClassName:ResponseMsg <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 返回操作结果信息. <br/>
 * Date:     2017年6月20日 下午6:21:19 <br/>
 * @author   zhangwenhao
 * @version
 * @since    JDK 1.8
 * @see
 */
public class ResponseMsgDTO {
    /**
     * 结果码
     */
	private int retCode;
    /**
     * 结果描述
     */
	private String retMsg;



	public ResponseMsgDTO() {

		super();
		// TODO Auto-generated constructor stub

	}

	public ResponseMsgDTO(int retCode, String retMsg, Object... argArray) {
		super();
		this.retCode = retCode;
		this.retMsg = StrFormatterUtils.format(retMsg,argArray);
	}
	
	/**
	 * 重载构造函数 
	 * 可以将枚举中的{}占位符替换成具体的参数
	 * @param returnCodeEnum 
	 * @param argArray 参数列表
	 * @author anning
	 * 
	 */
	public ResponseMsgDTO(ReturnCodeEnum returnCodeEnum, Object... argArray) {
        this.retCode = returnCodeEnum.getType();
        this.retMsg = StrFormatterUtils.format(returnCodeEnum.getName(), argArray);
    }

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg, Object... argArray) {
		this.retMsg = StrFormatterUtils.format(retMsg,argArray);
	}

    @Override
    public String toString() {
        return "ResponseMsgDto [retCode=" + retCode + ", retMsg=" + retMsg + "]";
    }

}

