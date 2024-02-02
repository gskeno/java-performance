package com.gskeno.tomcathighcpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    //创建线程池，其中有4096个线程。
    private ExecutorService executor = Executors.newFixedThreadPool(4096);
    //全局变量，访问它需要加锁。
    private int count;

    //以固定的速率向线程池中加入任务
    @Scheduled(fixedRate = 10)
    public void lockContention() {
        IntStream.range(0, 1000000)
                .forEach(i -> executor.submit(this::incrementSync));
        int size = ((ThreadPoolExecutor) executor).getQueue().size();
        System.out.println("队列大小" + size);
    }

    //具体任务，就是将count数加一
    private synchronized void incrementSync() {
        count = (count + 1) % 10000000;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

}
