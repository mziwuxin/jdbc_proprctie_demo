package cn.leslie.service.user;

import cn.leslie.entity.User;
import cn.leslie.service.IUserService;

/**
 * UserService接口继承了User类的Service就可以实现所有的共有方法并且可以
 * 写自己的方法
 */
public interface UserService extends IUserService<User> {
/**
 *
 */
}
