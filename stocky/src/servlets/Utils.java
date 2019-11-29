package servlets;

import java.util.*;

public class Utils {

    /**
     * Default constructor. 
     */
    public Utils() {
        // TODO Auto-generated constructor stub
    }
    
    public static String getGreetings() {
    	Calendar c = Calendar.getInstance();
    	int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

    	if(timeOfDay >= 0 && timeOfDay < 12){
    	    return "Good Morning";      
    	}else if(timeOfDay >= 12 && timeOfDay < 17){
    	    return "Good Afternoon";
    	}else {
    		return "Good Evening";
    	}
    }

    public static boolean isMarketOpen() {
    	Calendar c = Calendar.getInstance(TimeZone.getTimeZone("CDT"));
    	c.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
    	int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    	int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
    	System.out.println(dayOfWeek);
    	System.out.println(timeOfDay);

    	if(timeOfDay >= 8 && timeOfDay <= 16 && dayOfWeek >= 2 && dayOfWeek <= 6){
    	    return true;      
    	}else {
    		return false;
    	}
    }

}
