package com.thecms.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
public class ArticleTest {

    @Test
    public void TestTypeMatch(){

    }

    @Test
    public void login(){

        String pwd = "test";
        String s =  DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        System.out.println(s);

    }

}
