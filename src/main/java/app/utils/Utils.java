package app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.test.context.transaction.BeforeTransaction;


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
	
	public static boolean isMatchInTemporalRange(Date matchDate, Date betDate, int daysFarBetFrom, int daysFarBetTo) {
	Calendar c = Calendar.getInstance();
		
		c.setTime(betDate);
		c.add(Calendar.DAY_OF_YEAR, daysFarBetFrom);
		Date startingDate = c.getTime();
		
		c.setTime(betDate);
		c.add(Calendar.DAY_OF_YEAR, daysFarBetTo);
		Date expirationDate = c.getTime();
		
		
		
		if (matchDate.after(startingDate) && matchDate.before(expirationDate))
			return true;
		return false;
	}
	
	
//	public static boolean isMatchInTemporalRange(Date matchDate, int daysFarBetFrom, int daysFarBetTo) {
//		
//		Calendar c = Calendar.getInstance();
//		
//		c.setTime(new Date());
//		c.add(Calendar.DAY_OF_YEAR, daysFarBetFrom);
//		Date startingDate = c.getTime();
//		
//		c.setTime(new Date());
//		c.add(Calendar.DAY_OF_YEAR, daysFarBetTo);
//		Date expirationDate = c.getTime();
//		
//		
//		
//		if (matchDate.after(startingDate) && matchDate.before(expirationDate))
//			return true;
//		return false;
//	}
	
	
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
	
	public static Date getDateOfBet(int weekToAdd) {
		Calendar c = getCalendarStartDate();
//		Date initialDate = c.getTime();
		c.add(Calendar.WEEK_OF_YEAR, weekToAdd);
		Date dateOfWeek = c.getTime();
		return dateOfWeek;
	}
	
	public static int getActualTrenoSeasonDay() {
		Date now = new Date();
		Calendar calendarStartDate = getCalendarStartDate();
		Date startDate = calendarStartDate.getTime();
		
		long weeks = (now.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24 * 7);
		//long between = ChronoUnit.WEEKS.between(startDate.toInstant(), now.toInstant());
		
		return (int)weeks;
	}
	
	
	public static Calendar getCalendarStartDate() {
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.DATE, 10);			//10 giovedi
		c.set(Calendar.MONTH, 7);			//agosto
		c.set(Calendar.YEAR, 2017);			//2017
		c.set(Calendar.HOUR_OF_DAY, 0);		//0
		c.set(Calendar.MINUTE, 0);			//0
		c.set(Calendar.SECOND, 0);			//0
		return c;
	}
	
	
	
	public static Date getDateAfter7Days(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +7);
		Date dateAfter7Days = cal.getTime();
		
		return dateAfter7Days;
		
	}
	
	public static Date getLimitDayOfCurrentSeasonDay() {
		
		int actualTrenoSeasonDay = getActualTrenoSeasonDay();
		Calendar cal = getCalendarStartDate();
		cal.add(Calendar.DATE, (actualTrenoSeasonDay + 1 ) * 7);
		Date limitDayOfSeasonDayByDate = cal.getTime();
		
		return limitDayOfSeasonDayByDate;
		
	}



	
	
	
}
