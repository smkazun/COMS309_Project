package com.example.bansho.testingcard;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Event {

//    private Time start;
//    private Time end;
//    private String name;
//    private String content;
//
//    public Event(String startDate, String endDate, String name, String content)
//    {
//        start = new Time(startDate);
//        end = new Time(endDate);
//
//        this.content = content;
//        this.admin = admin;

//        for(int i = 0 ; i < toAdd.length ; i += 1)
//        {
//            users.add(toAdd[i]);
//        }
//    }
//
//    public Time getStartDate()
//    {
//        return start;
//    }
//
//    public Time getEndDate()
//    {
//        return end;
//    }
//
//    public String toString()
//    {
//        return name;
//    }
//
//    public class Time
//    {
//        private int day;
//        private int month;
//        private int year;
//
//        private int hour;
//        private int minute;
//
//        public Time(String time)
//        {
//            Scanner s = new Scanner(time);
//
//            try
//            {
//                month = s.nextInt();
//                day = s.nextInt();
//                year = s.nextInt();
//
//                hour = s.nextInt();
//                minute = s.nextInt();
//            }
//            catch (NoSuchElementException e)
//            {
//                System.out.println("Catched String mismatched in creating " + time + ", exit..");
//                System.exit(0);
//            }
//            finally
//            {
//                s.close();
//            }
//        }
//
//        public void getTime(int hour, int minute)
//        {
//            hour = this.hour;
//            minute = this.minute;
//        }
//
//        public void setTime(int hour, int minute)
//        {
//            this.hour = hour;
//            this.minute = minute;
//        }
//
//        public void getDate(int month, int day, int year)
//        {
//            month = this.month;
//            day = this.day;
//            year = this.year;
//        }
//
//        public void setDate(int month, int day, int year)
//        {
//            this.month = month;
//            this.day = day;
//            this.year = year;
//        }
//    }
    private String item_title,item_desc,item_date, item_time;
    private int item_image;

    public Event(String item_title, String item_desc, String item_date) {
        this.item_title = item_title;
        this.item_desc = item_desc;
        this.item_date = item_date;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public  String getItem_Time(String item_time){
        return item_time;
    }

    public  void  setItem_time(String item_time){
        this.item_time = item_time;
    }
    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {

        this.item_image = item_image;
    }

}
