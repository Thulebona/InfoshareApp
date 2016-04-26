package cput.ac.za.infoshare.AppConf.security;

import java.util.UUID;

/**
 * Created by hashcode on 2015/08/22.
 */
public class KeyGenerator {
    public static String getEntityId() {
        return hashKey(UUID.randomUUID().toString());
    }

    private static String hashKey(String value) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(value.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}
