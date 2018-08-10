package cn.leslie.dao.user;

import cn.leslie.dao.IBaseDao;
import cn.leslie.entity.User;

public interface UserDao extends IBaseDao<User> {
    /**
     * 只需要写自己特有的方法就可以了 比如用户的登录功能
     */
          String validateName(String userName);
/**
 *   登录验证
 */
    User login(String userName, String password);
}
