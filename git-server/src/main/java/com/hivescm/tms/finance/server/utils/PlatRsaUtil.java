package com.hivescm.tms.finance.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 杨彭伟
 * @date 2018-02-24 16:33
 */
public class PlatRsaUtil {
    private static Logger log = LoggerFactory.getLogger(PlatRsaUtil.class);

    public PlatRsaUtil() {
    }

    public static String addJsonSign(JSONObject reqJsonObj, String rsa_private) {
        if (reqJsonObj == null) {
            return "";
        } else {
            log.info("进入RSA加签名");
            String sign_src = genSignDataByJson(reqJsonObj);
            log.info("加签原串" + sign_src);

            try {
                return RSAUtil.sign(rsa_private, sign_src);
            } catch (Exception var4) {
                log.error("RSA加签名异常" + var4.getMessage(), var4);
                return "";
            }
        }
    }

    public static String addMapSign(Map reqMapObj, String rsa_private) {
        if (reqMapObj == null) {
            return "";
        } else {
            log.info("进入RSA加签名");
            String sign_src = genSignDataByMap(reqMapObj);
            log.info("加签原串" + sign_src);

            try {
                return RSAUtil.sign(rsa_private, sign_src);
            } catch (Exception var4) {
                log.error("RSA加签名异常" + var4.getMessage(), var4);
                return "";
            }
        }
    }

    public static boolean checkSign(String reqStr, String rsa_public) {
        JSONObject reqObj = JSON.parseObject(reqStr);
        if (reqObj == null) {
            return false;
        } else {
            String sign_type = reqObj.getString("sign_type");
            return checkSignRSAByJson(reqObj, rsa_public);
        }
    }

    public static boolean checkSignRSAByJson(JSONObject reqObj, String rsa_public) {
        if (reqObj == null) {
            return false;
        } else {
            log.info("进入签名验证");
            String sign = reqObj.getString("sign");
            String sign_src = genSignDataByJson(reqObj);
            log.info("待签名原串" + sign_src);
            log.info("签名串" + sign);

            try {
                if (RSAUtil.checksign(rsa_public, sign_src, sign)) {
                    log.info("RSA签名验证通过");
                    return true;
                } else {
                    log.info("RSA签名验证未通过");
                    return false;
                }
            } catch (Exception var5) {
                log.error("RSA签名验证异常" + var5.getMessage(), var5);
                return false;
            }
        }
    }

    public static boolean checkSignRSAMap(Map mapReqObj, String rsa_public) {
        if (mapReqObj == null) {
            return false;
        } else {
            log.info("进入签名验证");
            String sign = (String)mapReqObj.get("sign");
            String sign_src = genSignDataByMap(mapReqObj);
            log.info("待签名原串" + sign_src);
            log.info("签名串" + sign);

            try {
                if (RSAUtil.checksign(rsa_public, sign_src, sign)) {
                    log.info("RSA签名验证通过");
                    return true;
                } else {
                    log.info("RSA签名验证未通过");
                    return false;
                }
            } catch (Exception var5) {
                log.error("RSA签名验证异常" + var5.getMessage(), var5);
                return false;
            }
        }
    }

    public static String genSignDataByJson(JSONObject jsonObject) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            if (!"sign".equals(key)) {
                String value = jsonObject.getString(key);
                if (!StringUtils.isBlank(value)) {
                    content.append((i == 0 ? "" : "&") + key + "=" + value);
                }
            }
        }

        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }

        return signSrc;
    }

    public static String genSignDataByMap(Map map) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(map.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            if (!"sign".equals(key)) {
                String value = (String)map.get(key);
                if (!StringUtils.isBlank(value)) {
                    content.append((i == 0 ? "" : "&") + key + "=" + value);
                }
            }
        }

        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }

        return signSrc;
    }

//    public static void main(String[] args) {
//        String privatKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJPb6UtHkRtCmunLtxgWUUkqKVMqdMrvLxU4UzTRaNddI2tHUszyTSntfz+l1S3BjRBvjx1/yvrFRvneW7lmM9w+e5LPUnIhqnNrl2aeioOJWHz+Ba6qrRXz8kCf6kfsAMG4H2A2xMcb26ZiMPZxFKHinuKcW7bT+bXTFxrQsR/JAgMBAAECgYEAh2vK6F/LzyPZrngeYblPCavL3ZftEFCw1saXrrB9TYLIheD1PTBO7C/RdAH2lcnH4V3LvkDlL3iv4Pp/F/c7Vvvgs/LbpXwnPvYVtdkZ1x3AZRfS/5uSrSoAkiN0zEJnmb3Ywp7YlCYfVlke4u6dhQN+WxvqPl69VMBzNpagXWECQQDlBVUvIqQp6e0Gsp4oOj3HyQtCT+BsaRZkLtMNTq5pcz/83s1H0cIoU8dTT7LCZvRw+yjYgQ5YBY9D0CZBmwdfAkEApUbzmt2klNpf2apadyI+fYcbYBky3kb2q6YZ/xQuCU8eSJC4F2bPDDfxpsIqADj5A8KB74EnB6h1UT9rQONx1wJBAMXuFfDmv3p58aAYPxgFPd+soU5uOkd3iyKKVVzq41G/iU3CQSgQ4Px5a4tVFeltkVUTu/lhkEQCig7Rlj6c/YECQHwqUIrQ5nsZj5bDv1Du/glp/ev1Il0Q7PHJSJB0RZ2ivbqAVnzmNLgWM0o3ZjxikNj9QIaA/aRoLzLJtTa7aGMCQFEkTk/9gIYYKolMwMllO/SN+dO54W1Pc/Dx65ZsEwzgq+UEBb0BjbxbVebVRcaXam6OKIuCW2KwdQuMlY6AqeQ=";
//        Map redictData = new HashMap();
//        redictData.put("orderNo", "666");
//        redictData.put("businessCode", "6245");
//        redictData.put("sign", addMapSign(redictData, privatKey));
//        boolean result = HiveRSAUtil.checkSign("6245", "businessCode=6245&orderNo=666", addMapSign(redictData, privatKey));
//        System.out.println(addMapSign(redictData, privatKey));
//        System.out.println(result);
//    }
}

