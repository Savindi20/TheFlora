package lk.ijse.TheFlora.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static String dateNow(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
