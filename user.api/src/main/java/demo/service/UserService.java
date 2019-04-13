package demo.service;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 根据用户ID获取用户名称
     * @param userId
     * @return
     */
    String getUserNameById(Integer userId);
}
