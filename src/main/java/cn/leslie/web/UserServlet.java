package cn.leslie.web;

import cn.leslie.util.ResultUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

@WebServlet("/login")
public class UserServlet extends BaseServlet{
    @Override
    protected Class getServletClass() {
        return UserServlet.class;
    }
    public ResultUtil login(HttpServletRequest req, HttpServletResponse resp){
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
}
