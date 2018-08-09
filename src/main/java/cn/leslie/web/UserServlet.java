package cn.leslie.web;

import cn.leslie.entity.User;
import cn.leslie.service.user.UserService;
import cn.leslie.util.Md5Encrypt;
import cn.leslie.util.ResultUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

@WebServlet("/login")
public class UserServlet extends BaseServlet{
    //不实例化service层对象  让工厂去实例化
    private UserService userService;
    @Override
    protected Class getServletClass() {
        return UserServlet.class;
    }
    public ResultUtil login(HttpServletRequest req, HttpServletResponse resp){
        //拿到页面的登录信息
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        ResultUtil util=new ResultUtil();
        if(userName.equals("admin")){
            util.resultUtilsuccess(userName);
        }else {
            util.resultFail("错误了");
        }
            return util;
    }

    /**
     * 注册
     */
    public String register(HttpServletRequest req, HttpServletResponse resp){
//获取用户输入的参数
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        //创建出User类的实体来
        User user=new User();
        user.setUsername(userName);
        try {
            //调用MD5接口给密码加密
            user.setPassword(Md5Encrypt.getEncryptedPwd(password));
            System.out.println(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setType(0);  //设置用户类型
        int num= userService.add(user);
        if (num>0){
            return  "main";
        }else{
            return "register";
        }

    }
 }
