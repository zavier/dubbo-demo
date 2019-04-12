package demo.provider;

import demo.service.IDemoService;

public class DemoServiceImpl implements IDemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("called sayHello, name:" + name);
        return "Hello " + name;
    }
}
