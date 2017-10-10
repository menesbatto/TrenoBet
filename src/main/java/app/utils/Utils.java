package app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Utils {

	public static boolean isMatchNotTooFar(Date matchDate, Integer day) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, day);
		Date expirationDate = c.getTime();
		if (matchDate.before(expirationDate))
			return true;
		return false;
		
	}
	
	public static boolean isMatchInTemporalRange(Date matchDate, int daysFarBetFrom, int daysFarBetTo) {
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, daysFarBetFrom);
		Date startingDate = c.getTime();
		
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, daysFarBetTo);
		Date expirationDate = c.getTime();
		
		
		
		if (matchDate.after(startingDate) && matchDate.before(expirationDate))
			return true;
		return false;
	}
	
	
	public static String redimString(Double val) {
		if (val == null){
			return null;
		}
		if (val.toString().length()>5)
			return val.toString().substring(0,5);
		return redimString(val.toString(), 4);
	}


	public static String redimString(String string, Integer length) {
		if (string == null) {
			return "";
		}
		String returnString =  string;
		for (int i = string.length(); i< length; i++){
			returnString += " ";
		}
		return returnString;
	}


	public static Date convertDateString(String dateString) {
		DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
		Date startDate = null;
		try {
			startDate = df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}
	public static Date getDateFromStringDate(String dateString) {
		String[] split = dateString.split(" ");
		String dayString = split[1].substring(0, split[1].length()-2);
		String monthString = split[2];
		Integer monthInt = null;
		MonthEnum monthName = MonthEnum.valueOf(monthString); 
		switch (monthName) {
		case January:	monthInt = 0;	break;
		case February:	monthInt = 1;	break;
		case March:		monthInt = 2;	break;
		case April:		monthInt = 3;	break;
		case May:		monthInt = 4;	break;
		case June:		monthInt = 5;	break;
		case July:		monthInt = 6;	break;
		case August:	monthInt = 7;	break;
		case September:	monthInt = 8;	break;
		case October:	monthInt = 9;	break;
		case November:	monthInt = 10;	break;
		case December:	monthInt = 11;	break;
		default:		break;
		}
		String yearString = split[3];
		Calendar c = Calendar.getInstance();
		
		
		c.set(Integer.valueOf(yearString), monthInt, Integer.valueOf(dayString));  
		Date date = c.getTime();
//		System.out.println(dateString);
		return date;
	}
	public static String redimString(Double goodnessW, int i) {
		return redimString(goodnessW+"", i);
	}
	
	public static String forceLength(Double goodnessW, int i) {
		String result = goodnessW + "   ";
		result = result.substring(0,i);		
		return result;
	}
	
	public static String cleanString(String input) {
		String output = input.replaceAll("[^A-Za-z0-9]", "");
		return output;
	}
}
