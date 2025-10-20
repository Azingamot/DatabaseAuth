package com.example.databaseauth;

import static com.example.databaseauth.Data.UsersStaticInfo.POSITION;
import static com.example.databaseauth.Data.UsersStaticInfo.Users;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databaseauth.Data.User;
import com.example.databaseauth.Data.UsersStaticInfo;
import com.example.databaseauth.Utils.Transform;

public class UserActivity extends AppCompatActivity {

    private User activeUser;
    private EditText nameEdit;
    private EditText statusEdit;
    private EditText ageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try
        {
            int position = getIntent().getIntExtra(POSITION, 0);
            activeUser = Users.get(position);
            Init();
            SetUserInfo();
        }
        catch (Exception ex)
        {
            Log.d("ERRORZ", ex.getMessage());
        }

    }

    private void Init()
    {
        nameEdit = findViewById(R.id.nameUserEdit);
        statusEdit = findViewById(R.id.stateUserEdit);
        ageEdit = findViewById(R.id.ageUserEdit);
    }

    private void SetUserInfo()
    {
        nameEdit.setText(activeUser.getName());
        statusEdit.setText(activeUser.getState());
        ageEdit.setText(String.valueOf(activeUser.getAge()));
    }

    public void Back(View view)
    {
        onBackClicked();
    }

    public void Save(View view)
    {
        onSaveClicked();
    }

    private void onBackClicked()
    {
        finish();
    }

    private void onSaveClicked()
    {
        activeUser.setName(nameEdit.getText().toString());
        activeUser.setState(statusEdit.getText().toString());
        activeUser.setAge(Transform.parseIntOrDefault(ageEdit.getText().toString(), activeUser.getAge()));

        MainActivity.UpdateList();

        finish();
    }
}