package com.example.bansho.test;
import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DataManager {

    Calendar mCalendar;

    public DataManager(){
        mCalendar = Calendar.getInstance();
    }

    //get current month information
    public List<Date> getDays(){
        //save current information
        Date startDate = mCalendar.getTime();

        //count grid number
        int count = getWeeks() * 7 ;

        //calculate the days was show in the previous month
        mCalendar.set(Calendar.DATE, 1);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        mCalendar.add(Calendar.DATE, -dayOfWeek);

        List<Date> days = new ArrayList<>();

        for (int i = 0; i < count; i ++){
            days.add(mCalendar.getTime());
            mCalendar.add(Calendar.DATE, 1);
        }

        //reset
        mCalendar.setTime(startDate);

        return days;
    }

    //check current month
    public boolean isCurrentMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.US);
        String currentMonth = format.format(mCalendar.getTime());
        if (currentMonth.equals(format.format(date))){
            return true;
        }else {
            return false;
        }
    }

    //get week of number
    public int getWeeks(){
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    //get week
    public int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //go to next month
    public void nextMonth(){
        mCalendar.add(Calendar.MONTH, 1);
    }

    //go to previous month
    public void prevMonth(){
        mCalendar.add(Calendar.MONTH, -1);
    }


    public boolean isToday(Date data){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.US);
        String today = format.format(mCalendar.getTime());
        if (today.equals(format.format(data))) {
            return true;
        }else {
            return false;
        }

    }

}