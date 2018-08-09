package cn.leslie.web;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    //所exthends的BaseServlet的子类中都需要定位到的这个方法
    protected abstract Class getServletClass();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             //获取用户的请求，获取前台页面的值（登录的方法的方法名）
        String methodName=req.getParameter("methodName");
        //根据用户传的值来确定是用那个Servlet哪个方法调用
        //实例化方法对象
        Method method=null;
        //执行返回结构
        Object result=null;
        if(method==null||"".equals(methodName)){
            recurrence(req,resp);
        }else{
            //也就是说拿到了请求，拿到请求需要的方法，但是不知道是哪个子Servlet的方法所以就要先走BaseServlet这个父类，
            // 利用反射点出来所需要的子的Servlet的方法
            try {
                //加载
                method=getServletClass().getDeclaredMethod(methodName,HttpServletResponse.class,HttpServletRequest.class);
                //执行方法
                try {

                   result=method.invoke(this,req,resp);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
         //当执行了结果以后得到result的结果
        //但是不知道结果是什么类型的
        //String 和jsion两个类型的转换
        toView(req,resp,result);
    }

    private void toView(HttpServletRequest req, HttpServletResponse resp, Object result) throws ServletException, IOException {
        if(result==null){
            System.out.println("====没有返回值=======");
        }else{
            //有返回值的时候又分两种情况json和string
            if(result instanceof  String ){
                //结果是String字符串
                String viewNmae=result.toString()+".jsp";
                req.getRequestDispatcher(viewNmae).forward(req,resp);

            }else{
                //json模式
                String resultJson=(String) JSON.toJSONString(result);
                 PrintWriter write=resp.getWriter();
                 write.write(resultJson);
                 write.flush();
                 write.close();
            }
        }
    }


    private Object recurrence(HttpServletRequest req, HttpServletResponse resp) {
        //返回主页面
           return "main";
    }
}
