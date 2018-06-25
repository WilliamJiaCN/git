/**
 * Project Name:wms-base-server
 * File Name:ResponseMsg.java
 * Package Name:com.hivescm.wms.server.base.domain.dto
 * Date:2017年6月20日下午6:21:19
 *
*/

package com.hivescm.tms.utils;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @ClassName: QuenePojo
 * @Description: 消息队列传递实体
 * @author Zhangxiaoping
 * @date 2017年8月22日
 *
 */
@Getter
@Setter
@ToString
public class QuenePojo<T>  implements Serializable {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 4519862532473082094L;
	/**
	 * 数据类型
	 */
	private Integer type;
	/**
	 * 传递对象
	 */
	private T context;

	
}
