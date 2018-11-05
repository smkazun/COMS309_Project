package com.example.admin.callardar.Classes;

import java.util.ArrayList;

public class callenDar
{
    private int id;
    private String name;

    private ArrayList<User> people;
    private ArrayList<User> admins;
    private ArrayList<Event> events;

    public callenDar(int id, String Name)
    {
        this.id = id;
        name = Name;
    }

    public callenDar(String Name, User[] admins, User[] toAdd)
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

    public callenDar(int id, String Name, User[] admins, User[] toAdd)
    {
        this(Name, admins, toAdd);
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

    public User[] getCurrentUser()
    {
        User[] arr = new User[people.size()];

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            arr[i] = people.get(i);
        }

        return arr;
    }

    public User[] getAdmin()
    {
        User[] arr = new User[admins.size()];

        for(int i = 0 ; i < arr.length ;i += 1)
        {
            arr[i] = admins.get(i);
        }

        return arr;
    }

    /**
     *  create a event in current callendar
     * @param startDate
     *  should be format like 7 11 2018
     *                        15 35
     * @param endDate
     * @param content
     * @param toAdd
     */
    public void eventCreator(String name, String startDate, String endDate, String content, User admin, User[] toAdd)
        {
        events.add(new Event(name, startDate, endDate, content, admin, toAdd));

        //toDo
        //draw the UI on real callendar
    }

    public Event[] eventViewer()
    {
        Event[] arr = new Event[events.size()];

        arr = events.toArray(arr);

        return arr;
    }

}
