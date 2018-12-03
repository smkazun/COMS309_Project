package com.example.admin.callardar.Classes;

import com.example.admin.callardar.CalendarList;

import java.util.ArrayList;

public class callenDar
{
    private int id;
    private String name;
    private User holder;

    private ArrayList<User> people;
    private ArrayList<User> admins;
    private ArrayList<Event> events;

    public callenDar(int id, String Name)
    {
        this.id = id;
        name = Name;
        events = new ArrayList<Event>();
    }

    public callenDar(String Name, User holder, User[] admins, User[] toAdd)
    {
        name = Name;
        people = new ArrayList<User>();
        this.admins = new ArrayList<User>();
        events = new ArrayList<Event>();
        this.holder = holder;

        //people.add(holder);

        for(int i = 0 ; i < toAdd.length ; i += 1)
        {
            people.add(toAdd[i]);
        }

        for(int i = 0 ; i < admins.length ; i += 1)
        {
            this.admins.add(admins[i]);
        }

        id = -1;
    }

    public callenDar(int id, String Name, User holder, User[] admins, User[] toAdd)
    {
        this(Name, holder, admins, toAdd);
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String toString()
    {
        return name;
    }

    public User getHolder()
    {
        return holder;
    }

    public User[] getCurrentUser()
    {
        if(people == null)
        {
            return null;
        }

        User[] arr = new User[people.size()];

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            arr[i] = people.get(i);
        }

        return arr;
    }

    public User[] getAdmin()
    {
        if(admins == null)
        {
            return null;
        }

        User[] arr = new User[admins.size()];

        for(int i = 0 ; i < arr.length ;i += 1)
        {
            arr[i] = admins.get(i);
        }

        return arr;
    }

    public void addUser(User toAdd, UserType type)
    {
        if(type == UserType.Normal)
        {
            people.add(toAdd);
        }

        if(type == UserType.Admin)
        {
            people.add(toAdd);
            admins.add(toAdd);
        }
    }

    public void Event(String item_title, String item_desc, String item_date) {
        events.add(new Event(item_title, item_desc, item_date));
    }

    public void Event(int id, String item_title, String item_desc, String item_date)
    {
        events.add(new Event(id, item_title, item_desc, item_date));
    }

    public Event[] eventViewer()
    {
        if(events == null)
        {
            return new Event[]{};
        }

        Event[] arr = new Event[events.size()];
        arr = events.toArray(arr);

        return arr;
    }

    public void deleteEvent(int index)
    {
        events.remove(index);
    }

    public int equals(callenDar obj)
    {
        if( id != obj.id || ! name.equals(obj.name))
        {
            return 1;
        }

        for(int i = 0 ; i < events.size() ; i += 1)
        {
            if(events.size() != obj.events.size())
            {
                return 2;
            }

            if( ! events.contains(obj.events.get(i)))
            {
                return 2;
            }
        }

        return 0;
    }
}
