package demo.web;

import demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("greeting/name")
    public String greeting(String name) {
        System.out.println("client greeting name:" + name);
        return greetingService.sayHello(name);
    }

    @GetMapping("greeting/uid")
    public String greeting(Integer id) {
        System.out.println("client greeting id:" + id);
        return greetingService.sayHello(id);
    }
}
