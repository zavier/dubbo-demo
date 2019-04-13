package demo.provider;

import demo.service.UserService;

public class UserServiceImpl implements UserService {

    /**
     * 通过用户ID查找用户名称
     * @param userId
     * @return
     */
    @Override
    public String getUserNameById(Integer userId) {
        System.out.println("userService getUserNameById param:" + userId);
        return "userName: " + userId;
    }
}
