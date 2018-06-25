package com.hivescm.tms.utils;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @FileName:CommonUtil.java
 * @Description: 字符串工具类
 * @Author: qsk
 * @Date: 2017年9月8日 下午5:02:33
 * @since: JDK 1.7
 */
public class StringUtil {

    /**
     * 数字 正则校验
     */
    private static final Pattern p = Pattern.compile("^\\d*$");

    /**
     * 将对象转成json字符串
     */
    /*public static String toString(Object o) {
        if (o == null) {
            return null;
        }
        return JSONObject.toJSONString(o);
    }*/

    /**
     * @param str
     * @return
     * @Method：判断字符串是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    /**
     * @param str
     * @return
     * @Method：判断字符串是否为空 包括"null"
     */
    public static boolean isBlankALL(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    /**
     * @param str
     * @return
     * @Method：判断字符串不是空
     */
    public static boolean isNotBlank(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * CommonUtil.isBlank(null)= false StringUtils.isBlank(new ArrayList()) =
     * false StringUtils.isBlank(Arrays.asList(new Object())) = true
     *
     * @param list
     * @return
     * @Method：检查list集合是否为空
     */
    public static boolean isNotBlankList(@SuppressWarnings("rawtypes") List list) {
        return null != list && list.size() > 0;
    }

    /**
     * CommonUtil.isBlank(null)= true StringUtils.isBlank(new ArrayList()) =
     * true StringUtils.isBlank(Arrays.asList(new Object())) = false
     *
     * @param list
     * @return
     * @Method：检查list集合是否为空
     */
    public static boolean isBlankList(@SuppressWarnings("rawtypes") List list) {
        return null == list || list.size() == 0;
    }

    /**
     * 获取32位UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 判断是否是正数int类型
     *
     * @param param 字符串
     * @return true 是正数数字，false 不是正数数字
     */
    public static boolean isInteger(String param) {
        if (isBlank(param)) {
            return false;
        }
        return p.matcher(param).matches();
    }

    /**
     * @param length
     * @return
     * @Method：java生成随机字符串
     */
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * @param code
     * @return
     * @Method：MD5生成
     */
    public static String getMd5MessageDigest(String code) {
        StringBuilder sb = null;
        try {
            // 获取messageDigest对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 对什么进行加密
            byte[] digest = messageDigest.digest(code.getBytes());
            sb = new StringBuilder();
            for (byte b : digest) {
                int input = b & 0xff;
                if (input < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(input));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * @param id
     * @return
     * @Method：把str字符串转换成list
     */
    public static List<String> stringToList(String id) {
        if (id != null && !"".equals(id)) {
            List<String> ids = new ArrayList<String>();
            id = id.replace("[", "").replace("]", "").replace("\"", "");
            String[] idInfo = id.split(",");
            for (int i = 0; i < idInfo.length; i++) {
                ids.add(idInfo[i].trim());
            }
            return ids;
        } else {
            return null;
        }
    }
    
    /**
     * 拆分集合
     * @param <T>
     * @param resList  要拆分的集合
     * @param count    每个集合的元素个数
     * @return  返回拆分后的各个集合
     */
    public static  <T> List<List<T>> split(List<T> resList,int count){
        
        if(resList==null ||count<1)
            return  null ;
        List<List<T>> ret=new ArrayList<List<T>>();
        int size=resList.size();
        if(size<=count){ //数据量不足count指定的大小
            ret.add(resList);
        }else{
            int pre=size/count;
            int last=size%count;
            //前面pre个集合，每个大小都是count个元素
            for(int i=0;i<pre;i++){
                List<T> itemList=new ArrayList<T>();
                for(int j=0;j<count;j++){
                    itemList.add(resList.get(i*count+j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if(last>0){
                List<T> itemList=new ArrayList<T>();
                for(int i=0;i<last;i++){
                    itemList.add(resList.get(pre*count+i));
                }
                ret.add(itemList);
            }
        }
        return ret;
        
    }
    
    
    private static final Pattern pattern = Pattern.compile("\\d+");
    /**
     * 判断字符串是否由纯数字组成
     * @param str
     * @return
     */
    public static Boolean isNumber(String str){
    	if (null == str) {
			return false;
		}
    	Matcher matcher = pattern.matcher(str);
    	return matcher.matches();
    }

    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * @param id
     * @return
     * @Method：把str字符串转换成list 用";"分隔的字符串
     */
    public static List<String> stringTransforList(String id) {
        if (id != null && !"".equals(id)) {
            List<String> ids = new ArrayList<String>();
            id = id.replace("[", "").replace("]", "").replace("\"", "");
            String[] idInfo = id.split(";");
            for (int i = 0; i < idInfo.length; i++) {
                ids.add(idInfo[i].trim());
            }
            return ids;
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return
     * @Method：把str字符串转换成list 用"/"分隔的字符串
     */
    public static List<String> stringBecomeList(String id) {
        if (id != null && !"".equals(id)) {
            List<String> ids = new ArrayList<String>();
            id = id.replace("[", "").replace("]", "").replace("\"", "");
            String[] idInfo = id.split("/");
            for (int i = 0; i < idInfo.length; i++) {
                ids.add(idInfo[i].trim());
            }
            return ids;
        } else {
            return null;
        }
    }

    /**
     * 深度复制列表
     *
     * @param src
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopyList(List<T> src) {
        List<T> dest = null;
        try {
            if (isBlankList(src)) {
                return null;
            }
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return dest;
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
////        List<String> resList=Arrays.asList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95");
////        List<List<String>> ret=split(resList,10);
////        
////        for(int i=0;i<ret.size();i++){
////            System.out.println(ret.get(i));
////        }
//        System.out.println(isNumber(""));
//    
//    }
/*public static void main(String[] args) {

}*/
}
