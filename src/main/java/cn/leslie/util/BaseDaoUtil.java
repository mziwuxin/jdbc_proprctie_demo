package cn.leslie.util;

/**
 * 所以的业务以及增删改
 * 查询执行executeQuery查询
 * 增删改执行executeUpdate
 */

import java.sql.*;

/***
 * 1 增删改
 * 2 查询
 * 3 连接数据仓库
 * 4 释放资源
 */
public class BaseDaoUtil {
    /**
     * JDBC的需要的属性
     */
    protected Connection connection;
    protected PreparedStatement ps;
    protected ResultSet rs;
    /**
     * 连接数据库
     */
    public boolean getConcetion(){
        try {
            //加载驱动
            Class.forName(ConfigManager.GetValue("jdbc.driver"));
            try {
                connection= DriverManager.getConnection(ConfigManager.GetValue("jdbc.url"),
                        ConfigManager.GetValue("jdbc.username"),
                        ConfigManager.GetValue("jdbc.password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 释放资源
     *
     */
    public void closeConnection(){
        try {
            if (rs!=null){
                rs.close();  //释放结果集
            }
            if (ps!=null){
                ps.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /***
     * 增删改
     */
    public int executeUpdate(String sql,Object...params) {
        int num = 0;
        if (getConcetion()) {
            try {
                ps = connection.prepareStatement(sql);
                if (params != null) {
                    //有参数的判断
                    for (int i = 0; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);

                    }
                }
                num = ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection(); //释放资源
            }
        }
        return num;
    }
}
