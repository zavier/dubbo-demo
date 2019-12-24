package demo.provider;

import demo.entity.Person;
import demo.service.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return name;
    }

    public Person sayPerson(Person person) {
        System.out.println(person.getName());
        Person person1 = new Person();
        person1.setName("this is response");
        return person1;
    }
}
