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
    private String item_name,item_place,item_price;
    private int item_image;

    public Event(String item_name, String item_place, String item_price) {
        this.item_name = item_name;
        this.item_place = item_place;
        this.item_price = item_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_place() {
        return item_place;
    }

    public void setItem_place(String item_place) {
        this.item_place = item_place;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}
