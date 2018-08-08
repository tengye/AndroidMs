package com.teng.androidms.android.encrypt;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * Created by teng on 2018/6/28.
 */
public class RsaTest {

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    public static void main(String[] args) throws Exception {

        final Map<String, Object> keyMap = createKey();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        System.out.println("publicKey: " + publicKey);
        System.out.println("privateKey: " + privateKey);

        String message = "RAS测试";

        /******************  加密 *******************/
        byte[] encodeData = encryptByPrivateKey(message.getBytes(), privateKey);
        String encodeStr = Base64Utils.encoderData(encodeData);
        System.out.println("\n私钥加密结果:" + encodeStr);

        final byte[] decodeData = decryptByPublicKey(Base64Utils.decoderData(encodeStr), publicKey);
        String decodeStr = new String(decodeData);
        System.out.println("公钥解密结果:" + decodeStr);

        /******************  签名 *******************/
        String sign = sign(message.getBytes(), privateKey);
        System.out.println("\n签名结果:" + sign);

        boolean verify = verify(message.getBytes(), publicKey, sign);
        System.out.println("签名能否校验:" + verify);


        List<String> list = new ArrayList<>();
        System.out.println("签名能否校验:" + list.get(2));
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> createKey() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024); // keySize

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 获得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64Utils.encoderData(key.getEncoded());
    }

    /**
     * 获得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64Utils.encoderData(key.getEncoded());
    }

    /**
     * 公钥解密
     *
     * @param data 消息
     * @param key  公钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {

        // 将公钥字符串转换为公钥
        byte[] keyBytes = Base64Utils.decoderData(key);
        // 此类表示公钥的ASN.1编码，根据ASN.1类型编码
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // Cipher: 加密和解密提供密码功能
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = Base64Utils.decoderData(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 数字签名
     * @param data 消息
     * @param key 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String key) throws Exception {

        byte[] keyBytes = Base64Utils.decoderData(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data);

        return Base64Utils.encoderData(signature.sign());
    }

    /**
     * 校验数字签名
     * @param data
     * @param key 公钥
     * @param sign 私钥加密后的签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String key, String sign) throws Exception {
        // 将公钥字符串转换为公钥
        byte[] keyBytes = Base64Utils.decoderData(key);
        // 此类表示公钥的ASN.1编码，根据ASN.1类型编码
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(data);

        return signature.verify(Base64Utils.decoderData(sign));
    }


}
