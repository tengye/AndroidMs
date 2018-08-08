package com.teng.androidms.android.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by teng on 2018/6/28.
 */
public class AesTest {

    public static final String KEY_MD5 = "MD5";
    public static final String KEY_AES = "AES";
    public static final String KEY_PAS = "1234567890123456";
    public static final String CHARACTER_ENCODE = "UTF-8";
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String data = "aes测试";
        final String xxx = doAes(data, KEY_PAS, Cipher.ENCRYPT_MODE);
        System.out.println(xxx);

        final String pas = doAes(xxx, KEY_PAS, Cipher.DECRYPT_MODE);
        System.out.println(pas);
    }

    /**
     * 解密时候必须是十六进制
     * @param data
     * @param key
     * @param mode
     * @return
     */
    public static String doAes(String data, String key, int mode) {
        try {
            if (data == null || "".equals(data)) {
                System.out.println("加密数据不能为空");
                return null;
            }

            if (key == null || "".equals(key)) {
                System.out.println("加密密钥不能为空");
                return null;
            }

            // 生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(messageDigest.digest(key.getBytes(CHARACTER_ENCODE)), KEY_AES);

            // 生成密码器
            // EBC:简单对各段数据的拼接
            // CBC:加密的各段数据是有联系的
            // CTR、CFB、OFB
            // PKCS7Padding(0-255)、 PKCS5Padding(8)、NoPadding     blockSize的长度不一样
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  //"算法/模式/补码方式"
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(mode, keySpec, iv);

            if (mode == Cipher.ENCRYPT_MODE){
                byte[] result = cipher.doFinal(data.getBytes(CHARACTER_ENCODE));
                return Base64Utils.encoderData(result);
            }else {
                final byte[] ret = Base64Utils.decoderData(data);
                byte[] result = cipher.doFinal(ret);
                return new String(result, CHARACTER_ENCODE);
            }

        }catch (Exception e){
            System.out.println((mode == Cipher.ENCRYPT_MODE ? "加密失败": "解密失败")+e.toString());
        }
        return null;
    }
}
