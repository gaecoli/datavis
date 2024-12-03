package com.gaecoli.datavis.utils;


import org.mindrot.jbcrypt.BCrypt;

public class EncryptionUtils {
    private static final int BCRYPT_STRENGTH = 12;

    /**
     * 使用BCrypt加密密码
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_STRENGTH));
    }

    /**
     * 验证密码是否匹配
     * @param plainPassword 原始密码
     * @param hashedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
