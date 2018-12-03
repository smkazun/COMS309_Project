package com.example.admin.callardar.Classes;

import android.util.Log;

import java.util.ArrayList;

public class User implements Comparable<User>
{
    private int id;
    private String name;
    private String Email;
    private UserType type;

    private ArrayList<callenDar> calenders;
    private ArrayList<User> friends;

    public User(int ID, String Name, String Email)
    {
        id = ID;
        name = Name;
        this.Email = Email;
        type = null;

        calenders = new ArrayList<callenDar>();
        friends = new ArrayList<User>();
    }

    /**
     *  this constructor should call only adding user into calendar
     * @param Name
     * @param Email
     * @param Type
     */

    public User(int ID, String Name, String Email, UserType Type)
    {
        id = ID;
        name = Name;
        this.Email = Email;
        type = Type;
    }

    public int getID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return Email;
    }

    private UserType getType()
    {
        return type;
    }

    public void addFriends(User[] arr)
    {
        for(int i = 0 ; i < arr.length ; i += 1)
        {
            friends.add(arr[i]);
        }
    }

    public void deleteFriends(User toDelete)
    {
        friends.remove(toDelete);
    }

    public User[] getFriends()
    {
        User[] arr = new User[friends.size()];

        arr = friends.toArray(arr);

        return arr;
    }

    public void addCalender(callenDar calender)
    {
       calenders.add(calender);
    };

    public callenDar[] getCalender()
    {
        callenDar[] arr = new callenDar[calenders.size()];

        arr = calenders.toArray(arr);

        return arr;
    }

    public void set_CurCalender(int index, callenDar newCalendar)
    {
        calenders.set(index, newCalendar);
    }

    public void deleteCalender()
    {
        calenders = new ArrayList<callenDar>();
    }

    public void deleteCalendar(int index)
    {
        calenders.remove(index);
    }

    public UserType getType(callenDar calender)
    {
        User[] arr = calender.getCurrentUser();

        for(int i = 0 ; i < arr.length; i += 1)
        {
            if(arr[i].equals(this))
            {
                return arr[i].getType();
            }
        }

        throw new IllegalStateException("Cannot find " + name + " in " + calender);
    }

    public boolean equals(Object o)
    {
        if( o == null || getClass() != o.getClass())
        {
            return false;
        }

        User toCompare = (User) o;

        return name.equals(toCompare.getName()) && Email.equals(toCompare.getEmail()) && id == toCompare.id;
    }

    @Override
    public int compareTo(User arg0)
    {
        if(arg0 == null)
        {
            return 1;
        }

        for(int i = 0 ; i < getName().length() && i < arg0.getName().length() ; i += 1)
        {
            if(getName().charAt(i) < arg0.getName().charAt(i))
            {
                return -1;
            }
        }

        return -(getName().length() - arg0.getName().length());
    }
}
