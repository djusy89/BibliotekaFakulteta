package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {

public static final String FORMAT_DATUMA = "dd.MM.yyyy";
public static final String FORMAT_GODINA = "yyyy";

	
	public static String convertDate2String(Date ulDate){
		String dateStr = null;
		
		SimpleDateFormat formatDatuma = new SimpleDateFormat (FORMAT_DATUMA); // sablon
		dateStr = formatDatuma.format(ulDate);
		
		return dateStr;		
	}
	
	public static Date convertString2Date(String ulDatetr){
		Date date = null;
		SimpleDateFormat formatDatuma = new SimpleDateFormat (FORMAT_DATUMA);
		
		try {
			date = formatDatuma.parse(ulDatetr);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String year(Date year){
		String yearStr = null;
		SimpleDateFormat formatDatuma = new SimpleDateFormat (FORMAT_GODINA);
		yearStr = formatDatuma.format(year);
		
		return yearStr;
	}
	
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static String addYear(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, 1);
        Date newDate = cal.getTime();
        SimpleDateFormat formatDatuma = new SimpleDateFormat (FORMAT_DATUMA);
        String godina = formatDatuma.format(newDate);
        
        return godina;
    }
}
