package com.oasis.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

public class MD5Test {
    @Test
    public void testMD5(){
        Md5Hash  pwd = new Md5Hash("123","aaa");
        System.out.println(pwd);
    }
}
