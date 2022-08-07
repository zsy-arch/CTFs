package com.cdlinglu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* loaded from: classes.dex */
public class MD5 {
    public static final String ENCODE = "UTF-8";

    public static String md5Encode(String inStr) throws Exception {
        try {
            byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(inStr.getBytes("UTF-8"));
            StringBuffer hexValue = new StringBuffer();
            for (byte b : md5Bytes) {
                int val = b & 255;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
    }

    public static String hmacSign(String aValue, String aKey) {
        byte[] keyb;
        byte[] value;
        byte[] k_ipad = new byte[64];
        byte[] k_opad = new byte[64];
        try {
            keyb = aKey.getBytes("UTF-8");
            value = aValue.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }
        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 54);
            k_opad[i] = (byte) (keyb[i] ^ 92);
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(k_ipad);
            md.update(value);
            byte[] dg = md.digest();
            md.reset();
            md.update(k_opad);
            md.update(dg, 0, 16);
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String digest(String aValue) {
        byte[] value;
        String aValue2 = aValue.trim();
        try {
            value = aValue2.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            value = aValue2.getBytes();
        }
        try {
            return toHex(MessageDigest.getInstance("SHA").digest(value));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String toHex(byte[] input) {
        if (input == null) {
            return null;
        }
        StringBuffer output = new StringBuffer(input.length * 2);
        for (byte b : input) {
            int current = b & 255;
            if (current < 16) {
                output.append("0");
            }
            output.append(Integer.toString(current, 16));
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println("inputStr::" + hmacSign("加密前原码", "12345678"));
    }
}
