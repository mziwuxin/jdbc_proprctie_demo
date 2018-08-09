package cn.leslie.service;

import cn.leslie.service.user.UserServiceImpl;

public class ServiceFactory {
    /**
     *
     * 能够使用工厂的时候就是在实例化很多对象的时候
     * 并且这个对象呢有相同的父类或者实现相同的接口
     */
    /***
     * 工厂就使用一次实例嘛  所以也符合单例模式
     */
    //创建本类的静态对象
    private static  ServiceFactory factory;

    //私有化构造
    private ServiceFactory(){}
     //创建静态代码块
    static {
        if (factory==null){
            synchronized (ServiceFactory.class){
                if (factory==null){
                    factory=new ServiceFactory();
                }
            }
        }
    }
           //相当于创建立一个大的车站一样

    /**
     * 每次进来的的Service都不一样
     * 进行选择的时候符合那个Service就通过switch选择哪一个创建哪一个Service的实例
     * @param serviceName
     * @return
     */
    public static IUserService getServiceImpl(String serviceName) {
        IUserService  service=null;
        switch (serviceName){
            case "userService":
                service=new UserServiceImpl();
            default:
                break;
        }
        return  service;
    }
}
