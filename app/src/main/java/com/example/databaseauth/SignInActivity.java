package com.example.databaseauth;

import static com.example.databaseauth.Data.UsersStaticInfo.PASSWORD;
import static com.example.databaseauth.Data.UsersStaticInfo.PROFILE_ID;
import static com.example.databaseauth.Data.UsersStaticInfo.USERS_SIGN_IN_INFO;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databaseauth.Data.UsersStaticInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class SignInActivity extends AppCompatActivity {

    private TextView PasswordTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        PasswordTextView = findViewById(R.id.passwordTextView);
    }

    public void SignIn(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String login = PasswordTextView.getText().toString();
                Object value = dataSnapshot.child(login).child(PASSWORD).getValue();
                if(value!=null)
                {
                    if(value.toString().equals(getPassword()))
                    {
                        goNext(dataSnapshot.child(login).child(PROFILE_ID).getValue().toString());
                    }
                    else CantSignIn();
                }
                else CantSignIn();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    private void goNext(String profileId)
    {
        UsersStaticInfo.profileId = profileId;
    }
    private void CantSignIn()
    {
        Toast.makeText(SignInActivity.this,
            "Неверный логин или пароль",
            Toast.LENGTH_SHORT).show();
    }
    private String getPassword() {
        return PasswordTextView.getText().toString();
    }
}