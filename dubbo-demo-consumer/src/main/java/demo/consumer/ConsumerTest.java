package demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import demo.service.DemoService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerTest {
    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    DemoService demoService = null;

    // 1K字符串
    String body1K = "1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore1234567890qwertyuioplkjhgfdsazxcvbnjdkfdweruweiore";

    String body50K = "";

    {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            stringBuilder.append(body1K);
        }
        body50K = stringBuilder.toString();
    }

    @Before
    public void setUp() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-consumer");

        // 连接注册中心配置
//        RegistryConfig registry = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setUrl("dubbo://127.0.0.1:20881/demo.service.DemoService");
        reference.setApplication(application);
//        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
        reference.setVersion("1.0.0");

        // 和本地bean一样使用xxxService
        demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

        // 先调用1000次，这些调用不进行统计
        for (int i = 0; i < 1000; i++) {
            demoService.sayHello("hello");
        }

    }

    @Test
    public void SimpleSequelInvoke() {
        List<IntSummaryStatistics> statisticsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Long> costTimeList = new ArrayList<>();
            for (int j = 0; j < 10000; j++) {
                final long st = System.nanoTime();
                demoService.sayHello(body50K);
                final long et = System.nanoTime();
                costTimeList.add(et - st);
            }
            final IntSummaryStatistics statistics = costTimeList.stream()
                    .mapToInt(Long::intValue)
                    .summaryStatistics();
            statisticsList.add(statistics);
        }
        System.out.println("==================================");
        System.out.println("Count\tAvg\tMax\tMin");
        for (IntSummaryStatistics statistics : statisticsList) {
            System.out.print(statistics.getCount() + "\t");
            System.out.print(statistics.getAverage() + "\t");
            System.out.print(statistics.getMax() + "\t");
            System.out.print(statistics.getMin() + "\n");
        }
        System.out.println("==================================");
    }

    @Test
    public void testThreadPoolInvoke() {
        for (int i = 0; i < 100; i++) {
            final CallTask callTask = new CallTask();
            executorService.submit(callTask);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end-");
    }

    class CallTask implements Runnable {
        @Override
        public void run() {
            List<Long> costTimeList = new ArrayList<>();
            for (int j = 0; j < 10000; j++) {
                final long st = System.nanoTime();
                demoService.sayHello(body50K);
                final long et = System.nanoTime();
                costTimeList.add(et - st);
            }
            countDownLatch.countDown();
            final IntSummaryStatistics statistics = costTimeList.stream()
                    .mapToInt(Long::intValue)
                    .summaryStatistics();
            System.out.println(statistics);
        }
    }
}
