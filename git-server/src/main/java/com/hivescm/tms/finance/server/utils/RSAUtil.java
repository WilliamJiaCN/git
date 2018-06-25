package com.hivescm.tms.finance.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 杨彭伟
 * @date 2018-02-24 16:35
 */
public class RSAUtil {
    private static Logger log = LoggerFactory.getLogger(RSAUtil.class);
    private static RSAUtil instance;
    private static final String RSA_ENCRYPT_KEY = "encrypt=";
    private static final String DECRYPT_CONTENT = "id_no,card_no,bind_mob,acct_name,cvv2,vali_date";

    private RSAUtil() {
    }

    public static RSAUtil getInstance() {
        return null == instance ? new RSAUtil() : instance;
    }

    private void generateKeyPair(String key_path, String name_prefix) {
        KeyPairGenerator keygen = null;

        try {
            keygen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException var13) {
            log.error(var13.getMessage());
        }

        SecureRandom secrand = new SecureRandom();
        secrand.setSeed("21cn".getBytes());
        keygen.initialize(1024, secrand);
        KeyPair keys = keygen.genKeyPair();
        PublicKey pubkey = keys.getPublic();
        PrivateKey prikey = keys.getPrivate();
        String pubKeyStr = Base64.getBASE64(pubkey.getEncoded());
        String priKeyStr = Base64.getBASE64(prikey.getEncoded());
        File file = new File(key_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            FileOutputStream fos = new FileOutputStream(new File(key_path + name_prefix + "_RSAKey_private.txt"));
            fos.write(priKeyStr.getBytes());
            fos.close();
            fos = new FileOutputStream(new File(key_path + name_prefix + "_RSAKey_public.txt"));
            fos.write(pubKeyStr.getBytes());
            fos.close();
        } catch (IOException var12) {
            log.error(var12.getMessage());
        }

    }

    private static String getKeyContent(String key_file) {
        File file = new File(key_file);
        BufferedReader br = null;
        InputStream ins = null;
        StringBuffer sReturnBuf = new StringBuffer();

        Object var6;
        try {
            ins = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
            String readStr = null;

            for(readStr = br.readLine(); readStr != null; readStr = br.readLine()) {
                sReturnBuf.append(readStr);
            }

            return sReturnBuf.toString();
        } catch (IOException var20) {
            var6 = null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

            if (ins != null) {
                try {
                    ins.close();
                    ins = null;
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }

        return (String)var6;
    }

    public static String sign(String prikeyvalue, String sign_str) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getBytesBASE64(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            Signature signet = Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(sign_str.getBytes("UTF-8"));
            byte[] signed = signet.sign();
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
        } catch (Exception var7) {
            log.error("签名失败," + var7.getMessage());
            return null;
        }
    }

    public static boolean checksign(String pubkeyvalue, String oid_str, String signed_str) {
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.getBytesBASE64(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.getBytesBASE64(signed_str);
            Signature signetcheck = Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oid_str.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (Exception var8) {
            log.error("签名验证异常," + var8.getMessage());
            return false;
        }
    }

    public static String encrypt(String source, String public_key) throws Exception {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(public_key);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509ek);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, publicKey);
        byte[] sbt = source.getBytes("UTF-8");
        byte[] epByte = cipher.doFinal(sbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }

    public static String decrypt(String cryptograph, String private_key) throws Exception {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(private_key);
        PKCS8EncodedKeySpec s8ek = new PKCS8EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(s8ek);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) {
        String prikeyvalue = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJPb6UtHkRtCmunLtxgWUUkqKVMqdMrvLxU4UzTRaNddI2tHUszyTSntfz+l1S3BjRBvjx1/yvrFRvneW7lmM9w+e5LPUnIhqnNrl2aeioOJWHz+Ba6qrRXz8kCf6kfsAMG4H2A2xMcb26ZiMPZxFKHinuKcW7bT+bXTFxrQsR/JAgMBAAECgYEAh2vK6F/LzyPZrngeYblPCavL3ZftEFCw1saXrrB9TYLIheD1PTBO7C/RdAH2lcnH4V3LvkDlL3iv4Pp/F/c7Vvvgs/LbpXwnPvYVtdkZ1x3AZRfS/5uSrSoAkiN0zEJnmb3Ywp7YlCYfVlke4u6dhQN+WxvqPl69VMBzNpagXWECQQDlBVUvIqQp6e0Gsp4oOj3HyQtCT+BsaRZkLtMNTq5pcz/83s1H0cIoU8dTT7LCZvRw+yjYgQ5YBY9D0CZBmwdfAkEApUbzmt2klNpf2apadyI+fYcbYBky3kb2q6YZ/xQuCU8eSJC4F2bPDDfxpsIqADj5A8KB74EnB6h1UT9rQONx1wJBAMXuFfDmv3p58aAYPxgFPd+soU5uOkd3iyKKVVzq41G/iU3CQSgQ4Px5a4tVFeltkVUTu/lhkEQCig7Rlj6c/YECQHwqUIrQ5nsZj5bDv1Du/glp/ev1Il0Q7PHJSJB0RZ2ivbqAVnzmNLgWM0o3ZjxikNj9QIaA/aRoLzLJtTa7aGMCQFEkTk/9gIYYKolMwMllO/SN+dO54W1Pc/Dx65ZsEwzgq+UEBb0BjbxbVebVRcaXam6OKIuCW2KwdQuMlY6AqeQ=";
        String publickeyvalue = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCT2+lLR5EbQprpy7cYFlFJKilTKnTK7y8VOFM00WjXXSNrR1LM8k0p7X8/pdUtwY0Qb48df8r6xUb53lu5ZjPcPnuSz1JyIapza5dmnoqDiVh8/gWuqq0V8/JAn+pH7ADBuB9gNsTHG9umYjD2cRSh4p7inFu20/m10xca0LEfyQIDAQAB";
        String sign_str = "1,14,11,1,1,12222222222222222,1000,张三,2121,招商银行,11111111,1,1,1,0,1,呜呜呜呜,1,11111,-1";
        RSAUtil rSAUtil = new RSAUtil();
        String sign = sign(prikeyvalue, sign_str);
        boolean result = checksign(publickeyvalue, sign_str, sign);
        System.out.println(result);

        try {
            String secertStr = encrypt(sign_str, publickeyvalue);
            String oldStr = decrypt(secertStr, prikeyvalue);
            System.out.println(oldStr);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }
}
