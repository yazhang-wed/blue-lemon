package org.lemon.lemoncommon.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Java 自带的 Cipher 来实现对称加密工具类
 *
 * @author LBK
 * @create 2021-12-05 12:42
 */
public final class AESUtils {

    private AESUtils() {
    }

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String AES = "AES";

    /**
     * 获取 cipher
     *
     * @param key   密钥字节，用于创建一个对称密钥
     * @param model 密码操作模式
     * @return
     * @throws Exception
     */
    private static Cipher getCipher(byte[] key, int model) throws Exception {
        // 创建一个对称密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        return cipher;
    }

    /**
     * AES加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }

    /**
     * AES解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(Base64.getDecoder().decode(data));
    }
}
