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

@WebServlet("/login")
public class UserServlet extends BaseServlet{
    //不实例化service层对象  让工厂去实例化
    private UserService userService;
    private  ResultUtil util=new ResultUtil();
    @Override
    protected Class getServletClass() {
        return UserServlet.class;
    }
    public String login(HttpServletRequest req, HttpServletResponse resp){
        //拿到页面的登录信息
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        //get到扥到的用户名
      String  passwordDB =userService.validateName(userName);
      //判断如果数据库的用户名是否存在来判断是否执行以下代码
      if(passwordDB==null){
          try {
              //判断输入的密码和数据库的密文密码是否一样执行登录
              if(Md5Encrypt.validPassword(password,passwordDB)){
                  //拿到user的实例的值
                    User user=userService.login(userName,passwordDB);
                    req.getSession().setAttribute("loginUser",user);
                    return "main";


              }else {
                  System.out.println("密码错误");
              }

          } catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
          } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
          }


      }else{
          //错误
          util.resultFail("用户名不存在");
      }

        //userService.login(userName,password);

        return "login";

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

    /**
     * ajax对前台传过来的用户的验证
     * @param req
     * @param resp
     * @return
     */
    public ResultUtil validateName(HttpServletRequest req, HttpServletResponse resp){
        //System.out.println("=========================");
       String  userName=req.getParameter("username");
       //调用service的方法
       String passwordDB=userService.validateName(userName);
       if(passwordDB==null){
           //验证的用户名不存在也就是说没有这个用户
           //n那么就可以注册用户
           util.resultUtilsuccess();
       }else {
           util.resultFail("昵称已经被注册了亲！！！！");
       }
         return util;
    }

 }
