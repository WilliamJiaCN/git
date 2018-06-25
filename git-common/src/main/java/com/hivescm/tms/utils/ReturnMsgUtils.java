
package com.hivescm.tms.utils;
/**
 * Function:  TODO 集成一些公共的返回提示 <br/>
 * Reason:    返回前台信息工具类 <br/>
 * Date:      2017年7月10日 上午9:24:58 <br/>
 * @author    anning
 * @version   
 * @since     JDK 1.8
 * @see       
 */

public class ReturnMsgUtils {

    /**
     * 数据停用成功
     */
    private static String BLOCKUPSUCCESS = "数据停用成功";
    
    /**
     * 数据启用成功
     */
    private static String STARTUSINGSUCCESS = "数据启用成功";
    
    
    /**
     * 数据审核成功
     */
    private static String CHECKSUCCESS = "数据审核成功";
    
    /**
     * 取消审核成功
     */
    private static String CANCELCHECKSUCCESS = "数据取消审核成功";
    
    
    /**
     * 数据停用失败
     */
    private static String BLOCKUPFAILED = "数据停用失败";
    
    /**
     * 数据启用失败
     */
    private static String STARTUSINGPFAILED = "数据启用失败";
    
    /**
     * 数据审核失败
     */
    private static String CHECKFAILED = "数据审核失败";
    /**
     * 数据取消审核失败
     */
    private static String CANCELCHECKFAILED = "数据取消审核失败";
    /**
     * 条！
     */
    private static String SUFFIX = "条！";
    
    /**
     * 换行符  \n
     */
    private static String LINEFEED = "\n";
    
    /**
     * 失败原因：
     */
    private static String FAILEDPFAILEDPREFIX = "失败原因：";
    
    /**
     * 数据已经被（
     */
    private static String BLOCKUPFAILEDPREFIX = "数据已经被（";
    
    
    /**
     * ）引用，不允许停用
     */
    private static String BLOCKUPFAILEDSUFFIX = "）引用，不允许停用";
    
    /**
     * 启用成功
     * 
     * @param count 成功条目数
     * @return  数据启用成功{}条
     */
    public static String startUsingSuccess(int count){
        return new StringBuilder(STARTUSINGSUCCESS).append(count)
                .append(SUFFIX).toString();
    }
    
    /**
     * 启用失败
     * @param count 条目数
     * @param reason 原因
     * @return  数据启用失败{}条！  \n  失败原因：{}
     */
    public static String startUsingFailed(int count, String reason){
        return new StringBuilder(STARTUSINGPFAILED).append(count)
                .append(SUFFIX).append(LINEFEED).append(FAILEDPFAILEDPREFIX)
                .append(reason).toString();
    }
    
    
    
    
    /**
     * 停用成功
     * @param count 条目数
     * @return  数据停用成功{}条！
     */
    public static String blockUpSuccess(int count){
        return new StringBuilder(BLOCKUPSUCCESS).append(count)
                .append(SUFFIX).toString();
    }
    
    /**
     * 停用失败
     * @param count 条目数
     * @param reason 原因
     * @return  数据停用失败{}条！  \n  失败原因：{}
     */
    public static String blockUpFailed(int count, String reason){
        return new StringBuilder(BLOCKUPFAILED).append(count)
                .append(SUFFIX).append(LINEFEED).append(FAILEDPFAILEDPREFIX)
                .append(reason).toString();
    }
    
    
    /**
     * 审核成功
     * 
     * @param count 成功条目数
     * @return  数据审核成功{}条
     */
    public static String checkSuccess(int count){
        return new StringBuilder(CHECKSUCCESS).append(count)
                .append(SUFFIX).toString();
    }
    
    public static String checkSuccess(int count,int type){
    	if(1==type){
    		return new StringBuilder(CHECKSUCCESS).append(count)
                    .append(SUFFIX).toString();
    	}else if(2==type){
    		return new StringBuilder(CANCELCHECKSUCCESS).append(count)
                    .append(SUFFIX).toString();
    	}
        return null;
    }
    
    /**
     * 审核失败
     * @param count 条目数
     * @param reason 原因
     * @return  数据审核失败{}条！  \n  失败原因：{}
     */
    public static String checkFailed(int count, String reason){
        return new StringBuilder(CHECKFAILED).append(count)
                .append(SUFFIX).append(LINEFEED).append(FAILEDPFAILEDPREFIX)
                .append(reason).toString();
    }
    
    /**
     * 取消审核成功
     * 
     * @param count 成功条目数
     * @return  数据审核成功{}条
     */
    public static String cancelCheckSuccess(int count){
        return new StringBuilder(CANCELCHECKSUCCESS).append(count)
                .append(SUFFIX).toString();
    }
    
    /**
     * 取消审核失败
     * @param count 条目数
     * @param reason 原因
     * @return  数据审核失败{}条！  \n  失败原因：{}
     */
    public static String cancelCheckFailed(int count, String reason){
        return new StringBuilder(CANCELCHECKFAILED).append(count)
                .append(SUFFIX).append(LINEFEED).append(FAILEDPFAILEDPREFIX)
                .append(reason).toString();
    }
    
    /**
     * 数据被引用导致停用失败
     * @param count条目数
     * @param orderNo 引用的单号
     * @return
     */
    public static String blockUpFailedByCite(int count, String orderNo){
        return new StringBuilder(BLOCKUPFAILED).append(count)
                .append(SUFFIX).append(LINEFEED).append(BLOCKUPFAILEDPREFIX)
                .append(orderNo).append(BLOCKUPFAILEDSUFFIX).toString();
    }
    
    /**
     * 其他的返回数据（非公共的）
     * 例：<br>
     *      通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     *      转义{}：   format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     *      转义\：        format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     * 
     * @param msg 字符串模板
     * @param objects 参数
     * @return
     */
    public static String otherMsg(String msg, Object... objects){
        return StrFormatterUtils.format(msg, objects);
    }
    
    public static void main(String[] args) {
        String msg = ReturnMsgUtils.blockUpFailed(1, "未知");
        System.out.println(msg);
        System.out.println("=============");
        msg = ReturnMsgUtils.blockUpFailedByCite(2, "SHZY12342，SJZY452343");
        System.out.println(msg);
        System.out.println("=============");
        msg = ReturnMsgUtils.blockUpSuccess(3);
        System.out.println(msg);
        System.out.println("=============");
        msg = ReturnMsgUtils.startUsingFailed(4, "数据库异常");
        System.out.println(msg);
        System.out.println("=============");
        msg = ReturnMsgUtils.startUsingSuccess(5);
        System.out.println(msg);
        System.out.println("=============");
    }
    
}
