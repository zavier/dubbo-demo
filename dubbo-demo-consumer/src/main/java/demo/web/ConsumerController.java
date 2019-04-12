package demo.web;

import demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private IDemoService demoService;

    @GetMapping(value = "sayHello")
    public String consume(@RequestParam(defaultValue = "test")String name) {
        String result = demoService.sayHello(name);
        return result;
    }
}
