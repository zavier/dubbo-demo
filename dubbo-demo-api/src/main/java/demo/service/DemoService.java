package demo.service;

import demo.entity.Person;

public interface DemoService {
    String sayHello(String name);

    Person sayPerson(Person person);
}
