package com.example.databaseauth;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databaseauth.Data.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView usersList;
    private LayoutInflater layoutInflater;
    private Context context;

    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AddUsersInList();
        Init();
    }

    private void AddUsersInList() {
        users.add(new User("Иванов И.И", "В работе", 19));
        users.add(new User("Иванов И.И", "В работе", 19));
        users.add(new User("Иванов И.И", "В работе", 19));
        users.add(new User("Иванов И.И", "В работе", 19));
        users.add(new User("Иванов И.И", "В работе", 19));
    }

    private void Init() {
        usersList = findViewById(R.id.usersList);

        context = this;

        layoutInflater = LayoutInflater.from(context);
        UsersAdapter usersAdapter = new UsersAdapter();
        usersList.setAdapter(usersAdapter);
    }

    public class UsersAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User currentUser = getItem(position);

            convertView = layoutInflater.inflate(R.layout.users_layout, parent, false);

            TextView nameView = convertView.findViewById(R.id.userNameView);
            TextView stateView = convertView.findViewById(R.id.userStateView);

            nameView.setText(currentUser.getName());
            stateView.setText(currentUser.getState());

            return convertView;
        }
    }
}