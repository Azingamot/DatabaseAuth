package com.example.databaseauth.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.databaseauth.Data.User;
import com.example.databaseauth.Data.UserProfile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class UsersBase {
    public static void PostUser(User user)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");
        ref.setValue(user);
    }

    public static void PostUserProfile(UserProfile profile)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users_profile");
        ref.setValue(profile);
    }

    public static void GetUserProfile()
    {

    }
}
