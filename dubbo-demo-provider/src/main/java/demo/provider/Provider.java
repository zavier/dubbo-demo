package demo.provider;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext content = new ClassPathXmlApplicationContext(
            "dubbo-demo-provider.xml");
        content.start();
        // press any key to exit
        System.in.read();
    }
}
