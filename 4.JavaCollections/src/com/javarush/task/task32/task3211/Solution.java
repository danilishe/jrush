package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test strfgdff gsdfgdfg sdfgj34534 asdf выа фыва фывм фы43 й45ing"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(byteArrayOutputStream.toByteArray());
        BigInteger res = new BigInteger(1, md.digest());
        byte mdbytes[] = md.digest(byteArrayOutputStream.toByteArray());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mdbytes.length; i++) {
            String s = Integer.toHexString(0x00ff & mdbytes[i]);
            sb.append(s);
        }
        System.out.println(sb.toString());
        return sb.toString().equals(md5);
    }
}
