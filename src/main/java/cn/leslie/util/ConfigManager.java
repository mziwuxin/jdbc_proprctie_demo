package cn.leslie.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
* 因为什么功能都需要连接数据库所以可以写一个工具类，有一个对外的接口可以使用调用就可以了
* 所以写一个单例出来每次调用就可以了
*
* 只要有需求说对象只出现一次实例化一次
* 就是单例模式
* */
public class ConfigManager {
    //01创建一个单例的变量
    private static ConfigManager configManager;
    //创建jdbc的文件对象
    private  static Properties properties;
    //私有化构造
    /*private  ConfigManager(){
        String path="jdbc.properties";
        //自身加载解析properties文件
        properties=new Properties();
       InputStream inputStream=ConfigManager.class.getClassLoader().getResourceAsStream(path);
        try {
            //加载
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭流
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
    /*
    * 利用静态代码块优先级更高 只要加载就会加载
    *
    *
    * */
    static {
        String path="jdbc.properties";
        //自身加载解析properties文件
        properties=new Properties();
        InputStream inputStream=ConfigManager.class.getClassLoader().getResourceAsStream(path);
        try {
            //加载
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭流
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized   ConfigManager getInstance(){
          return  configManager;
    }
    /*
    * 加载到内存之后就可以操作properties文件的四要素
    * 可以通过Key获取Value了
    * */
    public static String GetValue(String key){
        return properties.getProperty(key);
    }
}
