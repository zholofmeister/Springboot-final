package com.zzy.homework;

import com.zzy.homework.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
//        SpringApplication.run(HomeworkApplication.class, args);
        ApplicationContext applicationContext =
                SpringApplication.run(HomeworkApplication.class, args);
        SpringUtil.setApplicationContext(applicationContext);

    }

}
