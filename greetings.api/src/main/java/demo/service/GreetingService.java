package demo.service;

/**
 * 问候服务
 */
public interface GreetingService {

    /**
     * 根据用户ID问候，需要查询到用户名称
     * @param userId
     * @return
     */
    String sayHello(Integer userId);

    /**
     * 根据用户名字问候
     * @param name
     * @return
     */
    String sayHello(String name);
}
