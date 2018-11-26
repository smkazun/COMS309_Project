package com.example.admin.callardar.Classes;

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
    }

    public callenDar(String Name, User holder, User[] admins, User[] toAdd)
    {
        name = Name;
        people = new ArrayList<User>();
        this.admins = new ArrayList<User>();
        events = new ArrayList<Event>();

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

    public void Event(String item_title, String item_desc, String item_date) {
        events.add(new Event(item_title, item_desc, item_date));
    }

    public void Event(int id, String item_title, String item_desc, String item_date)
    {
        events.add(new Event(id, item_title, item_desc, item_date));
    }

//    /**
//     *  create a event in current callendar
//     * @param startDate
//     *  should be format like 7 11 2018
//     *                        15 35
//     * @param endDate
//     * @param content
//     * @param toAdd
//     */
//    public void eventCreator(int id, String name, String startDate, String endDate, String content, User admin, User[] toAdd)
//        {
//        events.add(new Event(id, name, startDate, endDate, content, admin, toAdd));
//
//        //toDo
//        //draw the UI on real callendar
//    }

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
}
