package demo.provider;

import demo.service.GreetingService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private UserService userService;

    @Override
    public String sayHello(Integer userId) {
        System.out.println("greetingService sayHello userId:" + userId);
        String userName = userService.getUserNameById(userId);
        return "~ Hello " + userName;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("greetingService sayHello name:" + name);
        return "Hello " + name;
    }
}
