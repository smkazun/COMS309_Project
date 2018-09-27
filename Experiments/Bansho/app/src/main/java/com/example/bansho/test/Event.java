package com.example.bansho.test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Event
{
    private Time start;
    private Time end;
    private String name;
    private String content;

    private User admin;
    private ArrayList<User> users;

    public Event(String startDate, String endDate, String name, String content, User admin, User[] toAdd)
    {
        start = new Time(startDate);
        end = new Time(endDate);

        this.content = content;
        this.admin = admin;

        for(int i = 0 ; i < toAdd.length ; i += 1)
        {
            users.add(toAdd[i]);
        }
    }

    public Time getStartDate()
    {
        return start;
    }

    public Time getEndDate()
    {
        return end;
    }

    public String toString()
    {
        return name;
    }

    public User getAdmin()
    {
        return admin;
    }

    public User[] getCurrentUsers()
    {
        User[] arr = new User[users.size()];

        arr = users.toArray(arr);

        return arr;
    }

    /**
     * delete the specific user in this event
     * @param toDelete
     */

    public void deleteUser(User toDelete)
    {
        if(users.remove(toDelete))
        {
            return;
        }

        throw new NoSuchElementException();
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String toSet)
    {
        content = toSet;
    }

    public class Time
    {
        private int day;
        private int month;
        private int year;

        private int hour;
        private int minute;

        public Time(String time)
        {
            Scanner s = new Scanner(time);

            try
            {
                month = s.nextInt();
                day = s.nextInt();
                year = s.nextInt();

                hour = s.nextInt();
                minute = s.nextInt();
            }
            catch (NoSuchElementException e)
            {
                System.out.println("Catched String mismatched in creating " + time + ", exit..");
                System.exit(0);
            }
            finally
            {
                s.close();
            }
        }

        public void getTime(int hour, int minute)
        {
            hour = this.hour;
            minute = this.minute;
        }

        public void setTime(int hour, int minute)
        {
            this.hour = hour;
            this.minute = minute;
        }

        public void getDate(int month, int day, int year)
        {
            month = this.month;
            day = this.day;
            year = this.year;
        }

        public void setDate(int month, int day, int year)
        {
            this.month = month;
            this.day = day;
            this.year = year;
        }
    }
}
