package goevents.online.samplevolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import goevents.online.samplevolley.activity.MainActivity;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Button select = (Button)findViewById(R.id.selectbtn);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAreaActivity.this, usermain.class);
                startActivity(intent);
            }
        });


        Button logout = (Button)findViewById(R.id.logoutbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAreaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
