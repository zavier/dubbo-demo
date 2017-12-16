package demo.provider;

import demo.service.IDemoService;

public class DemoServiceImpl implements IDemoService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
