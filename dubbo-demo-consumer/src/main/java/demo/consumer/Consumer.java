package demo.consumer;

import demo.service.IDemoService;
import java.util.concurrent.TimeUnit;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "dubbo-demo-consumer.xml");
        context.start();
        IDemoService demoService = context.getBean("demoService", IDemoService.class);
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            String msg = demoService.sayHello("World");
            System.out.println(msg);
        }
    }
}
