package com.example.admin.callardar;

public abstract class abstractUser implements User
{
    private String name;
    private String Email;
    private UserType type;

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

    public UserType getType()
    {
        return type;
    }

    public boolean equals(Object o)
    {
        if( o == null || getClass() != o.getClass())
        {
            return false;
        }

        User toCompare = (User) o;

        return name == toCompare.getName() && Email == toCompare.getEmail() && type == toCompare.getType();
    }
}
