package demo.provider;

import demo.entity.Person;
import demo.service.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return name;
    }

    public Person sayPerson(Person person) {
        return person;
    }
}
