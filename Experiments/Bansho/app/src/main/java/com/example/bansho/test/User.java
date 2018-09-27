package com.example.bansho.test;

import java.util.ArrayList;

public interface User
{
    /**
     * get the user's name
     * @return
     */

    String getName();

    /**
     * get the user's Email
     * @return
     */

    String getEmail();

    /**
     * get user's type in this calendar
     * @return
     */

    UserType getType(callenDar calender);

    /**
     *  add a new calendar to current user
     * @param calender
     */

    void addCalender(callenDar calender);

    callenDar[] getCalender();

    boolean equals(Object o);
}
