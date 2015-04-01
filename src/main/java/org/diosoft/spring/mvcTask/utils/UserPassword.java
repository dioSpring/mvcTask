package org.diosoft.spring.mvcTask.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yar on 29.03.15.
 */
public class UserPassword {
    public static String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return String.valueOf(md.digest(password.getBytes()));
    }
}
