package cn.leslie.service.user;

import cn.leslie.dao.user.UserDao;
import cn.leslie.dao.user.impl.UserImpl;
import cn.leslie.entity.User;
import cn.leslie.util.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     *
     * @param user
     *
     * UserService的实现类，可以实现userService的所有的用到的方法
     * @return
     */
        private UserDao userDao=new UserImpl();
    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int delete(Serializable t) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public User findByCondition(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public int fingRownum() {
        return 0;
    }

    @Override
    public List<User> findAllByPage(PageUtil util) {
        return null;
    }
}
