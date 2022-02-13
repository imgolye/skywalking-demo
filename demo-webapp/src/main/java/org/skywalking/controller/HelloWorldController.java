package org.skywalking.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.skywalking.HelloService;
import org.skywalking.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Gol
 */
@RestController
@RequestMapping
public class HelloWorldController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    // 启动一个单线程的线程池
    private ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Reference
    private HelloService helloService;

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello/{words}")
    public String hello(@PathVariable("words") String words)throws Exception{
        Thread.sleep(1000);
        System.out.println("traceId:" + TraceContext.traceId());
        ActiveSpan.tag("hello-trace activation", words);
        // Dubbo 方式调用demo-provider
        String result = helloService.say(words);
        // demoService.traceMethod();
        executorService.submit(
                // 使用RunnableWrapper对Runnable进行包装，实现Trace跨线程传播
                RunnableWrapper.of(() -> {
                    try {
                        demoService.traceMethod();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
        LOGGER.info("this is an info log,{}", words);
        Thread.sleep(1000);
        return result;
    }

    @GetMapping("/err")
    public String err() {
        String traceId = TraceContext.traceId();
        System.out.println("traceId:" + TraceContext.traceId());
        ActiveSpan.tag("error-trace activation", "error");
        throw new RuntimeException("err");
    }

}
