package org.skywalking.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.skywalking.HelloService;
import org.springframework.stereotype.Component;

/**
 * @Author: Gol
 */
@Service
@Component
public class DefaultHelloService implements HelloService {
    @Override
    public String say(String name) throws Exception {
        Thread.sleep(2000);
        return "hello"+name;
    }
}
