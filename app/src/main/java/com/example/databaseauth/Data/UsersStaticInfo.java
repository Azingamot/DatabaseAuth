package com.example.databaseauth.Data;

import com.example.databaseauth.Database.UsersBase;

import java.util.ArrayList;
import java.util.List;

public class UsersStaticInfo {
    public static List<User> Users = new ArrayList<>();
    public final static String POSITION = "position";
    public final static String USERS_SIGN_IN_INFO = "UsersSignInInfo";
    public final static String USERS_PROFILE_INFO = "UsersProfileInfo";
    public final static String PASSWORD = "password";
    public final static String PROFILE_ID = "profileId";
    public final static String NAME = "name";
    public final static String AGE = "age";
    public final static String STATE = "state";
    public static String profileId;
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
        UsersBase.PostUser(Users.get(0));
    }

}
