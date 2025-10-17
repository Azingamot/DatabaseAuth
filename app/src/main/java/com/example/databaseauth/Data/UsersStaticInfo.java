package com.example.databaseauth.Data;

import java.util.ArrayList;
import java.util.List;

public class UsersStaticInfo {
    public static List<User> Users = new ArrayList<>();
    public final static String POSITION = "position";
    public UsersStaticInfo()
    {
        if (Users.isEmpty())
        {
            AddUsersInList();
        }
    }

    private void AddUsersInList() {
        Users.add(new User("Иванов И.И", "В работе", 19, 0));
        Users.add(new User("Иванов И.И", "В работе", 19, 1));
        Users.add(new User("Иванов И.И", "В работе", 19, 2));
        Users.add(new User("Иванов И.И", "В работе", 19, 1));
        Users.add(new User("Иванов И.И", "В работе", 19, 2));
    }

}
