package mariana.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by mariana on 15.08.2016.
 */
public class DateUtils {
    private static final DateTimeFormatter formatterForDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final  SimpleDateFormat formatterForString = new SimpleDateFormat("dd/MM/yyyy");

    public static LocalDate toLocalDate(String stringDate){
        return LocalDate.parse(stringDate, formatterForDate);
    }

    public static String dateToString(LocalDate localDate) throws Exception{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
       return formatterForString.format(date);
    }
}
