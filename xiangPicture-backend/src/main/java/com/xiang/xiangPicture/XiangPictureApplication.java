package com.xiang.xiangPicture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.xiang.xiangPicture.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class XiangPictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangPictureApplication.class, args);
    }

}

