package cn.leslie.dao.user.impl;

import cn.leslie.dao.user.UserDao;
import cn.leslie.entity.User;
import cn.leslie.util.BaseDaoUtil;
import cn.leslie.util.PageUtil;
import cn.leslie.util.ResultSetUtil;

import java.io.Serializable;
import java.util.List;

public class UserImpl extends BaseDaoUtil implements UserDao {
    /***
     *
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        String sql="insert into news_user(username,password,email) values(?,?,?,?)";
        Object[] params={user.getUsername(),user.getPassword(),user.getEmail(),user.getEmail()};

        return executeUpdate(sql,params);
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

    @Override
    public String validateName(String userName) {
        String sql = "SELECT password FROM news_user WHERE userName=?";
        rs =executeQuery(sql);
        String password = null;
        try { //获取密码
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public User login(String userName, String password) {
        String sql="SELECT id as users_id,userName,PASSWORD,email,userType FROM news_user where userName=? and password=?";
        Object [] params={userName,password};
        rs=executeQuery(sql,params);
        User users= ResultSetUtil.eachOne(rs,User.class);
        return users;
    }
}
