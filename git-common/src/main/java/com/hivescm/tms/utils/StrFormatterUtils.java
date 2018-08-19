
package com.hivescm.tms.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Function:  TODO  <br/>
 * Reason:    格式化字符串 <br/>
 * Date:      2017年7月7日 下午2:46:37 <br/>
 * @author    anning
 * @version   
 * @since     JDK 1.8
 * @see       
 */

public class StrFormatterUtils {

    private static final String EMPTY_JSON = "{}";
    private static final char C_BACKSLASH = '\\';
    private static final char C_DELIM_START = '{';
    private static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    
    /**
     * 格式化字符串<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     *      通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     *      转义{}：   format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     *      转义\：        format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     * @param strPattern 字符串模板
     * @param argArray 参数列表
     * @return 结果
     */
    public static String format(final String strPattern, final Object... argArray) {
        if(StringUtils.isEmpty(strPattern) || ArrayUtils.isEmpty(argArray)){
            return strPattern;
        }
        final int strPatternLength = strPattern.length();

        //初始化定义好的长度以获得更好的性能
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;//记录已经处理到的位置
        int delimIndex;//占位符所在位置
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(EMPTY_JSON, handledPosition);
            if (delimIndex == -1) {//剩余部分无占位符
                if (handledPosition == 0) { //不带占位符的模板直接返回
                    return strPattern;
                } else { //字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                    sbuf.append(strPattern, handledPosition, strPatternLength);
                    return sbuf.toString();
                }
            } else {
                if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == C_BACKSLASH) {//转义符
                    if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == C_BACKSLASH) {//双转义符
                        //转义符之前还有一个转义符，占位符依旧有效
                        sbuf.append(strPattern, handledPosition, delimIndex - 1);
                        sbuf.append(str(argArray[argIndex],CHARSET_UTF_8));
                        handledPosition = delimIndex + 2;
                    } else {
                        //占位符被转义
                        argIndex--;
                        sbuf.append(strPattern, handledPosition, delimIndex - 1);
                        sbuf.append(C_DELIM_START);
                        handledPosition = delimIndex + 1;
                    }
                } else {//正常占位符
                    sbuf.append(strPattern, handledPosition, delimIndex);
                    sbuf.append(str(argArray[argIndex],CHARSET_UTF_8));
                    handledPosition = delimIndex + 2;
                }
            }
        }
        //加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPattern.length());
        
        return sbuf.toString();
    }
    
    /**
     * 将对象转为字符串<br>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
     * 2、对象数组会调用Arrays.toString方法
     * 
     * @param obj 对象
     * @param charset 编码
     * @return 字符串
     */
    private static String str(Object obj, Charset charset){
        if(null == obj){
            return null;
        }
        
        if(obj instanceof String) {
            return (String)obj;
        }else if(obj instanceof byte[] || obj instanceof Byte[]){
            return str((Byte[])obj, charset);
        }else if(obj instanceof ByteBuffer){
            return str((ByteBuffer)obj, charset);
        }else if(obj.getClass().isArray()){
            return arrayToString(obj);
        }
        
        return obj.toString();
    }
    
    /**
     * 数组或集合转String
     * 
     * @param obj 集合或数组对象
     * @return 数组字符串，与集合转字符串格式相同
     */
    private static String arrayToString(Object obj) {
        if (null == obj) {
            return null;
        }
        if (obj.getClass().isArray()) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception e) {
                final String className = obj.getClass().getComponentType().getName();
                switch (className) {
                    case "long":
                        return Arrays.toString((long[]) obj);
                    case "int":
                        return Arrays.toString((int[]) obj);
                    case "short":
                        return Arrays.toString((short[]) obj);
                    case "char":
                        return Arrays.toString((char[]) obj);
                    case "byte":
                        return Arrays.toString((byte[]) obj);
                    case "boolean":
                        return Arrays.toString((boolean[]) obj);
                    case "float":
                        return Arrays.toString((float[]) obj);
                    case "double":
                        return Arrays.toString((double[]) obj);
                    default:
                        return null;
                }
            }
        }
        return obj.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(StrFormatterUtils.format("this is {} for {}", "a", "b"));
        System.out.println("=====================");
        System.out.println(StrFormatterUtils.format("this is \\{} for {}", "a", "b"));
        System.out.println("=====================");
        System.out.println(StrFormatterUtils.format("this is \\\\{} for {}", "a", "b"));
        System.out.println("=====================");
        System.out.println(StrFormatterUtils.format("this is aa for bb"));
    }
}
