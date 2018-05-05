package com.xue.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by miller on 2018/5/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test1(){
        String name = "xuehui";
        String password = "123456";
        log.info("name: {} , password: {}",name,password);
        log.info("info........");
        log.debug("debug.........");
        log.error("error..........");
    }
}
