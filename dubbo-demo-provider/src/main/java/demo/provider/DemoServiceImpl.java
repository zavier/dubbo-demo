package demo.provider;

import demo.service.IDemoService;

public class DemoServiceImpl implements IDemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
