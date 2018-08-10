package cn.leslie.util;

;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtil {
    public static <T> T eachOne(ResultSet rs, Class<T> userClass){
        T object=null;
        try {
            if(rs.next()){
                try {
                    object=userClass.newInstance();
                    Field[] fields=userClass.getDeclaredFields();
                    for (Field f:fields
                         ) {
                        f.setAccessible(true);
                        f.set(object,rs.getObject(f.getName()));


                    }

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





}
