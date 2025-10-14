package com.example.databaseauth;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
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
    private FrameLayout userFrame;
    private LayoutInflater layoutInflater;
    private Context context;
    private TextView nameView, stateView, ageView;
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
        users.add(new User("Иванов И.И", "В работе", 19, 0));
        users.add(new User("Иванов И.И", "В работе", 19, 1));
        users.add(new User("Иванов И.И", "В работе", 19, 2));
        users.add(new User("Иванов И.И", "В работе", 19, 1));
        users.add(new User("Иванов И.И", "В работе", 19, 2));
    }

    private void Init() {
        usersList = findViewById(R.id.usersList);
        userFrame = findViewById(R.id.userFrame);

        nameView = findViewById(R.id.nameView);
        stateView = findViewById(R.id.stateView);
        ageView = findViewById(R.id.ageView);

        context = this;

        layoutInflater = LayoutInflater.from(context);
        UsersAdapter usersAdapter = new UsersAdapter();
        usersList.setAdapter(usersAdapter);

        usersList.setOnItemClickListener(this::OnUserClick);
    }

    public void OnUserClick(AdapterView<?> parent, View view, int position, long id)
    {
        UserVisibility(true);
        SetUserData((User)parent.getAdapter().getItem(position));
    }

    public void BackToList(View view)
    {
        UserVisibility(false);
    }

    private void SetUserData(User user)
    {
        nameView.setText(user.getName());
        stateView.setText(user.getState());
        ageView.setText(String.valueOf(user.getAge()));
    }

    private void UserVisibility(boolean visible)
    {
        if (visible)
            userFrame.setVisibility(View.VISIBLE);
        else
            userFrame.setVisibility(View.GONE);
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

            FrameLayout stateIndicator = convertView.findViewById(R.id.stateIndicator);

            switch (currentUser.getStateSignal())
            {
                case 0:
                    stateIndicator.setBackgroundResource(R.drawable.back_offline);
                    break;
                case 1:
                    stateIndicator.setBackgroundResource(R.drawable.back_online);
                    break;
                case 2:
                    stateIndicator.setBackgroundResource(R.drawable.back_departed);
                    break;
            }

            return convertView;
        }
    }
}