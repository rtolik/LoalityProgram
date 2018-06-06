package photopolis.loalityprogram.service.utils;


import photopolis.loalityprogram.config.Constants;
import photopolis.loalityprogram.model.enums.BonusType;

/**
 * Created by Anatoliy on 25.05.2018.
 */
public class Utility {

    public static String dataParser(String dataISO) {
        if(dataISO==null)
            return null;
        if (dataISO.equals(""))
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

    private static String addRegularBonus(String dateOfStart){
        if (dateOfStart!=null) {
            String[] date = dateOfStart.split("-");
            Integer year = Integer.parseInt(date[0]);
            Integer mounth = Integer.parseInt(date[1]);
            Integer day = Integer.parseInt(date[2]);
            mounth += Constants.REGULAR_BONUS_DURATION_MOUNTH;
            return getDateCorrect(year, mounth, day);
        }
        return "";
    }

    private static String addPartyBonus(String dateOfStart){
        if (dateOfStart!=null) {
            String[] date = dateOfStart.split("-");
            Integer year = Integer.parseInt(date[0]);
            Integer mounth = Integer.parseInt(date[1]);
            Integer day = Integer.parseInt(date[2]);
            day += Constants.PARTY_BONUS_DURATION_DAYS;
            return getDateCorrect(year, mounth, day);
        }
        return "";
    }

    private static String addBirthDayBonus(String dateOfStart){
        if (dateOfStart!=null) {
            String[] date = dateOfStart.split("-");
            Integer year = Integer.parseInt(date[0]);
            Integer mounth = Integer.parseInt(date[1]);
            Integer day = Integer.parseInt(date[2]);
            day += Constants.BIRTHDAY_BONUS_DURATION_DAYS;
            return getDateCorrect(year, mounth, day);
        }
        return "";
    }

    private static String addAnniversaryBonus(String dateOfStart){
        if (dateOfStart!=null) {
            String[] date = dateOfStart.split("-");
            Integer year = Integer.parseInt(date[0]);
            Integer mounth = Integer.parseInt(date[1]);
            Integer day = Integer.parseInt(date[2]);
            day += Constants.ANNIVERSARY_BONUS_DURATION_DAYS;
            return getDateCorrect(year, mounth, day);
        }
        return "";
    }

    private static String getDateCorrect(Integer year, Integer mounth, Integer day) {
        if (mounth>12){
            year++;
            mounth-=12;
        }
        if(mounth==2&&year%4==0&&day>29){
            day-=29;
            mounth++;
            return createResultDate(year,mounth,day);
        }
        if(mounth==2&&year%4!=0&&day>28){
            day-=28;
            mounth++;
            return createResultDate(year,mounth,day);
        }
        if((mounth==4||mounth==6||mounth==9||mounth==11)&&day>30){
            day-=30;
            mounth++;
            return createResultDate(year,mounth,day);
        }
        if(day>31){
            day-=31;
            mounth++;
            if(mounth>12) {
                mounth-=12;
                year++;
            }
            return createResultDate(year,mounth,day);
        }
        return createResultDate(year,mounth,day);
    }

    private static String createResultDate(Integer year,Integer mounth, Integer day){
        return year+"-"+(mounth<10?"0"+mounth:mounth)+"-"+(day<10?"0"+day:day);
    }

    public static Double timeToDoubleParser(String time){
        String [] tmp = time.split(":");
        Double result= new Double(tmp[0]);
        if (tmp[1].equals("30"))
            result+=0.5;
        else {
            result+=0.0;
        }
        return result;
    }

    public static String doubleTimeToStringParser(Double time){
        String [] res = time.toString().split("\\.");
        Integer h=Integer.parseInt(res[0]);
        Integer m=Integer.parseInt(res[1]);
        String hour=h.toString();
        String minute;
        if(h<10){
            hour="0"+hour;
        }
        if (m==5){
            minute="30";
        }
        else {
            minute="00";
        }
        return hour+":"+minute;
    }

    public  static  Double countDuration(String timeOfStart, String timeOfEnd){
        return timeToDoubleParser(timeOfEnd)-timeToDoubleParser(timeOfStart);
    }

    public static String datePluser(String dateOfStart, BonusType bonusType){
        String result;
        switch (bonusType){
            case PARTY:{
                result=addPartyBonus(dateOfStart);
                break;
            }

            case REGULAR: {
                result = addRegularBonus(dateOfStart);
                break;
            }

            case BIRTHDAY:{
                result = addBirthDayBonus(dateOfStart);
                break;
            }

            case ANNIVERSARY:{
                result = addAnniversaryBonus(dateOfStart);
                break;
            }
            default:{
                result="IDI NAHUJ";
            }
        }
        return result;
    }

    public static Boolean dataComparer(String leftDate, String rightDate){
        String [] leftSplit=leftDate.split("-");
        String [] rightSplit=rightDate.split("-");
        Integer leftYear=Integer.parseInt(leftSplit[0]);
        Integer rightYear=Integer.parseInt(rightSplit[0]);
        if(leftYear>rightYear) {
            return true;
        }
        Integer leftMonth=Integer.parseInt(leftSplit[1]);
        Integer rightMonth=Integer.parseInt(rightSplit[1]);
        if (leftMonth>rightMonth){
            return true;
        }
        Integer leftDay=Integer.parseInt(leftSplit[2]);
        Integer rightDay=Integer.parseInt(rightSplit[2]);
        if (leftDay>=rightDay) {
            return true;
        }
        return false;
    }

    public static Boolean dataEqualiser(String left, String right){
        String [] leftSplit=left.split("-");
        String [] rightSplit=right.split("-");
        Integer leftMonth=Integer.parseInt(leftSplit[1]);
        Integer rightMonth=Integer.parseInt(rightSplit[1]);
        if (leftMonth==rightMonth){
            return true;
        }
        Integer leftDay=Integer.parseInt(leftSplit[2]);
        Integer rightDay=Integer.parseInt(rightSplit[2]);
        if (leftDay==rightDay) {
            return true;
        }
        return false;
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
