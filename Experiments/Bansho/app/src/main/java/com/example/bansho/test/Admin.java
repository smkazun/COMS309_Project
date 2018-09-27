package com.example.bansho.test;

public class Admin extends abstractUser
{
    public Admin(String Name, String Email) {
        super(Name, Email);
    }

    public Admin(String Name, String Email, UserType Type) {
        super(Name, Email, Type);
    }
}
