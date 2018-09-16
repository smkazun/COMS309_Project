package com.example.admin.callardar;

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
     * get user's type
     * @return
     */

    UserType getType();

    boolean equals(Object o);
}
