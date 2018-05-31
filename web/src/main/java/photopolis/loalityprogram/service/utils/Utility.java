package photopolis.loalityprogram.service.utils;


import io.swagger.models.auth.In;

/**
 * Created by Anatoliy on 25.05.2018.
 */
public class Utility {

    public static String dataParser(String dataISO) {
        if(dataISO.equals(null)||dataISO.equals(""))
            return null;
        String retDate = "";
        for (int i = 0; i < 10; i++) {
            retDate += dataISO.charAt(i);
        }
        return retDate;
    }

    public static String timeParser(String timeISO) {
        String retTime="";
        for(int i = 13; i < 18; i++){
            retTime+=timeISO.charAt(i);
        }
        return retTime;
    }

    public static Double timeToDoubleParser(String time){
        String [] tmp=time.split(":");
        Double result= new Double(tmp[0]);
        if (tmp[1].equals("30"))
            result+=0.5;
        return result;
    }

    public static String doubleTimeToStringParser(Double time){
        return time.toString();
    }

    public  static  Double countDuration(String timeOfStart, String timeOfEnd){
        return timeToDoubleParser(timeOfEnd)-timeToDoubleParser(timeOfStart);
    }

//    public static String outTimeParser(String time){
//        String retTime=time+=":";
//        String tmp=time.substring(3,5);
//        if (tmp.equals("00"))
//            retTime+="1";
//        else
//            retTime+="2";
//        return  retTime;
//    }

}
