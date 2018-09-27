package com.example.bansho.test;


import java.util.ArrayList;

public abstract class abstractUser implements User
{
    private String name;
    private String Email;
    private UserType type;

    protected ArrayList<callenDar> calenders;

    protected abstractUser(String Name, String Email)
    {
        name = Name;
        this.Email = Email;
        type = null;

        calenders = new ArrayList<callenDar>();
    }

    /**
     *  this constructor should call only adding user into calendar
     * @param Name
     * @param Email
     * @param Type
     */

    protected abstractUser(String Name, String Email, UserType Type)
    {
        name = Name;
        this.Email = Email;
        type = Type;
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

    public UserType getType(callenDar calender)
    {
        User[] arr = calender.getCurrentUser();

        for(int i = 0 ; i < arr.length; i += 1)
        {
            if(arr[i].equals(this))
            {
                return ((abstractUser)arr[i]).getType();
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

        return name == toCompare.getName() && Email == toCompare.getEmail() && type == ((abstractUser)toCompare).getType();
    }
}

