package cput.ac.za.infoshare.AppConf.Util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 2016/04/19.
 */
public class AppUtil {
    public static  Date getDate(String date) {
        DateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
        Date date_temp=null;
        try {
            date_temp =  formatter.parse(date);
        } catch (ParseException ex) {
                ex.printStackTrace();
        }

        return date_temp;
    }
}
